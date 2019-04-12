package com.zb.byb.service.impl;

import com.zb.byb.entity.UserInfo;
import com.zb.byb.service.LoginService;
import com.zb.byb.util.BackTransmitUtil;
import com.zb.byb.util.MethodName;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public String login(UserInfo userInfo,String openId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("data",userInfo);
        map.put("openId",openId);
        String data=JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_LOGIN_CUSTSTART);
        return jsonStr;
    }

    @Override
    public String register(UserInfo userInfo,String openId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("data",userInfo);
        map.put("openId",openId);
        String data=JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_SAVE_CUSTSTART);
        return jsonStr;
    }
}
