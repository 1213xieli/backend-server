package com.zb.byb.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zb.byb.entity.FeedRecord;
import com.zb.byb.entity.FeedTypeBean;
import com.zb.byb.entity.Pigwash;
import com.zb.byb.service.BatchRecordService;
import com.zb.byb.service.FeedRecordService;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
    private ObjectMapper objectMapper;
    @Autowired
    private FeedRecordService feedRecordService;
    @Autowired
    private BatchRecordService batchRecordService;
    @ApiOperation("保存饲喂记录")
    @PostMapping("/save")
    public ResponseEntity<?> saveFeedRecord(@RequestBody FeedRecord feedRecord, HttpServletRequest request) {
        FeedRecord feedRecord1 =new FeedRecord();
        feedRecord.setBatchName("002");
        Pigwash pigwash1=new Pigwash();
        List<Pigwash>list=new ArrayList<>();
        pigwash1.setConsumeQty(100);
        pigwash1.setFeedName("优质大猪料");
        list.add(pigwash1);
        //
        Pigwash pigwash2=new Pigwash();
        pigwash2.setConsumeQty(50);
        pigwash2.setFeedName("中等小猪料");
        list.add(pigwash2);
        //
        //feedRecord1.setBatchId("cheng");
        //feedRecord1.setId("cheng001");
        feedRecord1.setFeedList(list);
        //feedRecord.setFeedList();
        String userId=(String) request.getSession().getAttribute("userId");
        userId="Va4AAAA+/JHMns7U";//测试方便写死
        try {
            return ResponseEntity.buildSuccess(feedRecordService.addFeedRecord(feedRecord1,userId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.build(500,"服务器出现问题");
        }
    }
    @ApiOperation("获取饲喂记录")
    @GetMapping("/list")
    public ResponseEntity<List<FeedRecord>> getList(FeedRecord feedRecord,HttpServletRequest request){
        //String batchId=feedRecord.getBatchId();
        String recordId="";
        System.out.println("饲喂batchId="+recordId);
        //获取userId
        String userId=(String) request.getSession().getAttribute("userId");
        userId="Va4AAAA+/JHMns7U";//测试方便写死
        recordId="Va4AAAiadCCzx1nH";
        try {
            String backData= feedRecordService.queryFeedRecord(recordId,userId);
            //List<FeedRecord> list=objectMapper.readValue(backData,List.class);
            System.out.println("backData="+backData);
            String data=JSONObject.fromObject(backData).getString("data");
            List<FeedRecord> list=objectMapper.readValue(data,List.class);
            ResponseEntity<List<FeedRecord>> resp=new ResponseEntity<>();
            resp.setData(list);
            return resp;

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.build(500,"服务器出错");
        }
    }
    @ApiOperation("饲喂记录下拉框列表")
    @GetMapping("/siweiList")
    public ResponseEntity<List<FeedRecord>> getList(HttpServletRequest request){

        //获取userId
        String userId=(String) request.getSession().getAttribute("userId");
        userId="Va4AAAA+/JHMns7U";//测试方便写死

        String backData=feedRecordService.queryFeedRecordList(null,userId);
        System.out.println("backData="+backData);
        String data=JSONObject.fromObject(backData).getJSONArray("data").toString();
        System.out.println(data);
        ResponseEntity<List<FeedRecord>> resp=new ResponseEntity<>();

        try {
            List<FeedRecord> list=objectMapper.readValue(data,List.class);
            resp.setData(list);
            return  resp;
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.buildSuccess(data);
        }



    }
}
