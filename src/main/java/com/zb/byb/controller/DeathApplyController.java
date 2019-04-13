package com.zb.byb.controller;


import com.zb.byb.entity.DeathApply;
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
    public ResponseEntity<?> deathApply(@RequestBody DeathApply deathApply, HttpServletRequest request) {
        //获取userId
        String userId=(String) request.getSession().getAttribute("userId");
        try {
            String backData= deathApplyService.deathApply(deathApply,userId);
            return ResponseEntity.buildSuccess(backData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.build(500,"服务器出错");
        }
    }
    @ApiOperation("获取死亡申报记录")
    @GetMapping("/list")
    public ResponseEntity<List<DeathApply>> getList(HttpServletRequest request){
        //获取userId
        String userId=(String) request.getSession().getAttribute("userId");
        try {
            String backData= deathApplyService.getDeathApplyRecord(userId);
            String data=JSONObject.fromObject(backData).getString("data");
            //转成list
            JSONArray fromObject = JSONArray.fromObject(backData);
            List<DeathApply> list1 = fromObject.toList(fromObject,DeathApply.class);
            ResponseEntity<List<DeathApply>> resp=new ResponseEntity<>();
            resp.setData(list1);
            return resp;
            //return ResponseEntity.buildSuccess(batchRecordService.viewBatchRecord(batchId,openId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.build(500,"服务器出错");
        }

    }
}
