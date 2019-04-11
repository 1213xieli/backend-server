package com.zb.byb.controller;

import com.zb.byb.entity.DeathApply;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 结算记录
 */
@RestController
@RequestMapping("/api/balanceRecord")
public class BalanceRecordController {
    @ApiOperation("获取结算记录")
    @GetMapping("/list")
    public ResponseEntity<List<DeathApply>> getList(){
        List<DeathApply>  list = new ArrayList<>();
        DeathApply info = new DeathApply();
        info.setId("xieli");
        info.setActualDieNum(32);
        info.setApplyDieNum(31);
        info.setBatchId("xie3222");
        info.setDieDate(new Date());
        list.add(info);
        return ResponseEntity.buildSuccess(list);
    }

}
