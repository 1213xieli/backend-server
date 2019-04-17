package com.zb.byb.service.impl;

import com.zb.byb.common.Commonconst;
import com.zb.byb.entity.DrugApply;
import com.zb.byb.service.DrugApplyService;
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
public class DrugApplyServiceImpl implements DrugApplyService {

    @Override
    public String saveInfo(DrugApply info) throws Exception {
        if (info == null)
        {
            throw new Exception("无法保存");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("custId", Commonconst.CustId);
        map.put("source", Commonconst.WX_Flag);
        map.put("data", info);
        String data = JSONObject.fromObject(map).toString();
        String jsonBackStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_SAVE_MEDICINEAPPLY);
        return JsonPluginsUtil.isRequestSuccessBackId(jsonBackStr);
    }

    @Override
    public DrugApply queryListInitData(String custId) throws Exception {
        DrugApply info = new DrugApply();
//        info
        Map<String, Object> map = new HashMap<>();
        map.put("source", Commonconst.WX_Flag);
        map.put("data", info);
        map.put("custId", custId);
        // 要传入数据进行转化
        String data = JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_QUERY_MEDICINEAPPLY);
        return JsonPluginsUtil.jsonToBean(jsonData, DrugApply.class);
    }

    @Override
    public DrugApply queryInfoRecordList(String custId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("custId", custId);
        map.put("source", Commonconst.WX_Flag);

        // 要传入数据进行转化
        String data = JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_QUERY_MEDICINEAPPLY);
        return JsonPluginsUtil.jsonToBean(jsonData, DrugApply.class);
    }

    @Override
    public DrugApply queryInfoById(String id) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("custId", Commonconst.CustId);
        map.put("source", Commonconst.WX_Flag);
        DrugApply queryInfo = new DrugApply();
        queryInfo.setRcordId(id);
        map.put("data", queryInfo);

        // 要传入数据进行转化
        String data = JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_VIEW_MEDICINEAPPLY);
        return JsonPluginsUtil.jsonToBean(jsonData, DrugApply.class);
    }

    @Override
    public DrugApply deleteInfoById(String recordId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("custId", Commonconst.CustId);
        map.put("source", Commonconst.WX_Flag);
        DrugApply queryInfo = new DrugApply();
        queryInfo.setRcordId(recordId);
        map.put("data", queryInfo);

        // 要传入数据进行转化
        String data = JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_DELETE_MEDICINEAPPLY);
        return JsonPluginsUtil.jsonToBean(jsonData, DrugApply.class);
    }
}
