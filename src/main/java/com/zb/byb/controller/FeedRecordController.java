package com.zb.byb.controller;

import com.zb.byb.entity.FeedRecord;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 饲喂记录
 */
@RestController
@RequestMapping("/api/feedRecord")
public class FeedRecordController {
    @ApiOperation("保存饲喂记录")
    @PostMapping("/save")
    public ResponseEntity<?> saveFeedRecord(@RequestBody FeedRecord feedRecord) {
        return ResponseEntity.buildSuccess(null);
    }
    @ApiOperation("获取饲喂记录")
    @GetMapping("/list")
    public ResponseEntity<List<FeedRecord>> getList(){
        return null;
    }
}
