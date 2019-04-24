package com.zb.byb.service.impl;

import com.zb.byb.entity.Introducer;
import com.zb.byb.entity.UserInfo;
import com.zb.byb.service.LoginService;
import com.zb.byb.util.BackTransmitUtil;
import com.zb.byb.util.MethodName;
import com.zb.framework.common.constant.Global;
import com.zb.framework.common.entity.Message;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
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
    public String getCheckCode(String mobile) {
        String token = getToken();
        if (token==null || token.length()==0){
            return null;
        }
        mobile = "18070505443";
        String url = "http://service.zhengbang.com/SERVICE/message/send-code/"+mobile;
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authentication",token);
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);
        try{
            ResponseEntity<String> r = template.exchange(url, HttpMethod.GET, requestEntity, String.class);
            return "ok";
        }catch (HttpClientErrorException e){
            e.printStackTrace();
            return "ok";
        }catch (Exception e1){
            return "no";
        }
    }

    @Override
    public boolean check(String phone, String code) {
        String token = getToken();
        if (token==null || token.length()==0){
            return false;
        }
        StringBuffer url = new StringBuffer("http://service.zhengbang.com/SERVICE/message/validate-code?phone="+phone+"&code="+code);
        RestTemplate restTemplate= new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authentication",token);
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> r= restTemplate.exchange(url.toString(), HttpMethod.POST, requestEntity, String.class);
        String jsonback=r.getBody();
        System.out.println("json="+jsonback);
        String code1 = JSONObject.fromObject(jsonback).getString("code");
        if("200".equals(code1)){
            return true;
        }

        return false;
    }

    private  String getToken(){
        RestTemplate restTemplate= new RestTemplate();
        StringBuilder url = new StringBuilder("http://service.zhengbang.com/AUTH/login?userName=YHFWH_ADMIN&password=123456");
        ResponseEntity<String> r= restTemplate.exchange(url.toString(), HttpMethod.POST, null, String.class);
        String jsonback=r.getBody();
        String token=JSONObject.fromObject(jsonback).getJSONObject("data").getString("Authentication");
        return token;
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
