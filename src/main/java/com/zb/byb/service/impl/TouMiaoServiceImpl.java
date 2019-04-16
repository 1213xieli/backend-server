package com.zb.byb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.byb.common.CommonFunc;
import com.zb.byb.common.Commonconst;
import com.zb.byb.entity.DataRecord;
import com.zb.byb.entity.Farmer;
import com.zb.byb.entity.TouMiao;
import com.zb.byb.service.MyInfoService;
import com.zb.byb.service.TouMiaoService;
import com.zb.byb.util.BackTransmitUtil;
import com.zb.byb.util.JsonPluginsUtil;
import com.zb.byb.util.MethodName;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 作者：谢李
 */
@Service
public class TouMiaoServiceImpl implements TouMiaoService {
    @Autowired
    private MyInfoService myInfoService;

    @Override
    public String queryListByUser(String openId) throws Exception {
        return null;
//        Map<String, Object> map = new HashMap<>();
//        map.put("openId", openId);
//
//        // 要传入数据进行转化
//        String data= JSONObject.fromObject(map).toString();
//        String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_QUERY_PIGINGAPPLY);
//        System.out.println(jsonStr);
//        return jsonStr;
    }

    @Override
    public boolean saveInfo(TouMiao info) throws Exception {

//        TouMiao t = new TouMiao();
//        t.setId("xieli-test");
//        t.setApplyAmount(333);
//        t.setApplyDate(new Date());
//        t.setDisinfectFinished(true);
//        t.setElectricGeneratorReadied(true);
//        t.setActualAmount(333);
//        t.setTroughReadied(true);
//        t.setMaterialsReadied(true);
//        t.setPigTableReadied(true);
//        t.setActualAvgWeight(213.32);
//        t.setBarrelReadied(true);
//        t.setHeatLampReadied(true);
//        t.setIncubatorReadied(true);
//        t.setTemperatureControlReadied(true);

        Map<String, Object> map = new HashMap<>();
        map.put("openId", Commonconst.OpenId);
        map.put("custId", Commonconst.CustId);
        map.put("source", Commonconst.WX_Flag);
        map.put("data", info);

        String data = JSONObject.fromObject(map).toString();
        String jsonBackStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_SAVE_PIGINGAPPLY);
        System.out.println(jsonBackStr);
        return true;
    }

    @Override
    public TouMiao queryListInitData(String tokenId) throws Exception {

//        Farmer farmer = new Farmer();
//        farmer.setName("xieli");
//        farmer.setSize("12");
//        TouMiao t = new TouMiao();
//        t.setFarmer(farmer);
//        return t;

//        String backData= myInfoService.viewMyInfo(Commonconst.OpenId);
//        Map<String, String> map1 = JsonPluginsUtil.jsonToMap(backData);
//
//        String yhid = map1.get("id");
        Map<String, Object> map = new HashMap<>();
        map.put("openId", Commonconst.OpenId);
        map.put("custId", Commonconst.CustId);
        map.put("source", Commonconst.WX_Flag);
        map.put("data", new HashMap<>());

        // 要传入数据进行转化
        String data= JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_VIEW_PIGINGAPPLY);
        TouMiao info = JsonPluginsUtil.jsonToBean(jsonData, TouMiao.class);
        if (info == null)
            info = new TouMiao();

        return info;
    }

    @Override
    public TouMiao queryInfoById(String tmid) throws Exception {
//        TouMiao t = new TouMiao();
//        t.setId(tmid);
//        t.setApplyAmount(333);
//        t.setApplyDate(new Date());
//        t.setDisinfectFinished(true);
//        t.setElectricGeneratorReadied(true);
//        t.setActualAmount(333);
//        t.setTroughReadied(true);
//        t.setMaterialsReadied(true);
//        t.setPigTableReadied(true);
//        t.setActualAvgWeight(213.32);
//        t.setBarrelReadied(true);
//        t.setHeatLampReadied(true);
//        t.setIncubatorReadied(true);
//        t.setTemperatureControlReadied(true);

        if (CommonFunc.checkNullOrEmpty(tmid))
            return new TouMiao();

        Map<String, Object> map = new HashMap<>();
        map.put("openId", Commonconst.OpenId);
        map.put("custId", Commonconst.CustId);
        map.put("source", Commonconst.WX_Flag);

        TouMiao  queryInfo = new TouMiao();
        queryInfo.setId(tmid);
        map.put("data", queryInfo);

        // 要传入数据进行转化
        String data= JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_VIEW_PIGINGAPPLY);
        return JsonPluginsUtil.jsonToBean(jsonData, TouMiao.class);
    }

    @Override
    public List<TouMiao> queryInfoRecordList(String tokenId) throws Exception
    {
//        DataRecord record = new DataRecord();
//        record.setId("xieli");
//        record.setUserName("xieli用户名称");
//        record.setStartTime(new Date());
//        record.setRecordStatus(Commonconst.Record_Stauts.已完成.toString());
//
//        List list = new ArrayList();
//        list.add(record);
//        return list;

        Map<String, Object> map = new HashMap<>();
        TouMiao queryInfo = new TouMiao();
        queryInfo.setId(tokenId);
        map.put("openId", Commonconst.OpenId);
        map.put("custId", Commonconst.CustId);
        map.put("source", Commonconst.WX_Flag);
        map.put("data", queryInfo);

        // 要传入数据进行转化
        String data= JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_QUERY_PIGINGAPPLY);
        return JsonPluginsUtil.jsonToBeanList(jsonData, TouMiao.class);
    }
}
