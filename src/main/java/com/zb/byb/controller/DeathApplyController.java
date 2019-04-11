package com.zb.byb.controller;

import com.zb.byb.entity.DeathApply;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.buildSuccess(null);
    }
    @ApiOperation("获取死亡申报记录")
    @GetMapping("/list")
    public ResponseEntity<List<DeathApply>> getList(){
        return null;
    }
}
