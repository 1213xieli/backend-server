package com.zb.byb.controller;


import com.github.pagehelper.PageInfo;
import com.zb.byb.common.C;
import com.zb.byb.common.Commonconst;
import com.zb.byb.entity.DeathApply;
import com.zb.byb.entity.FeedApply;
import com.zb.byb.entity.FeedRecord;
import com.zb.byb.entity.FileEntry;
import com.zb.byb.service.DeathApplyService;
import com.zb.byb.util.Image2Base64Util;
import com.zb.byb.util.WeixinUtils;
import com.zb.framework.common.entity.Message;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 死亡申报
 */
@RestController
@RequestMapping("/api/deathApply")
public class DeathApplyController {
    @Autowired
    private DeathApplyService deathApplyService;
    @ApiOperation("保存死亡申报")
    @PostMapping("/save")
    public ResponseEntity<?> deathApply(@RequestBody(required = false) DeathApply deathApply, HttpServletRequest request,String mediaId) {
        //获取userId
        String userId=(String) request.getSession().getAttribute("userId");
        String base64FromInputStream = WeixinUtils.getInputStreamImg(mediaId);
        FileEntry fileEntry = new FileEntry();
        fileEntry.setImgContent(base64FromInputStream);
        fileEntry.setImgType(".jpg");
        List<FileEntry> list=new ArrayList<>();
        list.add(fileEntry);
        deathApply.setImgUrl(list);
        try {
            if(C.checkNullOrEmpty(userId)){
                throw new Exception("未传人养户id");
            }
            String backData= deathApplyService.deathApply(deathApply,"Va4AAAL/iSHMns7U");
            return ResponseEntity.buildSuccess(backData);
        } catch (Exception e) {
            e.printStackTrace();
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
        }
    }
    @ApiOperation("获取死亡申报记录")
    @GetMapping("/list")
    public ResponseEntity<List<DeathApply>> getList(String starttime,String endtime,String state,Integer pageNumber,Integer pageSize, HttpServletRequest request){
        //获取userId
        String userId=(String) request.getSession().getAttribute("userId");
        DeathApply deathApply=new DeathApply();
        deathApply.setStarttime(starttime);
        deathApply.setEndtime(endtime);
        deathApply.setPageSize(pageSize);
        deathApply.setState(state);
        deathApply.setPageNumber(pageNumber);
        try {
            List<DeathApply> list= deathApplyService.getDeathApplyRecord(userId,deathApply);
            PageInfo<FeedRecord> info = new PageInfo(list);
            return ResponseEntity.buildSuccess(info);
            //return ResponseEntity.buildSuccess(batchRecordService.viewBatchRecord(batchId,openId));
        } catch (Exception e) {
            e.printStackTrace();
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
        }

    }
    @ApiOperation("查看死亡申报记录详情")
    @GetMapping("/queryInfoById")
    public ResponseEntity<DeathApply> queryInfoById(String rcordId)
    {
        try{
            if (C.checkNull(rcordId))
                throw new Exception("未传入rcordId.");
            return ResponseEntity.buildSuccess(deathApplyService.getDeathApplyRecordbyId(rcordId));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
        }
    }

    @ApiOperation("取消死亡申报")
    @GetMapping("/cancleById")
    public ResponseEntity<DeathApply> cancleById(String rcordId)
    {
        try{
            if (C.checkNull(rcordId))
                throw new Exception("未传入rcordId.");
            return ResponseEntity.buildSuccess(deathApplyService.cancleDeathApply(rcordId));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
        }
    }

    @ApiOperation("死亡签名")
    @PostMapping("/singer")
    public ResponseEntity<DeathApply> singer(String rcordId, @RequestBody FileEntry fileEntry)
    {
        fileEntry= Image2Base64Util.subBase64(fileEntry);
        List<FileEntry> signerList=new ArrayList<>();
        signerList.add(fileEntry);
        DeathApply deathApply=new DeathApply();
        deathApply.setRcordId(rcordId);
        deathApply.setSignerList(signerList);
        try{
            if (C.checkNull(rcordId))
                throw new Exception("未传入rcordId.");
            String s = deathApplyService.signerDeathApply(deathApply);
            if ("0000".equals(JSONObject.fromObject(s).getString("code")))
                return ResponseEntity.buildSuccess(s);
            return ResponseEntity.build(100,"签名保存失败");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
        }
    }


}
