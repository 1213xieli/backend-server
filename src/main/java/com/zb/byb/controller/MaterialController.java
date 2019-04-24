package com.zb.byb.controller;

import com.github.pagehelper.PageInfo;
import com.zb.byb.common.C;
import com.zb.byb.common.Commonconst;
import com.zb.byb.entity.*;
import com.zb.byb.service.DrugApplyService;
import com.zb.byb.service.EquipmentApplyService;
import com.zb.byb.service.FeedApplyService;
import com.zb.framework.common.entity.Message;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
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
    @PostMapping("/saveFeedApply")//@RequestBody(required = false)
    public ResponseEntity<?> feedApply(FeedApply feedApply,HttpServletRequest request) {
        String userId=(String) request.getSession().getAttribute("userId");
        try {
            if (C.checkNull(userId))
                throw new Exception("未传入养户id");
            String id= feedApplyService.feedApply(feedApply,userId);
            return ResponseEntity.buildSuccess(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.build(100, "无法保存数据");
        }
    }

    @ApiOperation("获取领料申请记录")
    @GetMapping("/feedList")
    public ResponseEntity<List<FeedApply>> getFeedList(FeedApply feedApply,HttpServletRequest request){
        String userId=(String) request.getSession().getAttribute("userId");
        System.out.println(userId);
        try {
            if (C.checkNull(userId))
                throw new Exception("未传入养户id");
            return ResponseEntity.buildSuccess(feedApplyService.queryFeedApply(feedApply,userId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.build(100, "无法查询到数据");
        }
    }

    @ApiOperation("查看领料申请信息")
    @GetMapping("/viewInfoById")
    public ResponseEntity<List<FeedApply>> viewInfobyId( String rcordId,HttpServletRequest request){
        String userId=(String) request.getSession().getAttribute("userId");
        System.out.println(userId);
        rcordId=rcordId.trim().replace(" ","+");
        System.out.println("rcordId="+rcordId);
        try {
            return ResponseEntity.buildSuccess(feedApplyService.viewFeedApply(rcordId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.build(100, "无法查询到数据");
        }
    }

    @ApiOperation("提交签名")
    @GetMapping("/signer")//@RequestBody(required = false)
    public ResponseEntity<List<FeedApply>> signer(String rcordId,List<FileEntry> signerList, HttpServletRequest request){
        FeedApply feedApply=new FeedApply() ;
        String userId=(String) request.getSession().getAttribute("userId");
        feedApply.setSignerList(signerList);
        //空格处理
        rcordId=rcordId.trim().replace(" ","+");
        feedApply.setRcordId(rcordId);
        try {
            String data=feedApplyService.singer(feedApply);
            if (!"0000".equals(JSONObject.fromObject(data).getString("code")))
                return ResponseEntity.build(100, "签名失败");
            return ResponseEntity.buildSuccess(data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.build(100, "签名失败");
        }
    }

    @ApiOperation("获取司机列表")
    @GetMapping("/getDriverList")//@RequestBody(required = false)
    public ResponseEntity<List<Driver>> getDriverList(Integer pageNumber,Integer pageSize,HttpServletRequest request){
        String custId=(String) request.getSession().getAttribute("userId");
        FeedApply feedApply=new FeedApply();
        feedApply.setPageNumber(pageNumber);
        feedApply.setPageSize(pageSize);
        try {
            List <Driver> driverList= feedApplyService.getDriverList(feedApply,custId);
            return ResponseEntity.buildSuccess(driverList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.build(100, "签名失败");
        }
    }

    @ApiOperation("获取饲料列表")
    @GetMapping("/getFeedList")//@RequestBody(required = false)
    public ResponseEntity<List<LiLiaoInfo>> getFeedList(String batchId ,Integer pageSize,Integer pageNumber,HttpServletRequest request){
        String custId=(String) request.getSession().getAttribute("userId");
        FeedApply feedApply=new FeedApply();
        feedApply.setPageSize(pageSize);
        feedApply.setPageNumber(pageNumber);
        feedApply.setBatchId(batchId);
        try {
            List <LiLiaoInfo> driverList= feedApplyService.getFeedList(feedApply);
            return ResponseEntity.buildSuccess(driverList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.build(100, "签名失败");
        }
    }





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
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
        }
    }

    @ApiOperation("根据批次id查询药品，支持模糊查询")
    @GetMapping("/queryMaterialInfoByBatchId")
    public ResponseEntity<MaterialInfo> queryMaterialInfoByBatchId(HttpServletRequest request, MaterialInfo queryInfo)
    {
        String custId = C.parseStr(request.getSession().getAttribute("custId"));
        try{
            if (C.checkNull(custId))
                throw new Exception("未传入养户id");

            queryInfo.setCustId(custId);
            List<MaterialInfo> list = drugApplyService.queryMaterialListByFuzzyKey(queryInfo);
            PageInfo page = new PageInfo(list);
            return ResponseEntity.buildSuccess(page);
        }
        catch (Exception e)
        {
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
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
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
        }
    }

    @ApiOperation("根据用户id查询到记录列表")
    @GetMapping("/queryDrugApplyRecordList")
    public ResponseEntity<?> queryDrugApplyRecordList(HttpServletRequest request, DrugApply queryInfo)
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
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
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
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
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
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
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
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
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
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
        }
    }

    @ApiOperation("根据养户id查询到设备记录列表")
    @GetMapping("/queryEquipmentApplyRecordList")
    public ResponseEntity<?> queryEquipmentApplyRecordList(HttpServletRequest request, EquipmentApply info)
    {
        String custId = C.parseStr(request.getSession().getAttribute("custId"));
        try{
            if (C.checkNull(custId))
                throw new Exception("未传入id");
            info.setCustId(custId);
            List<EquipmentApply> list = equipmentApplyService.queryInfoRecordList(info);
            PageInfo page = new PageInfo(list);
            return ResponseEntity.buildSuccess(page);
        }
        catch (Exception e)
        {
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
        }
    }

    @ApiOperation("查询委托人列表")
    @GetMapping("/queryEntrustList")
    public ResponseEntity<?> queryEntrustList(HttpServletRequest request, EquipmentApply info)
    {
        String custId = C.parseStr(request.getSession().getAttribute("custId"));
        try{
            if (C.checkNull(custId))
                throw new Exception("未传入id");

            info.setCustId(custId);
            List<EntrustInfo> list = equipmentApplyService.queryEntrustList(info);
            PageInfo page = new PageInfo(list);
            return ResponseEntity.buildSuccess(page);
        }
        catch (Exception e)
        {
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
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
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
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
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
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
