package com.zb.byb.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zb.byb.common.Commonconst;
import com.zb.byb.common.Func;
import com.zb.byb.config.EasConfig;
import com.zb.byb.config.EasSession;
import com.zb.byb.webService.WSCustWechatAppFacade.WSCustWechatAppFacadeSoapBindingStub;
import com.zb.byb.webService.WSCustWechatAppFacade.WSCustWechatAppFacadeSrvProxyServiceLocator;
import com.zb.byb.webService.client.WSWSAppDataDealFacadeSoapBindingStub;
import com.zb.byb.webService.client.WSWSAppDataDealFacadeSrvProxyServiceLocator;
import org.apache.axis.client.Call;
import org.apache.axis.message.SOAPHeaderElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;

/**
* @Function: 后台传输数据公共方法
* @Author: shaoys
* @Date: Created in 10:39 2019/5/14
**/
@Component
public class BackTransmitUtil {
    private static final Logger logger = LoggerFactory.getLogger(BackTransmitUtil.class);
    @Autowired
    private EasSession easSession;
    @Autowired
    private EasConfig easConfig;
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 公共调用方法，与后台交互数据
     * @param jsonData json格式数据
     * @param methodName 调用方法
     * @return 返回JSON格式数据
     * @throws Exception 异常
     */
    public String invokeFunc(String jsonData,String methodName) throws Exception
    {
        String sessionId = easSession.getSessionId();
        String result = invokeFuncWeChat(sessionId, jsonData, methodName);
        try {
            if ("1001".equals(getReturnCode(result))) {
                sessionId = easSession.getSessionIdDirect();
                result = invokeFuncWeChat(sessionId, jsonData, methodName);
                logger.info("再次获取result ：" + result + "   " + System.currentTimeMillis());
            }
            return result;
        } catch (Exception e) {
            logger.error("服务名：" + "WSCustWechatAppFacade" + "方法名：" + methodName + "; 异常信息：" + e.getMessage());
            throw new Exception("无法连接金蝶软件！");
        }
    }

    /* *
     * @description 公共调用方法，与后台交互数据
     * @author xieli
     * @date  11:18 2019/7/24
     * @param []
     * @return java.lang.String
     **/
    public String commonInvoke(String servicesName, String methodName, Object[] objData) throws Exception {
        String sessionId = easSession.getSessionId();
        if (Func.checkNullOrEmpty(servicesName) || Func.checkNullOrEmpty(methodName))
            throw new Exception("接口服务名称错误！");

        try {
            org.apache.axis.client.Service service = new org.apache.axis.client.Service();
            Call call = (Call) service.createCall();
            //设置地址
            call.setTargetEndpointAddress(easConfig.getCommonUrl() + "/" + servicesName);
            SOAPHeaderElement soapHead = new org.apache.axis.message.SOAPHeaderElement(Commonconst.EAS_LOGIN_NAMESPACE, Commonconst.SESSIONID, sessionId);
            call.addHeader(soapHead);

            //设置要执行的方法(以下两种方式都可以)
            call.setOperationName(methodName);
            return Func.parseStr(call.invoke(objData));
        } catch (Exception e) {
            logger.error("服务名：" + servicesName + "方法名：" + methodName + "; 异常信息：" + e.getMessage());
            throw new Exception("无法连接金蝶软件！");
        }
    }


    private String invokeFuncWeChat(String sessionId, String jsonData, String methodName) throws Exception {
        WSCustWechatAppFacadeSrvProxyServiceLocator locator2 = new WSCustWechatAppFacadeSrvProxyServiceLocator();
        URL url2 = new URL(easConfig.getTaskUrl());
        WSCustWechatAppFacadeSoapBindingStub soap2 = new WSCustWechatAppFacadeSoapBindingStub(url2, locator2);
        //设置头部
        soap2.setHeader(new SOAPHeaderElement(Commonconst.EAS_LOGIN_NAMESPACE, Commonconst.SESSIONID, sessionId));
        jsonData = jsonData.trim();
        jsonData = "{\"sessionId\":\"" + sessionId + "\"," + jsonData.substring(1);
        String result = soap2.bybHandler(methodName, jsonData);
        return result;
    }

    /**
     * 接口调用方法
     * @param jsonData
     * @param methodName
     * @return
     * @throws Exception
     */
    public String newInvokeFunc(String jsonData,String methodName) throws Exception {
        String sessionId = easSession.getSessionId();
        String result = invokeFuncBatch(sessionId, jsonData, methodName);
        if ("1001".equals(getReturnCode(result))) {
            //logger.info("######retry for session issue########");
            sessionId = easSession.getSessionIdDirect();
            result = invokeFuncBatch(sessionId, jsonData, methodName);
        }
        return result;
    }

    private String invokeFuncBatch(String sessionId, String jsonData, String methodName) throws Exception  {
        WSWSAppDataDealFacadeSrvProxyServiceLocator locator2 = new WSWSAppDataDealFacadeSrvProxyServiceLocator();
        URL url2 = new URL(easConfig.getBatchUrl());
        WSWSAppDataDealFacadeSoapBindingStub soap2 = new WSWSAppDataDealFacadeSoapBindingStub(url2, locator2);
        //设置头部
        soap2.setHeader(new SOAPHeaderElement(Commonconst.EAS_LOGIN_NAMESPACE, Commonconst.SESSIONID, sessionId));
        jsonData = jsonData.trim();
        jsonData = "{\"sessionId\":\"" + sessionId + "\"," + jsonData.substring(1);
        String result = soap2.getData(methodName, jsonData);
        return result;
    }

    private String getReturnCode(String json) {
        try {
            String code = objectMapper.readTree(json).get("code").asText();
            return code;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
