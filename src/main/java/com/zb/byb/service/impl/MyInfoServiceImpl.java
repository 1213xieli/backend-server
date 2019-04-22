package com.zb.byb.service.impl;

import com.zb.byb.entity.MyInfo;
import com.zb.byb.service.MyInfoService;
import com.zb.byb.util.BackTransmitUtil;
import com.zb.byb.util.MethodName;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class MyInfoServiceImpl implements MyInfoService {

    @Override
    public String viewMyInfo(String openId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        map.put("openId",openId);
        map.put("data",map1);
        String data= JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_VIEW_CUSTINFO);
        return jsonStr;
    }
}
