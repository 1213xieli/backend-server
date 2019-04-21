package com.zb.byb.controller;

import com.github.pagehelper.PageInfo;
import com.zb.byb.common.CommonFunc;
import com.zb.byb.entity.Batch;
import com.zb.byb.entity.FeedRecord;

import com.zb.byb.entity.Pigwash;
import com.zb.byb.service.FeedRecordService;

import com.zb.framework.common.entity.Message;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 饲喂记录
 */
@RestController
@RequestMapping("/api/feedRecord")
public class FeedRecordController {
    @Autowired
    private FeedRecordService feedRecordService;
    @ApiOperation("保存饲喂记录")
    @PostMapping("/save")
    public ResponseEntity<?> saveFeedRecord(@RequestBody(required = false) FeedRecord feedRecord, HttpServletRequest request) {
        //获取userId
        String userId=(String) request.getSession().getAttribute("userId");
        try {
            return ResponseEntity.buildSuccess(feedRecordService.addFeedRecord(feedRecord,userId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.build(100, "无法保存数据");
        }
    }
    @ApiOperation("获取饲料列表")
    @GetMapping("/getPigwashList")
    public ResponseEntity<?> getPigwashList(Batch batch, HttpServletRequest request) {
        String sessionId=(String) request.getSession().getAttribute("sessionId");
        String batchId=batch.getId();
        System.out.println("batchId="+batchId);
        System.out.println("sessionId="+sessionId);
        try {
            return ResponseEntity.buildSuccess(feedRecordService.pigwashList(batchId));
        } catch (Exception e) {
            e.printStackTrace();
           return ResponseEntity.build(100, "无法查询到数据");
        }
    }

    @ApiOperation("获取饲喂记录")
    @GetMapping("/list")
    public ResponseEntity<List<FeedRecord>> getList(FeedRecord feedRecord,HttpServletRequest request){
        //获取userId

        String custId=(String) request.getSession().getAttribute("userId");
        try {
            if (CommonFunc.checkNull(custId))
                throw new Exception("未传入养户id.");
            List<FeedRecord> list = feedRecordService.queryFeedRecordList(custId,feedRecord);
            //给外层批次赋值
            for(int i=0;i<list.size();i++){
                list.get(i).setBatchId(list.get(i).getFeedList().get(0).getBatchId());
                list.get(i).setBatchName(list.get(i).getFeedList().get(0).getBatchName());
            }
            List<Pigwash> feedList = list.get(0).getFeedList();


            PageInfo<FeedRecord> info = new PageInfo(list);

            return ResponseEntity.build(100,new Message(),info);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.build(100, "无法查询到数据");
        }
    }

    @ApiOperation("根据id查询到对象信息")
    @GetMapping("/queryInfoById")
    @ResponseBody
    public ResponseEntity<FeedRecord> queryInfoById(String rcordId)
    {
        try{
            if (CommonFunc.checkNull(rcordId))
                throw new Exception("未传入rcordId.");
            FeedRecord feedRecord = feedRecordService.queryFeedRecordbyRcordId(rcordId);
            if (null==feedRecord){
                return ResponseEntity.build(100, "无法查询到数据");
            }
            List<Pigwash> feedList = feedRecord.getFeedList();
            ResponseEntity<FeedRecord> recordResponseEntity=new ResponseEntity<>();
           /* for (Pigwash p :
                    feedList) {
                feedRecord.setBatchName(p.getBatchName()+"");
                feedRecord.setBatchId(p.getBatchId()+"");
                break;
            }*/
            recordResponseEntity.setData(feedRecord);
            //System.out.println(feedRecord.getBatchName());;
            return recordResponseEntity;
        }
        catch (Exception e)
        {
            return ResponseEntity.build(100, "无法查询到数据");
        }
    }
}
