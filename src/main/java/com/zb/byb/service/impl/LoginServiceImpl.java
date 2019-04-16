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


    /**
     * 绑定用户
     * @param userInfo 用户信息
     * @param openId 唯一id
     * @return
     * @throws Exception
     */
    @Override
    public String bind(UserInfo userInfo, String openId) throws Exception {
        Map<String, Object> map = new HashMap<>();

        map.put("data",userInfo);
        map.put("openId",openId);
        String data=JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_BIND_CUSTINFO);
        return jsonStr;
    }

    /**
     * 解绑
     * @param openId 唯一id
     * @return
     * @throws Exception
     */
    @Override
    public String unBind(String openId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("openId",openId);
        String data=JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_UNBIND_CUSTINFO);
        return jsonStr;
    }

    /**
     *登入
     * @param userInfo 用户信息
     * @param openId
     * @return
     * @throws Exception
     */
    @Override
    public String login(UserInfo userInfo,String openId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("data",userInfo);
        map.put("openId",openId);
        String data=JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_BIND_CUSTINFO);
        return jsonStr;
    }



    @Override
    public String register(UserInfo userInfo,String openId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("openId",openId);//微信id
        map.put("data",userInfo);//开户信息
        String data=JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_SAVE_CUSTSTART);
        return jsonStr;
    }

    @Override
    public String getIntroducer(String instroducerName) throws Exception{
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> param = new HashMap<>();
        param.put("instroducerName",instroducerName);//介绍人姓名
        map.put("source","WECHAT");//微信
        map.put("data",param);//
        String data=JSONObject.fromObject(map).toString();
        //String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_SAVE_CUSTSTART);
        return null;
    }
}
