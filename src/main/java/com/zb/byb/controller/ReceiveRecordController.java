package com.zb.byb.controller;

import com.zb.byb.common.C;
import com.zb.byb.common.Commonconst;
import com.zb.byb.entity.DeathApply;
import com.zb.byb.entity.DrugRecord;
import com.zb.byb.entity.Pigwash;
import com.zb.byb.entity.PigwashRecord;
import com.zb.byb.service.ReceivedRecordService;
import com.zb.framework.common.entity.Message;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 领料记录
 */
@RestController
@RequestMapping("/api/receiveRecord")
public class ReceiveRecordController {
    @Autowired
    private ReceivedRecordService receivedRecordService;
    @ApiOperation("获取领料记录")
    @GetMapping("/pigwashRecordlist")
    public ResponseEntity<List<PigwashRecord>> getPigwashRecordList(){
        List<PigwashRecord> list = new ArrayList<>();
        PigwashRecord info = new PigwashRecord();
        info.setAmount(5433);
        info.setBread("ewexx");
        info.setPrice(3232.4343);
        info.setSpec("xieli");
        list.add(info);
        return ResponseEntity.buildSuccess(list);
    }

    @ApiOperation("获取领药记录")
    @GetMapping("/drugRecordlist")
    public ResponseEntity<List<DrugRecord>> getDrugRecordList(){
        List<DrugRecord> list = new ArrayList<>();
        DrugRecord info = new DrugRecord();
        info.setAmount(32);
        info.setName("xieli");
        list.add(info);
        return ResponseEntity.buildSuccess(list);
    }

    @ApiOperation("获取领用记录")
    @GetMapping("/receivedRecord")
    public ResponseEntity<?> getReceivedRecordList(HttpServletRequest request){
        String userId = (String)request.getSession().getAttribute("userId");

        try {
            List list = receivedRecordService.getReceivedList(userId,new Object());
            return ResponseEntity.buildSuccess(list);
        } catch (Exception e) {
            e.printStackTrace();
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
        }
    }
}
