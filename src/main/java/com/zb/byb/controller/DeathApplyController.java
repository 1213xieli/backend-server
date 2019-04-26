package com.zb.byb.controller;


import com.github.pagehelper.PageInfo;
import com.zb.byb.common.C;
import com.zb.byb.entity.DeathApply;
import com.zb.byb.entity.FeedApply;
import com.zb.byb.entity.FeedRecord;
import com.zb.byb.service.DeathApplyService;
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
    public ResponseEntity<?> deathApply(@RequestBody(required = false) DeathApply deathApply, HttpServletRequest request) {
        //获取userId
        String userId=(String) request.getSession().getAttribute("userId");
        try {
            String backData= deathApplyService.deathApply(deathApply,userId);

            return ResponseEntity.buildSuccess(backData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.build(500,"保存失败");
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
            return ResponseEntity.build(100,"无法查询到数据");
        }

    }
    @ApiOperation("查看死亡申报记录详情")
    @GetMapping("/queryInfoById")
    public ResponseEntity<FeedRecord> queryInfoById(String rcordId)
    {
        try{
            if (C.checkNull(rcordId))
                throw new Exception("未传入rcordId.");
            return ResponseEntity.buildSuccess(deathApplyService.getDeathApplyRecordbyId(rcordId));
        }
        catch (Exception e)
        {
            return ResponseEntity.build(100, "无法查询到数据");
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
            return ResponseEntity.build(100, "无法查询到数据");
        }
    }

}
