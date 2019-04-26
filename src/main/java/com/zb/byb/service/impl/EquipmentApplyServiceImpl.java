package com.zb.byb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.byb.common.Commonconst;
import com.zb.byb.entity.EntrustInfo;
import com.zb.byb.entity.EquipmentApply;
import com.zb.byb.entity.EquipmentApply;
import com.zb.byb.service.EquipmentApplyService;
import com.zb.byb.util.BackTransmitUtil;
import com.zb.byb.util.JsonPluginsUtil;
import com.zb.byb.util.MethodName;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设备申请
 * 作者：谢李
 */
@Service
public class EquipmentApplyServiceImpl implements EquipmentApplyService {
    @Override
    public String saveInfo(EquipmentApply info) throws Exception {
        if (info == null) {
            throw new Exception("无法保存");
        }

        info.setEquipAmt("100");
//        info.setIsEntrust(true);
        info.setEntrustorId("vKYTT1wJTV+A7XdlVyduYMyeytQ=");
//        info.setEntrustorName("测试");
        info.setDescription("测试idcard");

        Map<String, Object> map = new HashMap<>();
        map.put("custId", info.getCustId());
        map.put("source", Commonconst.WX_Flag);
        map.put("data", info);
        String data = JSONObject.fromObject(map).toString();
        String jsonBackStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_SAVE_EQUIPMENTRECBILL);
        System.out.println("设备申请，保存方法----" + jsonBackStr);
        return JsonPluginsUtil.isRequestSuccessBackId(jsonBackStr);

//        String jsonBackStr = BackTransmitUtil.invokeFunc(data,"queryEntrust");
//        //{"code":"0000","count":1,"data":[{"id":"vKYTT1wJTV+A7XdlVys=","idcard":"测试idcard","isDefault":true,"name":"测试","phone":"测试phone"}],"msg":"查询成功!"}
//        System.out.println(jsonBackStr);
//        return null;

    }

    @Override
    public List<EntrustInfo> queryEntrustList(EquipmentApply info) throws Exception {
        if (info == null) {
            throw new Exception("无法查询");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("custId", info.getCustId());
        map.put("source", Commonconst.WX_Flag);
        map.put("data", info);
        String data = JSON.toJSONString(map);
        String jsonBackStr = BackTransmitUtil.invokeFunc(data,MethodName.Method_Name_queryEntrust);
        //{"code":"0000","count":1,"data":[{"id":"vKYTT1wJTV+A7XdlVys=","idcard":"测试idcard","isDefault":true,"name":"测试","phone":"测试phone"}],"msg":"查询成功!"}
        System.out.println("查询委托人列表----" + jsonBackStr);
        return JsonPluginsUtil.jsonToBeanList(jsonBackStr, EntrustInfo.class);
    }

    @Override
    public EquipmentApply queryListInitData(String custId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("custId", custId);
        map.put("source", Commonconst.WX_Flag);
        EquipmentApply info = new EquipmentApply();
        map.put("data", info);

        // 要传入数据进行转化
        String data = JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_COUNT_EQUIPMENTRECBILL);
        System.out.println("设备申请，金额初始化----" + jsonData);

        return JsonPluginsUtil.jsonToBean(jsonData, EquipmentApply.class);
    }

    @Override
    public List<EquipmentApply>  queryInfoRecordList(EquipmentApply info) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("custId", info.getCustId());
        map.put("source", Commonconst.WX_Flag);
        map.put("data", info);

        // 要传入数据进行转化
        String data = JSON.toJSONString(map);
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_QUERY_EQUIPMENTRECBILL);
        System.out.println("设备申请，查询query方法----" + jsonData);
        //{"code":"0000","count":2,"data":[{"billStatus":"保存","billStatusIndex":"10","bizDate":"2019-04-23","custId":"vKYTT1wJTV+A7XdlVyduYMyeztQ=","custName":"严春明","description":"测试idcard","entrys":[],"equipAmt":100,"isEntrust":true,"rcordId":"Va4AAAiccBCZvJQc","serviceId":"Va4AAAAZLk/4nGYi","serviceName":"龙湾服务部","state":1},{"billStatus":"保存","billStatusIndex":"10","bizDate":"2019-04-22","custId":"vKYTT1wJTV+A7XdlVyduYMyeztQ=","custName":"严春明","description":"测试idcard","entrys":[],"equipAmt":100,"isEntrust":false,"rcordId":"Va4AAAicYFaZvJQc","serviceId":"Va4AAAAZLk/4nGYi","serviceName":"龙湾服务部","state":1}],"msg":"查询成功!"}
        return JsonPluginsUtil.jsonToBeanList(jsonData, EquipmentApply.class);
    }

    @Override
    public EquipmentApply queryInfoById(String rcordId) throws Exception {
        Map<String, Object> map = new HashMap<>();
//        map.put("custId", Commonconst.CustId);
        map.put("source", Commonconst.WX_Flag);
        EquipmentApply info = new EquipmentApply();
        info.setRcordId(rcordId);
        map.put("data", info);

        // 要传入数据进行转化
        String data = JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_VIEW_EQUIPMENTRECBILL);
        System.out.println("设备申请，查询view方法----" + jsonData);
        return JsonPluginsUtil.jsonToBean(jsonData, EquipmentApply.class);
    }

    @Override
    public String deleteInfoById(String rcordId) throws Exception {
        Map<String, Object> map = new HashMap<>();
//        map.put("custId", Commonconst.CustId);
        map.put("source", Commonconst.WX_Flag);
        EquipmentApply info = new EquipmentApply();
        info.setRcordId(rcordId);
        map.put("data", info);

        // 要传入数据进行转化
        String data = JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_DELETE_EQUIPMENTRECBILL);
        return JsonPluginsUtil.isRequestSuccessBackId(jsonData);
    }
}
