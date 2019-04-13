package com.zb.byb.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.zb.byb.common.Constants;
import com.zb.byb.entity.BatchRecord;
import com.zb.byb.entity.DeathApply;
import com.zb.byb.service.BatchRecordService;
import com.zb.byb.service.TouMiaoService;
import com.zb.byb.util.HttpUtils;
import com.zb.byb.util.JDService;
import com.zb.byb.util.MethodName;
import com.zb.byb.util.RequestUtils;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 批次记录
 */
@RestController
@RequestMapping("/api/batchRecord")
public class BatchRecordController {
    @Autowired
    private BatchRecordService batchRecordService;
    @ApiOperation("获取批次记录")
    @GetMapping("/list")
    public ResponseEntity<List<BatchRecord>> getList(HttpServletRequest request,BatchRecord batchRecord){
        //获取openid
        //String openId = RequestUtils.getCookieByName(request, Constants.OPEN_ID);
        //获取userId
        String userId=(String) request.getSession().getAttribute("userId");
        //批次号
        String batchId=batchRecord.getBatchId();
        try {
            String backData= batchRecordService.viewBatchRecord(batchId,userId);
            String data=JSONObject.fromObject(backData).getString("data");
            System.out.println("data="+data);
            //转成list
            JSONArray fromObject = JSONArray.fromObject(backData);
            List<BatchRecord> list = fromObject.toList(fromObject,BatchRecord.class);
            ResponseEntity<List<BatchRecord>> resp=new ResponseEntity<>();
            resp.setData(list);
            return resp;
            //return ResponseEntity.buildSuccess(batchRecordService.viewBatchRecord(batchId,openId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.build(500,"服务器出错");
        }
    }





}
