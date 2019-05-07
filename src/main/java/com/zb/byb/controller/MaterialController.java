package com.zb.byb.controller;

import com.github.pagehelper.PageInfo;
import com.zb.byb.common.AccessToken;
import com.zb.byb.common.C;
import com.zb.byb.common.Commonconst;
import com.zb.byb.common.WxCache;
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
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.zb.byb.util.Image2Base64Util.getBase64FromInputStream;

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
    public ResponseEntity<?> feedApply(@RequestBody FeedApply feedApply,HttpServletRequest request) {
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

    @ApiOperation("提交领料签名")
    @PostMapping("/signerFeedApply")//@RequestBody(required = false)
    public ResponseEntity<List<FeedApply>> signer(String rcordId,@RequestBody FileEntry fileEntry, HttpServletRequest request){
        FeedApply feedApply=new FeedApply() ;
        String userId=(String) request.getSession().getAttribute("userId");
        List<FileEntry> signerList=new ArrayList<>();
        signerList.add(fileEntry);//存入实体
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
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
        }
    }

    @ApiOperation("取消领料申请")
    @GetMapping("/canclefeedApply")//@RequestBody(required = false)
    public ResponseEntity<List<FeedApply>> canclefeedApply(String rcordId, HttpServletRequest request){

        String userId=(String) request.getSession().getAttribute("userId");
        //空格处理
        try {
            String data=feedApplyService.cancleFeedApply(rcordId);
            return ResponseEntity.buildSuccess(data);
        } catch (Exception e) {
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
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
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
        }
    }

    @ApiOperation("获取饲料列表")
    @GetMapping("/getFeedList")//@RequestBody(required = false)
    public ResponseEntity<List<LiLiaoInfo>> getFeedList(String batchId ,HttpServletRequest request){
        String custId=(String) request.getSession().getAttribute("userId");
        FeedApply feedApply=new FeedApply();
        feedApply.setBatchId(batchId);
        try {
            List <LiLiaoInfo> liLiaoInfoList= feedApplyService.getFeedList(feedApply);
            return ResponseEntity.buildSuccess(liLiaoInfoList);
        } catch (Exception e) {
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
        }
    }


    /**
     * 我要领药
     * @param drugApply
     * @return
     */
    @ApiOperation("保存领药申请")
    @PostMapping("/saveDrugApply")
    public ResponseEntity<?> saveDrugApply(HttpServletRequest request, @RequestBody DrugApply drugApply,String mediaId) {
        String base64FromInputStream = getInputStream(mediaId);
        FileEntry fileEntry=new FileEntry();
        fileEntry.setImgContent(base64FromInputStream);
        fileEntry.setImgType(".amr");
        List<FileEntry> list=new ArrayList<>();
        list.add(fileEntry);
        drugApply.setWxRecordList(list);
        try{
            String custId = C.parseStr(request.getSession().getAttribute("custId"));
            drugApply.setCustId(custId);
            return ResponseEntity.buildSuccess(drugApplyService.saveInfo(drugApply));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Message message = new Message();
            message.setMessage("保存失败");
            return ResponseEntity.build(Commonconst.FailStatus,message,null);
        }
    }

    @ApiOperation("根据批次id查询药品选择列表，支持模糊查询")
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

    @ApiOperation("领药申请记录列表")
    @GetMapping("/queryDrugApplyRecordList")
    public ResponseEntity<?> queryDrugApplyRecordList(HttpServletRequest request, DrugApply queryInfo)
    {
        String custId = C.parseStr(request.getSession().getAttribute("custId"));
        try{
            if (C.checkNull(custId))
                throw new Exception("未传入养户id");

            List<DrugApply> list = drugApplyService.queryInfoRecordList(custId,queryInfo);
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

    @ApiOperation("查看领药申请详情")
    @GetMapping("/queryDrugApplyInfoById")
    @ResponseBody
    public ResponseEntity<DrugApply> queryDrugApplyInfoById(HttpServletRequest request, String rcordId)
    {
        String custId = C.parseStr(request.getSession().getAttribute("custId"));
        try{
            if (C.checkNull(rcordId))
                throw new Exception("未传入id");
            DrugApply drugApply = drugApplyService.queryInfoById(rcordId);
            return ResponseEntity.buildSuccess(drugApply);
        }
        catch (Exception e)
        {
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
        }
    }

    @ApiOperation("提交领药签名")
    @PostMapping("/signerDrugApply")//@RequestBody(required = false)
    public ResponseEntity<?> signerDrugApply(String rcordId,@RequestBody FileEntry fileEntry, HttpServletRequest request){
        DrugApply drugApply=new DrugApply() ;
        String userId=(String) request.getSession().getAttribute("userId");
        List<FileEntry> signerList=new ArrayList<>();
        signerList.add(fileEntry);//存入实体
        drugApply.setSignerList(signerList);
        //空格处理
        rcordId=rcordId.trim().replace(" ","+");
        drugApply.setRcordId(rcordId);
        try {
            String data=drugApplyService.singer(drugApply);
            if (!"0000".equals(JSONObject.fromObject(data).getString("code")))
                return ResponseEntity.build(100, "签名失败");
            return ResponseEntity.buildSuccess(data);
        } catch (Exception e) {
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
        custId="Va4AAACPobTMns7U";
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


    @ApiOperation("根据id查询到设备领用详情")
    @GetMapping("/queryEquipmentApplyInfoById")
    @ResponseBody
    public ResponseEntity<EquipmentApply> queryEquipmentApplyInfoById(String rcordId)
    {
        try{
            if (C.checkNull(rcordId))
                throw new Exception("未传入id");
            return ResponseEntity.buildSuccess(equipmentApplyService.queryInfoById(rcordId));
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


    @ApiOperation("搜索设备列表")
    @GetMapping("/searchEquimp")
    public ResponseEntity<?> searchEquimp(HttpServletRequest request, String keyword)
    {
        String custId = C.parseStr(request.getSession().getAttribute("custId"));
        try{
            if (C.checkNull(custId))
                throw new Exception("未传入id");
            List<Equipment> list = equipmentApplyService.searchEquipment(keyword);
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

    @ApiOperation("设备领用签名")
    @PostMapping("/signerEquipApply")
    public ResponseEntity<?> signerEquipApply(String rcordId,@RequestBody FileEntry fileEntry, HttpServletRequest request){
        String userId=(String) request.getSession().getAttribute("userId");
        EquipmentApply equipmentApply=new EquipmentApply();
        List<FileEntry> signerList=new ArrayList<>();
        signerList.add(fileEntry);//存入实体
        equipmentApply.setSignerList(signerList);
        equipmentApply.setRcordId(rcordId);
        try {
            String data=equipmentApplyService.signerEquipApply(equipmentApply);
            if (!"0000".equals(JSONObject.fromObject(data).getString("code")))
                return ResponseEntity.build(100, "签名失败");
            return ResponseEntity.buildSuccess(data);
        } catch (Exception e) {
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
        }
    }
        //去腾讯下载音频
        public static String getInputStream(String mediaId) {
            AccessToken accessToken = WxCache.getInstance().getAccessToken();
            InputStream is = null;
            try {
                String URL_DOWNLOAD_TEMP_MEDIA = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
                String url = URL_DOWNLOAD_TEMP_MEDIA.replace("ACCESS_TOKEN", accessToken.getToken()).replace("MEDIA_ID", mediaId);
                URL urlGet = new URL(url);
                HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
                http.setRequestMethod("GET"); // 必须是get方式请求
                http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                http.setDoOutput(true);
                http.setDoInput(true);
                System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
                System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
                http.connect();
                // 获取文件转化为byte流
                is = http.getInputStream();
                String base64FromInputStream = getBase64FromInputStream(is);
                System.out.println(base64FromInputStream);
                return base64FromInputStream;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
}
