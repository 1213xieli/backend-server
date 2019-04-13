package com.zb.byb.service.impl;

import com.zb.byb.entity.DeathApply;
import com.zb.byb.service.DeathApplyService;
import com.zb.byb.util.BackTransmitUtil;
import com.zb.byb.util.MethodName;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class DeathApplyServiceImpl implements DeathApplyService  {
    @Override
    public String deathApply(DeathApply deathApply, String userId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("data",deathApply);
        map.put("userId",userId);
        String data= JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_SAVE_YZRHDEATH);
        return jsonStr;
    }

    @Override
    public String getDeathApplyRecord(String userId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("userId",userId);
        String data= JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_VIEW_YZRHDEATH);
        return jsonStr;
    }
}
