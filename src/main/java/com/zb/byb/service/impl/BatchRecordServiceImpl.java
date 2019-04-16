package com.zb.byb.service.impl;

import _1._0._0._127.ormrpc.services.WSCustWechatAppFacade.WSCustWechatAppFacadeSoapBindingStub;
import _1._0._0._127.ormrpc.services.WSCustWechatAppFacade.WSCustWechatAppFacadeSrvProxyServiceLocator;
import _35._91._168._192.ormrpc.services.EASLogin.EASLoginProxyServiceLocator;
import _35._91._168._192.ormrpc.services.EASLogin.EASLoginSoapBindingStub;
import client.WSContext;
import com.zb.byb.service.BatchRecordService;
import com.zb.byb.util.BackTransmitUtil;
import com.zb.byb.util.MethodName;
import com.zb.byb.util.Resource;
import net.sf.json.JSONObject;
import org.apache.axis.message.SOAPHeaderElement;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
@Service
public class BatchRecordServiceImpl implements BatchRecordService {

    @Override
    public String viewBatchRecord(String batchId, String userId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("openId",userId);
        map.put("data",batchId);
        String data= JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_QUERY_BATCHRECORD);
        return jsonStr;
    }

    @Override
    public String getBatchList(String userId, String bizType) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> param = new HashMap<>();
        //param.put("batchId",batchId);
        map.put("custId",userId);//养户id
        map.put("source","WECHAT");//微信
        map.put("data",param);//参数
        String data= JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data,"queryBatch");
        return jsonStr;
    }

}
