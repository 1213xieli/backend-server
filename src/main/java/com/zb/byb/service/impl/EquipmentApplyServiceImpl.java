package com.zb.byb.service.impl;

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
    public String queryListByUser(String userId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        // 要传入数据进行转化
        String data = JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_QUERY_PIGINGAPPLY);
        return jsonStr;
    }

    @Override
    public boolean saveInfo(EquipmentApply info) throws Exception {
        String data = JSONObject.fromObject(info).toString();
        BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_SAVE_PIGINGAPPLY);
        return true;
    }

    @Override
    public EquipmentApply queryListInitData(String tokenId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("tokenId", tokenId);
        // 要传入数据进行转化
        String data = JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_VIEW_PIGINGAPPLY);
        return JsonPluginsUtil.jsonToBean(jsonData, EquipmentApply.class);
    }

    @Override
    public EquipmentApply queryInfoRecordList(String tokenId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("tokenId", tokenId);

        // 要传入数据进行转化
        String data = JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_QUERY_PIGINGAPPLY);
        return JsonPluginsUtil.jsonToBean(jsonData, EquipmentApply.class);
    }
}
