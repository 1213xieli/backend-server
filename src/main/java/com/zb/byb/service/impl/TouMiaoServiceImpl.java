package com.zb.byb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.byb.common.Commonconst;
import com.zb.byb.entity.TouMiao;
import com.zb.byb.service.MyInfoService;
import com.zb.byb.service.TouMiaoService;
import com.zb.byb.util.BackTransmitUtil;
import com.zb.byb.util.JsonPluginsUtil;
import com.zb.byb.util.MethodName;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：谢李
 */
@Service
public class TouMiaoServiceImpl implements TouMiaoService {
    @Autowired
    private MyInfoService myInfoService;

    @Override
    public String queryListByUser(String openId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("openId", openId);

        // 要传入数据进行转化
        String data= JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_QUERY_PIGINGAPPLY);
        System.out.println(jsonStr);
        return jsonStr;
    }

    @Override
    public boolean saveInfo(TouMiao info) throws Exception {
        String data = JSONObject.fromObject(info).toString();
        String jsonBackStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_SAVE_PIGINGAPPLY);
        System.out.println(jsonBackStr);
        return true;
    }

    @Override
    public TouMiao queryListInitData(String tokenId) throws Exception {

        String backData= myInfoService.viewMyInfo(Commonconst.OpenId);
//        JSONObject jsonObject=JSONObject.fromObject(backData);
        Map<String, String> map1 = JsonPluginsUtil.jsonToMap(backData);

        String yhid = map1.get("id");
        Map<String, Object> map = new HashMap<>();
        // 传一个养户id、openid
        map.put("openId", Commonconst.OpenId);
        map.put("custId", yhid);

        // 要传入数据进行转化
        String data= JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_VIEW_PIGINGAPPLY);
        return JsonPluginsUtil.jsonToBean(jsonData, TouMiao.class);
    }

    @Override
    public TouMiao queryInfoRecordList(String tokenId) throws Exception
    {
        Map<String, Object> map = new HashMap<>();
        map.put("tokenId", tokenId);

        // 要传入数据进行转化
        String data= JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_QUERY_PIGINGAPPLY);
        return JsonPluginsUtil.jsonToBean(jsonData, TouMiao.class);
    }
}
