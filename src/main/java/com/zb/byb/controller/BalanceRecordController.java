package com.zb.byb.controller;

import com.zb.byb.entity.BalanceRecord;
import com.zb.byb.entity.DeathApply;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/balanceRecord")
public class BalanceRecordController {
    @ApiOperation("获取结算记录")
    @GetMapping("/list")
    public ResponseEntity<List<BalanceRecord>> getList(){
        return null;
    }

}
