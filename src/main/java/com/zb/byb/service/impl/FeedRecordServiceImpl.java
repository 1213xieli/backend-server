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
        map.put("data",feedRecord);
        map.put("userId",userId);
        String data= JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_SAVE_SUPPLIESBILL);
        return jsonStr;
    }

    @Override
    public String queryFeedRecord(String userId)throws Exception {
        Map<String, Object> map = new HashMap<>();

        map.put("userId",userId);
        String data= JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_VIEW_SUPPLIESBILL);
        return jsonStr;

    }
}
