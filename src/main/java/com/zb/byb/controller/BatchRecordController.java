package com.zb.byb.controller;

import com.zb.byb.entity.BatchRecord;
import com.zb.byb.entity.DeathApply;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 批次记录
 */
@RestController
@RequestMapping("/api/batchRecord")
public class BatchRecordController {
    @ApiOperation("获取批次记录")
    @GetMapping("/list")
    public ResponseEntity<List<BatchRecord>> getList(){
        List<BatchRecord> list = new ArrayList<>();
        BatchRecord info = new BatchRecord();
        return ResponseEntity.buildSuccess(list);
    }
}
