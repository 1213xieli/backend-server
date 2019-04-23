package com.zb.byb.service.impl;

import com.zb.byb.entity.Introducer;
import com.zb.byb.entity.UserInfo;
import com.zb.byb.service.LoginService;
import com.zb.byb.util.BackTransmitUtil;
import com.zb.byb.util.MethodName;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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

    @Override
    public String getCheckCode() {

        return null;
    }

    private String getToken(){

    return "";
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
    //
    @Override
    public List<Introducer> getIntroducer(Introducer introducer) throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("source","WECHAT");//微信
        map.put("data",introducer);//
        String data=JSONObject.fromObject(map).toString();
        String jsonBack = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_INTRODUCER_CUSTSTART);
        if(!"0000".equals(JSONObject.fromObject(jsonBack).getString("code"))){
            return null;
        }
        JSONArray jsonObject=JSONObject.fromObject(jsonBack).getJSONArray("data");
        // {"code":"0000","data":[{"billState":"保存","billStateIndex":"10","feedDate":"2019-04-19","feedList":[{"batchId":"QOKuwU+4Q5uVQ5msWQNUVEMbbjA=","batchName":"001","columnQty":0,"consumeQty":112,"feedId":"Va4AAAAJLFb1CZfS","feedName":"保育料","id":"Va4AAAib16PHfMLr"}],"rcordId":"Va4AAAib16Kzx1nH","state":1}],"msg":"查询成功!"}
        //List<FeedRecord> list = JSONArray.toList(jsonObject, FeedRecord.class);
        List<Introducer> list= com.alibaba.fastjson.JSONArray.parseArray(jsonObject.toString(),Introducer.class);
        return list;
    }
}
