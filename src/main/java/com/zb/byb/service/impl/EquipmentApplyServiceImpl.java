package com.zb.byb.service.impl;

import com.zb.byb.common.Commonconst;
import com.zb.byb.entity.EquipmentApply;
import com.zb.byb.entity.EquipmentApply;
import com.zb.byb.service.EquipmentApplyService;
import com.zb.byb.util.BackTransmitUtil;
import com.zb.byb.util.JsonPluginsUtil;
import com.zb.byb.util.MethodName;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：谢李
 */
@Service
public class EquipmentApplyServiceImpl implements EquipmentApplyService
{
    @Override
    public String saveInfo(EquipmentApply info) throws Exception {
        if (info == null)
        {
            throw new Exception("无法保存");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("custId", Commonconst.CustId);
        map.put("source", Commonconst.WX_Flag);
        map.put("data", info);
        String data = JSONObject.fromObject(map).toString();
        String jsonBackStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_SAVE_EQUIPMENTRECBILL);
        return JsonPluginsUtil.isRequestSuccessBackId(jsonBackStr);
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
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_VIEW_EQUIPMENTRECBILL);
        return JsonPluginsUtil.jsonToBean(jsonData, EquipmentApply.class);
    }

    @Override
    public EquipmentApply queryInfoRecordList(String custId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("custId", custId);
        map.put("source", Commonconst.WX_Flag);
        EquipmentApply info = new EquipmentApply();
        map.put("data", info);

        // 要传入数据进行转化
        String data = JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_QUERY_EQUIPMENTRECBILL);
        return JsonPluginsUtil.jsonToBean(jsonData, EquipmentApply.class);
    }

    @Override
    public EquipmentApply queryInfoById(String rcordId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("custId", Commonconst.CustId);
        map.put("source", Commonconst.WX_Flag);
        EquipmentApply info = new EquipmentApply();
        info.setRcordId(rcordId);
        map.put("data", info);

        // 要传入数据进行转化
        String data = JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_VIEW_EQUIPMENTRECBILL);
        return JsonPluginsUtil.jsonToBean(jsonData, EquipmentApply.class);
    }

    @Override
    public String deleteInfoById(String rcordId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("custId", Commonconst.CustId);
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
