package com.zb.byb.controller;

import com.zb.byb.entity.*;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 物资申请
 */
@RestController
@RequestMapping("/api/material")
public class MaterialController {
    /**
     * 我要领料
     * @param feedApply
     * @return
     */
    @ApiOperation("保存领料申请")
    @PostMapping("/saveFeedApply")
    public ResponseEntity<?> feedApply(@RequestBody FeedApply feedApply) {
        FeedApply fee = new FeedApply();
        fee.setId("xieli");
        fee.setBatchId("xieli");
        fee.setPlanDate(new Date());
        return ResponseEntity.buildSuccess(fee);
    }
    @ApiOperation("获取领料申请记录")
    @GetMapping("/feedList")
    public ResponseEntity<List<FeedApply>> getFeedList(){
        FeedApply fee = new FeedApply();
        fee.setId("xieli");
        fee.setBatchId("xieli");
        fee.setPlanDate(new Date());
        return ResponseEntity.buildSuccess(fee);
    }
    /**
     * 我要领药
     * @param drugApply
     * @return
     */
    @ApiOperation("保存领药申请")
    @PostMapping("/saveDrugApply")
    public ResponseEntity<?> drugApply(@RequestBody DrugApply drugApply) {
        return ResponseEntity.buildSuccess(null);
    }
    @ApiOperation("获取领药申请记录")
    @GetMapping("/drugList")
    public ResponseEntity<List<DrugApply>> getDrugList(){
        return null;
    }

    /**
     * 设备领用
     * @param equipmentApply
     * @return
     */
    @ApiOperation("保存设备领用申请")
    @PostMapping("/saveEquipmentApply")
    public ResponseEntity<?> equipmentApply(@RequestBody EquipmentApply equipmentApply) {
        return ResponseEntity.buildSuccess(null);
    }
    @ApiOperation("获取设备领用申请记录")
    @GetMapping("/equipmentList")
    public ResponseEntity<List<EquipmentApply>> getEquipmentList(){
        return null;
    }

    /**
     * 我要结算
     * @param balance
     * @return
     */
    @ApiOperation("保存结算申请")
    @PostMapping("/saveBalanceApply")
    public ResponseEntity<?> balanceApply(@RequestBody Balance balance) {
        return ResponseEntity.buildSuccess(null);
    }
    @ApiOperation("获取结算申请记录")
    @GetMapping("/balanceList")
    public ResponseEntity<List<Balance>> getBalanceList(){
        return null;
    }

    /**
     * 我要理财
     * @param financing
     * @return
     */
    @ApiOperation("保存理财申请")
    @PostMapping("/saveFinancingApply")
    public ResponseEntity<?> financingApply(@RequestBody Financing financing) {
        return ResponseEntity.buildSuccess(null);
    }
    @ApiOperation("获取理财申请记录")
    @GetMapping("/financingList")
    public ResponseEntity<List<Financing>> getFinancingList(){
        return null;


    }

}
