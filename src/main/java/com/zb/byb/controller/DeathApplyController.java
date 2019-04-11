package com.zb.byb.controller;

import com.zb.byb.entity.DeathApply;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation("保存死亡申报")
    @PostMapping("/save")
    public ResponseEntity<?> deathApply(@RequestBody DeathApply deathApply) {
        DeathApply info = new DeathApply();
        info.setDieDate(new Date());
        info.setBatchId("32xieli");
        info.setApplyDieNum(33);
        info.setId("ffdcie");
        info.setDayAge(31);
        info.setWei(32.32);
        info.setJing(65.434);
        return ResponseEntity.buildSuccess(info);
    }
    @ApiOperation("获取死亡申报记录")
    @GetMapping("/list")
    public ResponseEntity<List<DeathApply>> getList(){
        List<DeathApply> list = new ArrayList<>();
        DeathApply info = new DeathApply();
        info.setDieDate(new Date());
        info.setBatchId("32xieli");
        info.setApplyDieNum(33);
        info.setId("ffdcie");
        info.setDayAge(31);
        info.setWei(32.32);
        info.setJing(65.434);
        list.add(info);
        return ResponseEntity.buildSuccess(list);
    }
}
