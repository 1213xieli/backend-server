package com.zb.byb.service.impl;

import com.zb.byb.common.C;
import com.zb.byb.entity.DeathApply;
import com.zb.byb.entity.Driver;
import com.zb.byb.entity.FeedApply;
import com.zb.byb.entity.LiLiaoInfo;
import com.zb.byb.service.FeedApplyService;
import com.zb.byb.util.BackTransmitUtil;
import com.zb.byb.util.JsonPluginsUtil;
import com.zb.byb.util.MethodName;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FeedApplyServiceImpl implements FeedApplyService {

    @Override
    public String feedApply(FeedApply feedApply, String userId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("custId",userId);//养户id
        map.put("source","WECHAT");//微信
        map.put("data",feedApply);//参数QUERY_
        String data=JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_SAVE_PICKINGAPPLY);
        return JsonPluginsUtil.isRequestSuccessBackId(jsonStr);
    //{"code":"0000","id":"Va4AAAic0ajq+f3A","msg":"生成成功!"}
    }

    @Override
    public List<FeedApply> queryFeedApply(FeedApply feedApply, String userId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("custId",userId);//养户id
        map.put("source","WECHAT");//微信
        map.put("data",feedApply);//参数QUERY_
        String data=JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_QUERY_PICKINGAPPLY);
        System.out.println("领料申请,查询query方法---" + jsonStr);

        return JsonPluginsUtil.jsonTOList(jsonStr, FeedApply.class);
    }

    @Override
    public FeedApply viewFeedApply(String rcordId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("rcordId",rcordId);
        //map.put("custId",userId);//养户id
        map.put("source","WECHAT");//微信
        map.put("data",map1);//参数QUERY_
        String data=JSONObject.fromObject(map).toString();
        String jsonBack=BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_VIEW_PICKINGAPPLY);
        return JsonPluginsUtil.jsonToBean(jsonBack, FeedApply.class);
        //{"code":"0000","data":{"actEntrys":[],"applydate":"2019-04-23","batchId":"QOKuwU+4Q5uVQ5msWQNUVEMbbjA=","batchName":"001","billStatus":"保存","billStatusIndex":"10","curcnt":"0","curday":"0","custid":"vKYTT1wJTV+A7XdlVyduYMyeztQ=","number":"SLSQ1904230014","pickDetail":[{"bclybs":49,"id":"Va4AAAic0bkOXrKS","kg":1960}],"pigfarmerCode":"SYLWYH009","plandate":"2019-05-06","rcordId":"Va4AAAic0bjq+f3A","state":1,"type":0},"msg":"查询成功!"}
    }

    @Override
    public String singer(FeedApply feedApply) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("source","WECHAT");//微信
        map.put("data",feedApply);
        String data=JSONObject.fromObject(map).toString();
        String jsonBack=BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_SIGNER_PICKINGAPPLY);
        return jsonBack;
    }

    public String cancleFeedApply(String rcordId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("rcordId",rcordId);
        //map.put("custId",userId);//养户id
        map.put("source","WECHAT");//微信
        map.put("data",map1);//参数QUERY_
        String data=JSONObject.fromObject(map).toString();
        String jsonBack=BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_DELETE_PICKINGAPPLY);
        return jsonBack;
    }

    @Override
    public List<LiLiaoInfo> getFeedList(FeedApply feedApply) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("source","WECHAT");//微信
        map.put("data",feedApply);
        String data=JSONObject.fromObject(map).toString();
        String jsonBack=BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_QUERY_FEED);

        return null;
    }

    @Override
    public List<Driver> getDriverList(FeedApply feedApply, String custId) throws Exception {
        Map<String, Object> map = new HashMap<>();
       // Map<String, Object> param = new HashMap<>();

        custId="Va4AAAL/iSHMns7U";
        map.put("custId",custId);//养户id
        map.put("source","WECHAT");//微信
        map.put("data",feedApply);
        String data=JSONObject.fromObject(map).toString();
        String jsonBack=BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_QUERY_DRIVER);
        return JsonPluginsUtil.jsonTOList(jsonBack,Driver.class);
        //{"code":"0000","count":2,"data":[{"id":"Va4AAAidCKPDf2W2","isDefault":false,"name":"张三","number":"432243","phone":"423121"},{"id":"Va4AAAidCKTDf2W2","isDefault":false,"name":"李四","number":"2431412","phone":"1243124"}],"msg":"查询成功!"}

    }

    /*public  List<FeedApply> toList(String jsonStr,Class s){
        if (s == null || C.checkNullOrEmpty(jsonStr))
            return new ArrayList<>();
        if(!"0000".equals(JSONObject.fromObject(jsonStr).getString("code"))){
            return null;
        }
        // 通过Data 字段获取数据
        //JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        JSONArray jsonObject=JSONObject.fromObject(jsonStr).getJSONArray("data");
        if (C.checkNullOrEmpty(jsonObject))
            return null;
        List<FeedApply> list =com.alibaba.fastjson.JSONArray.parseArray(jsonObject.toString(),FeedApply.class);
        return list;
    }*/

}