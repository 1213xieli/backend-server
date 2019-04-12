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
        String openId = RequestUtils.getCookieByName(request, Constants.OPEN_ID);
        //批次号
        String batchId=batchRecord.getBatchId();
        //假数据
        List<BatchRecord> list=new ArrayList<>();
        BatchRecord record=new BatchRecord();
        record.setBatchId("zb221");
        record.setBread("黑猪");
        record.setDayAge(10);
        record.setDieNum(10);
        record.setInNum(7777);
        BatchRecord record2=new BatchRecord();
        record2.setBatchId("zbc2212");
        record2.setBread("白猪");
        record2.setDayAge(1);
        record2.setDieNum(200);
        record2.setInNum(120);
        list.add(record2);
        list.add(record);
        ResponseEntity<List<BatchRecord>> resp=new ResponseEntity<>();
        resp.setData(list);
        try {
            String backData= batchRecordService.viewBatchRecord(batchId,openId);
            JSONObject jsonObjec=JSONObject.fromObject(backData);



            return resp;
            //return ResponseEntity.buildSuccess(batchRecordService.viewBatchRecord(batchId,openId));
        } catch (Exception e) {
            return ResponseEntity.build(100, "无法查询");
        }
    }





}
