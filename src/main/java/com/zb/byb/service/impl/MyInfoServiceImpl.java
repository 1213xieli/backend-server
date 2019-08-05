package com.zb.byb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zb.byb.service.MyInfoService;
import com.zb.byb.util.BackTransmitUtil;
import com.zb.byb.util.MethodName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MyInfoServiceImpl implements MyInfoService {
    @Autowired
    private BackTransmitUtil backTransmitUtil;
    @Override
    public String viewMyInfo(String openId) throws Exception {
        JSONObject map = new JSONObject();
        map.put("openId",openId);

        String jsonStr = backTransmitUtil.invokeFunc(map.toString(), MethodName.METHOD_NAME_VIEW_CUSTINFO);

        JSONObject jsonStrData = JSONObject.parseObject(jsonStr).getJSONObject("data");
        if (jsonStrData == null || jsonStrData.size() <= 0)
            throw new Exception("未从金蝶软件获取到用户信息");

        return jsonStr;
    }
}
