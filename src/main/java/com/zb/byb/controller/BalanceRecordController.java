package com.zb.byb.controller;

import com.zb.byb.entity.Balance;
import com.zb.byb.entity.BalanceRecord;

import com.zb.byb.service.BalanceService;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/balanceRecord")
public class BalanceRecordController {
    @Autowired
    private BalanceService balanceService;
    @ApiOperation("结算申请")
    @PostMapping("/apply")
    public ResponseEntity<?> balanceApply(@RequestBody Balance balance,HttpServletRequest request){
        try {
            String backData=balanceService.balanceApply(balance,(String) request.getSession().getAttribute("userId"));
            return ResponseEntity.buildSuccess(backData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.build(500);

        }

    }

    @ApiOperation("获取结算记录")
    @GetMapping("/list")
    public ResponseEntity<List<BalanceRecord>> getList(BalanceRecord balanceRecord,HttpServletRequest request){
        try {
            String backData=balanceService.getBalanceRecord(balanceRecord,(String) request.getSession().getAttribute("userId"));
            return ResponseEntity.buildSuccess(backData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.build(500);
        }

    }

}
