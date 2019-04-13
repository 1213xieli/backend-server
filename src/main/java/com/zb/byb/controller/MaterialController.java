package com.zb.byb.controller;

import com.zb.byb.entity.*;
import com.zb.byb.service.DrugApplyService;
import com.zb.byb.service.EquipmentApplyService;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 物资申请
 */
@RestController
@RequestMapping("/api/material")
public class MaterialController {

    @Autowired
    private DrugApplyService drugApplyService;
    @Autowired
    private EquipmentApplyService equipmentApplyService;


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
    public ResponseEntity<?> drugApply(DrugApply drugApply) {
        try{
            boolean flag = drugApplyService.saveInfo(drugApply);
            return ResponseEntity.buildSuccess(flag);
        }
        catch (Exception e)
        {
            return ResponseEntity.build(100, "无法保存数据");
        }
    }
    @ApiOperation("初始化我要领药数据")
    @GetMapping("/queryDrugApplyInitData")
    public ResponseEntity<DrugApply> queryDrugApplyInitData(String tokenid)
    {
        try{
            return ResponseEntity.buildSuccess(drugApplyService.queryListInitData(tokenid));
        }
        catch (Exception e)
        {
            return ResponseEntity.build(100, "无法查询到数据");
        }
    }

    @ApiOperation("根据用户id查询到投苗记录")
    @GetMapping("/queryTouMiaoRecordList")
    public ResponseEntity<DrugApply> queryDrugApplyRecordList(String tokenid)
    {
        try{
            return ResponseEntity.buildSuccess(drugApplyService.queryInfoRecordList(tokenid));
        }
        catch (Exception e)
        {
            return ResponseEntity.build(100, "无法查询到数据");
        }
    }


    /**
     * 前台传入，用户登录id
     * 返回 对象列表数据“TouMiao”
     * @return
     */
    @ApiOperation("获取领药申请记录")
    @GetMapping("/getDrugList")
    public ResponseEntity<List<DrugApply>> getDrugList(UserInfo userInfo){
        try{
            return ResponseEntity.buildSuccess(drugApplyService.queryListByUser(userInfo.getIdentity()));
        }
        catch (Exception e)
        {
            return ResponseEntity.build(100, "无法查询到数据");
        }
    }
    /**
     * 设备领用
     * @param
     * @return
     */
    @ApiOperation("保存领药申请")
    @PostMapping("/saveEquipmentApply")
    public ResponseEntity<?> saveEquipmentApply(EquipmentApply equipmentApply) {
        try{
            boolean flag = equipmentApplyService.saveInfo(equipmentApply);
            return ResponseEntity.buildSuccess(flag);
        }
        catch (Exception e)
        {
            return ResponseEntity.build(100, "无法保存数据");
        }
    }
    @ApiOperation("初始化设备申请数据")
    @GetMapping("/queryEquipmentApplyInitData")
    public ResponseEntity queryEquipmentApplyInitData(String tokenid)
    {
        try{
            return ResponseEntity.buildSuccess(equipmentApplyService.queryListInitData(tokenid));
        }
        catch (Exception e)
        {
            return ResponseEntity.build(100, "无法查询到数据");
        }
    }

    @ApiOperation("根据用户id查询到投苗记录")
    @GetMapping("/queryEquipmentApplyRecordList")
    public ResponseEntity<EquipmentApply> queryEquipmentApplyRecordList(String tokenid)
    {
        try{
            return ResponseEntity.buildSuccess(equipmentApplyService.queryInfoRecordList(tokenid));
        }
        catch (Exception e)
        {
            return ResponseEntity.build(100, "无法查询到数据");
        }
    }


    /**
     * 前台传入，用户登录id
     * 返回 对象列表数据“TouMiao”
     * @return
     */
    @ApiOperation("获取设备申请记录")
    @GetMapping("/getEquipmentApplyList")
    public ResponseEntity<List<EquipmentApply>> getEquipmentApplyList(UserInfo userInfo){
        try{
            return ResponseEntity.buildSuccess(equipmentApplyService.queryListByUser(userInfo.getIdentity()));
        }
        catch (Exception e)
        {
            return ResponseEntity.build(100, "无法查询到数据");
        }
    }


/*    *//**
     * 设备领用
     * @param equipmentApply
     * @return
     *//*
    @ApiOperation("保存设备领用申请")
    @PostMapping("/saveEquipmentApply")
    public ResponseEntity<?> equipmentApply(@RequestBody EquipmentApply equipmentApply) {
        EquipmentApply e = new EquipmentApply();
        e.setApplyDate(new Date());
        e.setFarmerName("xieli");
        e.setTotalPayment(13254.7);
        e.setEntrustedName("xieli");
        e.setEntrust(true);
        return ResponseEntity.buildSuccess(e);
    }
    @ApiOperation("获取设备领用申请记录")
    @GetMapping("/equipmentList")
    public ResponseEntity<List<EquipmentApply>> getEquipmentList(){
        List<EquipmentApply> list = new ArrayList<>();
        EquipmentApply e = new EquipmentApply();
        e.setApplyDate(new Date());
        e.setFarmerName("xieli");
        e.setTotalPayment(13254.7);
        e.setEntrustedName("xieli");
        e.setEntrust(true);
        list.add(e);
        return ResponseEntity.buildSuccess(list);
    }*/

    /**
     * 我要结算
     * @param balance
     * @return
     */
    @ApiOperation("保存结算申请")
    @PostMapping("/saveBalanceApply")
    public ResponseEntity<?> balanceApply(@RequestBody Balance balance) {
        Balance ba = new Balance();
        ba.setAliveRate(0.8);
        ba.setAvgDayAge(12);
        ba.setAvgWeight(0.8);
        ba.setId("xieliId");
        ba.setBatchId("batchid");
        ba.setGoodAmount(32);
        ba.setJinMiaoDate(new Date());
        return ResponseEntity.buildSuccess(ba);
    }

    @ApiOperation("获取结算申请记录")
    @GetMapping("/balanceList")
    public ResponseEntity<List<Balance>> getBalanceList(){
        List<Balance> list = new ArrayList<Balance>();
        Balance ba = new Balance();
        ba.setAliveRate(0.8);
        ba.setAvgDayAge(12);
        ba.setAvgWeight(0.8);
        ba.setId("xieliId");
        ba.setBatchId("batchid");
        ba.setGoodAmount(32);
        ba.setJinMiaoDate(new Date());
        list.add(ba);
        return ResponseEntity.buildSuccess(list);
    }

    /**
     * 我要理财
     * @param financing
     * @return
     */
    @ApiOperation("保存理财申请")
    @PostMapping("/saveFinancingApply")
    public ResponseEntity<?> financingApply(@RequestBody Financing financing) {
        Financing info = new Financing();
        info.setApplyDate(new Date());
        info.setApplyType("谢李");
        info.setAvaibleBalance(123.65);
        info.setBuyKnow("323");
        info.setChooseBalance(32.43);
        return ResponseEntity.buildSuccess(info);
    }
    @ApiOperation("获取理财申请记录")
    @GetMapping("/financingList")
    public ResponseEntity<List<Financing>> getFinancingList(){
        Financing info = new Financing();
        info.setApplyDate(new Date());
        info.setApplyType("谢李");
        info.setAvaibleBalance(123.65);
        info.setBuyKnow("323");
        info.setChooseBalance(32.43);
        List<Financing> list = new ArrayList<>();
        list.add(info);
        return ResponseEntity.buildSuccess(list);


    }

}
