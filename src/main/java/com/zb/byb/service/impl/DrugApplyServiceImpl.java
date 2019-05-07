package com.zb.byb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.byb.common.C;
import com.zb.byb.common.Commonconst;
import com.zb.byb.entity.Drug;
import com.zb.byb.entity.DrugApply;
import com.zb.byb.entity.MaterialInfo;
import com.zb.byb.entity.QuestionReportInfo;
import com.zb.byb.service.DrugApplyService;
import com.zb.byb.util.BackTransmitUtil;
import com.zb.byb.util.JsonPluginsUtil;
import com.zb.byb.util.MethodName;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 领药申请
 * 作者：谢李
 */
@Service
public class DrugApplyServiceImpl implements DrugApplyService {

    @Override
    public String saveInfo(DrugApply info) throws Exception {
        if (info == null)
        {
            throw new Exception("药品未选择");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("custId", info.getCustId());
        map.put("source", Commonconst.WX_Flag);
        map.put("data", info);
        String data = JSONObject.fromObject(map).toString();
        String jsonBackStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_SAVE_MEDICINEAPPLY);
        System.out.println("领药申请,保存方法---" + jsonBackStr);
        return JsonPluginsUtil.isRequestSuccessBackId(jsonBackStr);
    }

    @Override
    public List<MaterialInfo> queryMaterialListByFuzzyKey(MaterialInfo queryInfo) throws Exception {
        if (C.checkNullOrEmpty(queryInfo.getBatchId()) || C.checkNullOrEmpty(queryInfo.getCustId()))
            throw  new Exception("未传入数据!");
        Map<String, Object> map = new HashMap<>();
        map.put("custId", queryInfo.getCustId());
        map.put("source", Commonconst.WX_Flag);
        Map paramMap = new HashMap();
        paramMap.put("batchId",queryInfo.getBatchId());
        paramMap.put("keyword", queryInfo.getFuzzyKey());
        map.put("data", paramMap);

        String data = JSON.toJSONString(map);
        String jsonBackStr = JsonPluginsUtil.getSuccessData(BackTransmitUtil.invokeFunc(data, MethodName.Method_Name_queryMaterial));
        jsonBackStr = JsonPluginsUtil.getSuccessData(jsonBackStr, "materialDetails");
        System.out.println("根据关键字+批次id进行模糊查询药品列表-------------" + jsonBackStr);
        return JSON.parseArray(jsonBackStr, MaterialInfo.class);
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
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_VIEW_MEDICINEAPPLY);
        System.out.println("领药申请,初始化单个view方法---" + jsonData);
        return JsonPluginsUtil.jsonToBean(jsonData, DrugApply.class);
    }

    @Override
    public List<DrugApply> queryInfoRecordList(String custId,DrugApply queryInfo ) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("custId", custId);
        map.put("source", Commonconst.WX_Flag);
        queryInfo.setCustId(custId);
        map.put("data", queryInfo);

        // 要传入数据进行转化
        String data = JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_QUERY_MEDICINEAPPLY);
        System.out.println("领药申请,查询query方法---" + jsonData);
        return JsonPluginsUtil.jsonToBeanList(jsonData, DrugApply.class);
    }

    @Override
    public DrugApply queryInfoById(String id) throws Exception {
        Map<String, Object> map = new HashMap<>();
//        map.put("custId", Commonconst.CustId);
        map.put("source", Commonconst.WX_Flag);
        DrugApply queryInfo = new DrugApply();
        queryInfo.setRcordId(id);
        map.put("data", queryInfo);

        // 要传入数据进行转化
        String data = JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_VIEW_MEDICINEAPPLY);
        System.out.println("领药申请,查询单个view方法---" + jsonData);
        return JsonPluginsUtil.jsonToBean(jsonData, DrugApply.class);
    }

    @Override
    public boolean deleteInfoById(String recordId) throws Exception {
        Map<String, Object> map = new HashMap<>();
//        map.put("custId", Commonconst.CustId);
        map.put("source", Commonconst.WX_Flag);
        DrugApply queryInfo = new DrugApply();
        queryInfo.setRcordId(recordId);
        map.put("data", queryInfo);

        // 要传入数据进行转化
        String data = JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_DELETE_MEDICINEAPPLY);
        System.out.println("领药申请,删除单个view方法---" + jsonData);
        return "0000".equals(JSONObject.fromObject(jsonData).getString("code")) ? true : false;
    }

    @Override
    public String singer(DrugApply drugApply) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("source", Commonconst.WX_Flag);
        map.put("data", drugApply);
        // 要传入数据进行转化
        String data = JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_SIGNER_MEDICINEAPPLY);
        System.out.println("领药申请,删除单个view方法---" + jsonData);
        return jsonData;
    }
}
