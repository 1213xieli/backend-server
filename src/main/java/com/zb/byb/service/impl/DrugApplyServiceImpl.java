package com.zb.byb.service.impl;

import com.zb.byb.common.Commonconst;
import com.zb.byb.entity.Drug;
import com.zb.byb.entity.DrugApply;
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
            throw new Exception("无法保存");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("custId", info.getCustId());
        map.put("source", Commonconst.WX_Flag);
        info.setBatchId("Wif1/wprT0+mqAaT1bq6T0MbbjA=");
        Drug drug = new Drug();
        drug.setMaterialid("Va4AAAAI+8FECefw");
        drug.setSupplierId("Va4AAAAAPMg3xn38");
        drug.setNum(2.0);
        List list = new ArrayList();
        list.add(drug);
        info.setEntrys(list);
        map.put("data", info);
//        Map paramMap = new HashMap();
//        paramMap.put("batchId","Wif1/wprT0+mqAaT1bq6T0MbbjA=");
//        paramMap.put("keyword", "");
//        map.put("data", paramMap);
        String data = JSONObject.fromObject(map).toString();

//        String jsonBackStr = BackTransmitUtil.invokeFunc(data, "selectMaterialInfo");
//        System.out.println(jsonBackStr);
//        return null;

        String jsonBackStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_SAVE_MEDICINEAPPLY);
        System.out.println("领药申请,保存方法---" + jsonBackStr);
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
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_VIEW_MEDICINEAPPLY);
        System.out.println("领药申请,初始化单个view方法---" + jsonData);
        return JsonPluginsUtil.jsonToBean(jsonData, DrugApply.class);
    }

    @Override
    public List<DrugApply> queryInfoRecordList(String custId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("custId", custId);
        map.put("source", Commonconst.WX_Flag);
        DrugApply queryInfo = new DrugApply();
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
        return true;
    }
}
