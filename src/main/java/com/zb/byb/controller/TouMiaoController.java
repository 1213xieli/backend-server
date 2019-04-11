package com.zb.byb.controller;

import com.zb.byb.entity.TouMiao;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 投苗申请
 */
@RestController
@RequestMapping("/api/toumiao")
public class TouMiaoController {
    @ApiOperation("保存投苗申请")
    @PostMapping
    public ResponseEntity<?> toumiaoApply(@RequestBody TouMiao touMiao) {
        return ResponseEntity.buildSuccess(null);
    }
    @ApiOperation("获取投苗记录列表")
    @GetMapping
    public ResponseEntity<List<TouMiao>> getList(){
        return null;
    }
}
