package com.zb.byb.controller;

import com.github.pagehelper.PageInfo;
import com.zb.byb.common.C;
import com.zb.byb.entity.*;
import com.zb.byb.service.DrugApplyService;
import com.zb.byb.service.EquipmentApplyService;
import com.zb.byb.service.FeedApplyService;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private FeedApplyService feedApplyService;


    /**
     * 我要领料
     * @param feedApply
     * @return
     */
    @ApiOperation("保存领料申请")
    @PostMapping("/saveFeedApply")
    public ResponseEntity<?> feedApply(@RequestBody FeedApply feedApply,HttpServletRequest request) {
        String userId=(String) request.getSession().getAttribute("userId");
        feedApply.setBatchId("QOKuwU+4Q5uVQ5msWQNUVEMbbjA=");
        feedApply.setBatchName("已删除薛昌宇001");
        feedApply.setDriverName("小王");
        feedApply.setPlandate("2019-05-06");
        feedApply.setDriverIdcard("360122199409090915");
        Pigwash pigwash=new Pigwash();
        pigwash.setPrice(2.0);
        pigwash.setAmount(40.0);
        List<Pigwash> list=new ArrayList();
        list.add(pigwash);
        feedApply.setPickDetail(list);
        try {
            System.out.println("userid="+userId);
            String id= feedApplyService.feedApply(feedApply,userId);
            return ResponseEntity.buildSuccess(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.build(100, "无法保存数据");
        }

    }

    /*@ApiOperation("获取领料申请记录")
    @GetMapping("/feedList")
    public ResponseEntity<List<FeedApply>> getFeedList(){
        String userId=(String) request.getSession().getAttribute("userId");
        try {
            return ResponseEntity.buildSuccess(feedRecordService.addFeedRecord(feedRecord,userId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.build(100, "无法保存数据");
        }
    }*/


    /**
     * 我要领药
     * @param drugApply
     * @return
     */
    @ApiOperation("保存领药申请")
    @PostMapping("/saveDrugApply")
    public ResponseEntity<?> saveDrugApply(HttpServletRequest request, DrugApply drugApply) {
        try{
            String custId = C.parseStr(request.getSession().getAttribute("custId"));
            drugApply.setCustId(custId);
            return ResponseEntity.buildSuccess(drugApplyService.saveInfo(drugApply));
        }
        catch (Exception e)
        {
            return ResponseEntity.build(100, "无法保存数据");
        }
    }
    @ApiOperation("初始化我要领药数据传入custId")
    @GetMapping("/queryDrugApplyInitData")
    public ResponseEntity<DrugApply> queryDrugApplyInitData(HttpServletRequest request)
    {
        String custId = C.parseStr(request.getSession().getAttribute("custId"));
        try{
            if (C.checkNull(custId))
                throw new Exception("未传入养户id");

            return ResponseEntity.buildSuccess(drugApplyService.queryListInitData(custId));
        }
        catch (Exception e)
        {
            return ResponseEntity.build(100, "无法查询到数据");
        }
    }

    @ApiOperation("根据用户id查询到记录列表")
    @GetMapping("/queryDrugApplyRecordList")
    public ResponseEntity<?> queryDrugApplyRecordList(HttpServletRequest request)
    {
        String custId = C.parseStr(request.getSession().getAttribute("custId"));
        try{
            if (C.checkNull(custId))
                throw new Exception("未传入养户id");

            List<DrugApply> list = drugApplyService.queryInfoRecordList(custId);
            PageInfo page = new PageInfo(list);
            return ResponseEntity.buildSuccess(page);
        }
        catch (Exception e)
        {
            return ResponseEntity.build(100, "无法查询到数据");
        }
    }

    @ApiOperation("根据id查询到对象信息")
    @GetMapping("/queryDrugApplyInfoById")
    @ResponseBody
    public ResponseEntity<DrugApply> queryDrugApplyInfoById(HttpServletRequest request, String id)
    {
        String custId = C.parseStr(request.getSession().getAttribute("custId"));
        try{
            if (C.checkNull(id))
                throw new Exception("未传入id");

            return ResponseEntity.buildSuccess(drugApplyService.queryInfoById(id));
        }
        catch (Exception e)
        {
            return ResponseEntity.build(100, "无法查询到数据");
        }
    }

    @ApiOperation("删除领药对象信息通过id")
    @GetMapping("/deleteDrugApplyInfoById")
    @ResponseBody
    public ResponseEntity<DrugApply> deleteDrugApplyInfoById(String id)
    {
        try{
            if (C.checkNull(id))
                throw new Exception("未传入id");

            return ResponseEntity.buildSuccess(drugApplyService.deleteInfoById(id));
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
    @ApiOperation("保存设备申请")
    @PostMapping("/saveEquipmentApply")
    public ResponseEntity<?> saveEquipmentApply(HttpServletRequest request, EquipmentApply equipmentApply) {
        try{
            String custId = C.parseStr(request.getSession().getAttribute("custId"));
            equipmentApply.setCustId(custId);
            return ResponseEntity.buildSuccess(equipmentApplyService.saveInfo(equipmentApply));
        }
        catch (Exception e)
        {
            return ResponseEntity.build(100, "无法保存数据");
        }
    }
    @ApiOperation("初始化设备申请数据")
    @GetMapping("/queryEquipmentApplyInitData")
    public ResponseEntity queryEquipmentApplyInitData(HttpServletRequest request)
    {
        String custId = C.parseStr(request.getSession().getAttribute("custId"));
        try{
            if (C.checkNull(custId))
                throw new Exception("未传入id");
            return ResponseEntity.buildSuccess(equipmentApplyService.queryListInitData(custId));
        }
        catch (Exception e)
        {
            return ResponseEntity.build(100, "无法查询到数据");
        }
    }

    @ApiOperation("根据养户id查询到投苗记录列表")
    @GetMapping("/queryEquipmentApplyRecordList")
    public ResponseEntity<?> queryEquipmentApplyRecordList(HttpServletRequest request)
    {
        String custId = C.parseStr(request.getSession().getAttribute("custId"));
        try{
            if (C.checkNull(custId))
                throw new Exception("未传入id");

            List<EquipmentApply> list = equipmentApplyService.queryInfoRecordList(custId);
            PageInfo page = new PageInfo(list);
            return ResponseEntity.buildSuccess(page);
        }
        catch (Exception e)
        {
            return ResponseEntity.build(100, "无法查询到数据");
        }
    }

    @ApiOperation("根据id查询到对象信息")
    @GetMapping("/queryEquipmentApplyInfoById")
    @ResponseBody
    public ResponseEntity<EquipmentApply> queryEquipmentApplyInfoById(String id)
    {
        try{
            if (C.checkNull(id))
                throw new Exception("未传入id");

            return ResponseEntity.buildSuccess(equipmentApplyService.queryInfoById(id));
        }
        catch (Exception e)
        {
            return ResponseEntity.build(100, "无法查询到数据");
        }
    }

    @ApiOperation("删除设备对象信息通过id")
    @GetMapping("/deleteEquipmentApplyInfoById")
    @ResponseBody
    public ResponseEntity<?> deleteEquipmentApplyInfoById(String id)
    {
        try{
            if (C.checkNull(id))
                throw new Exception("未传入id");
            return ResponseEntity.buildSuccess(equipmentApplyService.deleteInfoById(id));
        }
        catch (Exception e)
        {
            return ResponseEntity.build(100, "无法查询到数据");
        }
    }

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
        ba.setId("xieliId") ;
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
        info.setAvaibleBalance(123.65) ;
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
