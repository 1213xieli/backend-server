package com.zb.byb.service.impl;

import com.zb.byb.entity.Batch;
import com.zb.byb.entity.BatchRecord;
import com.zb.byb.service.BatchRecordService;
import com.zb.byb.util.BackTransmitUtil;
import com.zb.byb.util.JsonPluginsUtil;
import com.zb.byb.util.MethodName;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class BatchRecordServiceImpl implements BatchRecordService {

    @Override
    public BatchRecord viewBatchRecord(String batchId, String userId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        //batchId="QOKuwU+4Q5uVQ5msWQNUVEMbbjA=";
        params.put("rcordId",batchId);
        map.put("custId",userId);//养户id
        map.put("source","WECHAT");//微信
        map.put("data",params);//参数QUERY_
        String data=JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_VIEW_BATCHRECORD);
        return JsonPluginsUtil.jsonToBean(jsonStr,BatchRecord.class);
    }

    @Override
    public String getBatchList(String userId, Batch batch) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> param = new HashMap<>();
        //param.put("batchId",batchId);
        map.put("custId",userId);//养户id
        map.put("source","WECHAT");//微信
        map.put("data",batch);//参数
        String data= JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data,MethodName.METHOD_NAME_QUERY_ALL_BATCHI);
        return jsonStr;
    }

}
