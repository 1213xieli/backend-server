package com.zb.byb.controller;

import com.zb.byb.entity.FeedRecord;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
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
        FeedRecord info = new FeedRecord();
        info.setBatchId("32fv");
        info.setFeedDate(new Date());
        info.setId("xxxieli");
        return ResponseEntity.buildSuccess(info);
    }
    @ApiOperation("获取饲喂记录")
    @GetMapping("/list")
    public ResponseEntity<List<FeedRecord>> getList(){
        List<FeedRecord> list = new ArrayList<>();
        FeedRecord info = new FeedRecord();
        info.setBatchId("32fv");
        info.setFeedDate(new Date());
        info.setId("xxxieli");
        list.add(info);
        return ResponseEntity.buildSuccess(list);
    }
}
