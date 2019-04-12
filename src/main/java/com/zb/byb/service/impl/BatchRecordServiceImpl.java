package com.zb.byb.service.impl;

import com.zb.byb.service.BatchRecordService;
import com.zb.byb.util.BackTransmitUtil;
import com.zb.byb.util.MethodName;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class BatchRecordServiceImpl implements BatchRecordService {

    @Override
    public String viewBatchRecord(String batchId, String openId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("openId",openId);
        map.put("data",batchId);
        String data= JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_QUERY_BATCHRECORD);
        return jsonStr;
    }
}
