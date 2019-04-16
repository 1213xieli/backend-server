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
        map.put("custId",userId);//养户id
        map.put("source","WECHAT");//微信
        map.put("data",deathApply);
        String data= JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_SAVE_YZRHDEATH);
        return jsonStr;
    }

    @Override
    public String getDeathApplyRecord(String userId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> param = new HashMap<>();
        map.put("custId",userId);//养户id
        map.put("source","WECHAT");//微信
        map.put("data",param);//参数
        String data= JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_VIEW_SUPPLIESBILL);
        return jsonStr;
    }
}