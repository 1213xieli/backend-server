package com.zb.byb.service.impl;

import com.zb.byb.common.C;
import com.zb.byb.common.Commonconst;
import com.zb.byb.entity.Farmer;
import com.zb.byb.entity.TouMiao;
import com.zb.byb.service.MyInfoService;
import com.zb.byb.service.TouMiaoService;
import com.zb.byb.util.BackTransmitUtil;
import com.zb.byb.util.DateUtil;
import com.zb.byb.util.JsonPluginsUtil;
import com.zb.byb.util.MethodName;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 投苗
 * 作者：谢李
 */
@Service
public class TouMiaoServiceImpl implements TouMiaoService {
    @Autowired
    private MyInfoService myInfoService;

    @Override
    public String queryListByUser(String openId) throws Exception {
        return null;
    }

    @Override
    public String saveInfo(TouMiao info) throws Exception {
        if (info == null || C.checkNullOrEmpty(info.getCustId()) || info.getApplyDate() == null)
        {
            throw new Exception("无法保存");
        }

//        Farmer farmer = new Farmer();
//        farmer.setSize("32");
//        farmer.setName("xieli");
//        info.setFarmer(farmer);

        Map<String, Object> map = new HashMap<>();
        map.put("custId", info.getCustId());
        map.put("source", Commonconst.WX_Flag);
        map.put("data", info);

        String data = JSONObject.fromObject(map).toString();
        String jsonBackStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_SAVE_PIGINGAPPLY);
        System.out.println("投苗保存功能-----" + jsonBackStr);

        return JsonPluginsUtil.isRequestSuccessBackId(jsonBackStr);
    }

    @Override
    public TouMiao queryListInitData(String custId) throws Exception {

        Map<String, Object> map = new HashMap<>();
        map.put("custId", custId);
        map.put("source", Commonconst.WX_Flag);
        TouMiao queryInfo = new TouMiao();
        queryInfo.setCustId(custId);
        map.put("data", queryInfo);

        // 要传入数据进行转化
        String data= JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_VIEW_CUSTINFO);

        JSONObject jsonObject = JSONObject.fromObject(jsonData);
        String obj = jsonObject.getString(JsonPluginsUtil.Data);
        JSONObject custInfoStr = JSONObject.fromObject(obj);
        TouMiao info = new TouMiao();
        info.setCustName(custInfoStr.getString("fname"));
        info.setScope(custInfoStr.getString("cfwinternum"));

//        TouMiao info = JsonPluginsUtil.jsonToBean(jsonData, TouMiao.class);
//        if (info == null)
//            info = new TouMiao();

        return info;
    }

    @Override
    public TouMiao queryInfoById(String tmid) throws Exception {
        if (C.checkNullOrEmpty(tmid))
            return new TouMiao();

        Map<String, Object> map = new HashMap<>();
//        map.put("openId", Commonconst.OpenId);
//        map.put("custId", custId);
        // 微信入口获取数据，统一标识
        map.put("source", Commonconst.WX_Flag);
        TouMiao  queryInfo = new TouMiao();
        queryInfo.setRcordId(tmid);
        map.put("data", queryInfo);

        // 要传入数据进行转化
        String data = JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_VIEW_PIGINGAPPLY);
        System.out.println("投苗记录查询VIEW----" + jsonData);
        return JsonPluginsUtil.jsonToBean(jsonData, TouMiao.class);
    }

    @Override
    public List<TouMiao> queryInfoRecordList(String custId, TouMiao info) throws Exception
    {
        if (C.checkNullOrEmpty(custId))
            return new ArrayList<>();

        Map<String, Object> map = new HashMap<>();
        TouMiao queryInfo = new TouMiao();
        queryInfo.setCustId(custId);
        map.put("custId", custId);
        map.put("source", Commonconst.WX_Flag);
        map.put("data", queryInfo);
        map.put("pageNumber","1");
        map.put("pageSize","1000");

        // 要传入数据进行转化
        String data= JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_QUERY_PIGINGAPPLY);
//        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_QUERY_BATCH);
//        String jsonData2 = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_QUERY_DRIVER);
//        String jsonData3 = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_QUERY_ENTRUST);

        System.out.println("投苗记录列表查询Query----" + jsonData);
        return JsonPluginsUtil.jsonToBeanList(jsonData, TouMiao.class);
    }
}
