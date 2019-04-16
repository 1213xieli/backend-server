package com.zb.byb.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zb.byb.entity.FeedRecord;

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
import java.util.LinkedList;
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

        String userId=(String) request.getSession().getAttribute("userId");
        userId="Va4AAAA+/JHMns7U";//测试方便写死
        try {
            return ResponseEntity.buildSuccess(feedRecordService.addFeedRecord(feedRecord,userId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.build(500,"服务器出现问题");
        }
    }
    @ApiOperation("获取饲喂记录")
    @GetMapping("/list")
    public ResponseEntity<FeedRecord> getList(FeedRecord feedRecord,HttpServletRequest request){
        //String batchId=feedRecord.getBatchId();
        String recordId="";
        System.out.println("饲喂batchId="+recordId);
        //获取userId
        String userId=(String) request.getSession().getAttribute("userId");
        userId="Va4AAAA+/JHMns7U";//测试方便写死
        recordId="Va4AAAiaeB+zx1nH";
        try {
            String backData= feedRecordService.queryFeedRecord(recordId,userId);
            //List<FeedRecord> list=objectMapper.readValue(backData,List.class);
            System.out.println("backData="+backData);
            JSONArray arr=JSONObject.fromObject(backData).getJSONObject("data").getJSONArray("feedList");

            FeedRecord feedRecord1=new FeedRecord();
            List<Pigwash> list=new LinkedList<>();
            for(int i=0;i<arr.size();i++){
                Pigwash pigwash=new Pigwash();
                JSONObject json=arr.getJSONObject(i);
                pigwash.setFeedName("");
                pigwash.setFeedId(json.getString("id"));
                list.add(pigwash);
            }
            feedRecord1.setFeedList(list);
            ResponseEntity<FeedRecord> resp=new ResponseEntity<>();
            resp.setData(feedRecord1);
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
