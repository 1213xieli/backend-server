package com.zb.byb.controller;

import com.zb.byb.entity.DeathApply;
import com.zb.byb.entity.DrugRecord;
import com.zb.byb.entity.Pigwash;
import com.zb.byb.entity.PigwashRecord;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 领料记录
 */
@RestController
@RequestMapping("/api/receiveRecord")
public class ReceiveRecordController {
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
}
