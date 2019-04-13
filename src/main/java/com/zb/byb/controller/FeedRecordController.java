package com.zb.byb.controller;


import com.zb.byb.entity.FeedRecord;
import com.zb.byb.service.FeedRecordService;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 饲喂记录
 */
@RestController
@RequestMapping("/api/feedRecord")
public class FeedRecordController {
    @Autowired
    FeedRecordService feedRecordService;
    @ApiOperation("保存饲喂记录")
    @PostMapping("/save")
    public ResponseEntity<?> saveFeedRecord(@RequestBody FeedRecord feedRecord, HttpServletRequest request) {
        String userId=(String) request.getSession().getAttribute("userId");
        try {
            return ResponseEntity.buildSuccess(feedRecordService.addFeedRecord(feedRecord,userId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.build(500,"服务出现问题");
        }
    }
    @ApiOperation("获取饲喂记录")
    @GetMapping("/list")
    public ResponseEntity<List<FeedRecord>> getList(HttpServletRequest request){
        //获取userId
        String userId=(String) request.getSession().getAttribute("userId");
        try {
            String backData= feedRecordService.queryFeedRecord(userId);
            String data= JSONObject.fromObject(backData).getString("data");
            //转成list
            JSONArray fromObject = JSONArray.fromObject(backData);
            List<FeedRecord> list1 = fromObject.toList(fromObject,FeedRecord.class);
            ResponseEntity<List<FeedRecord>> resp=new ResponseEntity<>();
            resp.setData(list1);
            return resp;
            //return ResponseEntity.buildSuccess(batchRecordService.viewBatchRecord(batchId,openId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.build(500,"服务器出错");
        }


    }
}
