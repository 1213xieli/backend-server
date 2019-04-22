package com.zb.byb.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zb.byb.common.Constants;
import com.zb.byb.entity.Batch;
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
import org.springframework.web.bind.annotation.RequestParam;
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
    private ObjectMapper objectMapper;
    @Autowired
    private BatchRecordService batchRecordService;

    @ApiOperation("查看批次记录")
    @GetMapping("/list")
    public ResponseEntity<BatchRecord> getList(HttpServletRequest request,BatchRecord batchRecord){
        //获取openid
        //String openId = RequestUtils.getCookieByName(request, Constants.OPEN_ID);
        //获取userId
        String userId=(String) request.getSession().getAttribute("userId");
        //批次号
        String batchId=batchRecord.getBatchid();
        try {
            BatchRecord batchRecord1= batchRecordService.viewBatchRecord(batchId,userId);
            //String data=JSONObject.fromObject(backData).getString("data");
            //System.out.println("data="+data);
            //转成list
            //JSONArray fromObject = JSONArray.fromObject(backData);
            //List<BatchRecord> list = fromObject.toList(fromObject,BatchRecord.class);
            ResponseEntity<BatchRecord> resp=new ResponseEntity<>();
            resp.setData(batchRecord1);
            return resp;
            //return ResponseEntity.buildSuccess(batchRecordService.viewBatchRecord(batchId,openId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.build(100,"查询不到数据");
        }

    }
    @ApiOperation("获取批次列表下拉框")
    @GetMapping("/batchListList")
    public ResponseEntity<List<Batch>> getBatchList(HttpServletRequest request,Batch batch){
        String userId=(String) request.getSession().getAttribute("userId");
        userId="mRkwGN6DQgGNsONd+yMkV8yeztQ=";
        System.out.println("=="+userId);
        try {
            String str=batchRecordService.getBatchList(userId,batch);
            System.out.println("str="+str);
            String batchIdlist=JSONObject.fromObject(str).getString("data");
            System.out.println("下拉batchIdlist="+batchIdlist);
            List<Batch> list=objectMapper.readValue(batchIdlist,List.class);
            ResponseEntity<List<Batch>> resp=new ResponseEntity<>();
            resp.setData(list);
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.build(500);
        }
    }

}
