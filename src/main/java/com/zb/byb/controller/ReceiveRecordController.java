package com.zb.byb.controller;

import com.zb.byb.entity.DeathApply;
import com.zb.byb.entity.DrugRecord;
import com.zb.byb.entity.PigwashRecord;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/receiveRecord")
public class ReceiveRecordController {
    @ApiOperation("获取领料记录")
    @GetMapping("/pigwashRecordlist")
    public ResponseEntity<List<PigwashRecord>> getPigwashRecordList(){
        return null;
    }

    @ApiOperation("获取领药记录")
    @GetMapping("/drugRecordlist")
    public ResponseEntity<List<DrugRecord>> getDrugRecordList(){
        return null;
    }
}
