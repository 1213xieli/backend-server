package com.zb.byb.util;

import _1._0._0._127.ormrpc.services.WSCustWechatAppFacade.WSCustWechatAppFacadeSoapBindingStub;
import _1._0._0._127.ormrpc.services.WSCustWechatAppFacade.WSCustWechatAppFacadeSrvProxyServiceLocator;
import _35._91._168._192.ormrpc.services.EASLogin.EASLoginProxyServiceLocator;
import _35._91._168._192.ormrpc.services.EASLogin.EASLoginSoapBindingStub;
import client.WSContext;
import com.zb.byb.common.C;
import org.apache.axis.message.SOAPHeaderElement;

import java.net.URL;

/**
 * 金碟公共调用服务
 */
public class JDService {
    /**
     * 用户登录数据获取sessionId
     * @return 返回sessionId
     * @throws Exception 异常
     */
    public static String login() throws Exception {
        EASLoginProxyServiceLocator locator = new EASLoginProxyServiceLocator();
        URL url = new URL(Resource.URL_LOGIN_TEST);
        EASLoginSoapBindingStub soap = new EASLoginSoapBindingStub(url, locator);
        //设置头部
        soap.setHeader(new SOAPHeaderElement("http://login.webservice.bos.kingdee.com", "SessionId", ""));
        //获取sessionId
        WSContext ctx = soap.login("zengneng", "", "eas", Resource.DC_NAME, "l2", 1);
        String sessionId = ctx.getSessionId();
        if (C.checkNullOrEmpty(sessionId))
            throw new Exception("登录失败，未获取到sessionId");

        return sessionId;
    }

    /**
     * session公共调用方法
     * @param sessionId sessionId
     * @param jsonData json格式数据
     * @param methodName 调用方法
     * @return 返回JSON格式数据
     * @throws Exception 异常
     */
    public static String bybService(String sessionId,String jsonData,String methodName) throws Exception {
        WSCustWechatAppFacadeSrvProxyServiceLocator locator2=new WSCustWechatAppFacadeSrvProxyServiceLocator();
        URL url2 = new URL(Resource.URL_TASK_TEST);
        WSCustWechatAppFacadeSoapBindingStub soap2 = new WSCustWechatAppFacadeSoapBindingStub(url2, locator2);
        //设置头部
        soap2.setHeader(new SOAPHeaderElement("http://login.webservice.bos.kingdee.com", "SessionId", sessionId));
        String bybHandler = soap2.bybHandler(methodName, jsonData);
        return bybHandler;
    }
}
