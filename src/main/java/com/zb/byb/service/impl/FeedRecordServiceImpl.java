package com.zb.byb.service.impl;

import com.zb.byb.entity.FeedRecord;
import com.zb.byb.service.FeedRecordService;
import com.zb.byb.util.BackTransmitUtil;
import com.zb.byb.util.MethodName;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class FeedRecordServiceImpl implements FeedRecordService {
    @Override
    public String addFeedRecord(FeedRecord feedRecord, String userId) throws Exception{
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> param = new HashMap<>();
        map.put("custId",userId);//养户id
        map.put("source","WECHAT");//微信
        map.put("data",feedRecord);//
        String data= JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_SAVE_SUPPLIESBILL);
        return jsonStr;
    }

    @Override
    public String queryFeedRecord(String recordId,String userId)throws Exception {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> param = new HashMap<>();
        param.put("rcordId",recordId);//批次记录id/单据id
        //param.put("batchId",batchId);
        map.put("custId",userId);//养户id
        map.put("source","WECHAT");//微信
        map.put("data",param);//参数
        String data= JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_VIEW_SUPPLIESBILL);
        return jsonStr;
    }

    @Override
    public String queryFeedRecordList(String recordId, String userId) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> param = new HashMap<>();
        //param.put("recordId",recordId);
        //param.put("batchId",batchId);
        map.put("custId",userId);//养户id
        map.put("source","WECHAT");//微信
        map.put("data",param);//参数
        String data=JSONObject.fromObject(map).toString();
        try {
            return BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_QUERY_SUPPLIESBILL);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }
}
