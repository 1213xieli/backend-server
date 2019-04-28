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
        //{"code":"0000","data":{"cflevel":"B","cfpigpen":"江苏省泗洪县归仁镇","cfraisestate":"0","cfraisestateText":"在养","cfwinternum":"600","fcell":"18251027402","fkhsj":"2017-06-01","fname":"苗甫","ftelno":"18279104590","id":"ziV1BzNNSpu6C18rZKydrMyeztQ=","manager":"王家建","servicedep":"洋河服务部"},"msg":"查询成功!"}
        return jsonStr;
    }
}
