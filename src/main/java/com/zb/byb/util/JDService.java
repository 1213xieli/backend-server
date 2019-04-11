package com.zb.byb.util;

import _1._0._0._127.ormrpc.services.WSCustWechatAppFacade.WSCustWechatAppFacadeSoapBindingStub;
import _1._0._0._127.ormrpc.services.WSCustWechatAppFacade.WSCustWechatAppFacadeSrvProxyServiceLocator;
import _35._91._168._192.ormrpc.services.EASLogin.EASLoginProxyServiceLocator;
import _35._91._168._192.ormrpc.services.EASLogin.EASLoginSoapBindingStub;
import client.WSContext;
import org.apache.axis.AxisFault;
import org.apache.axis.message.SOAPHeaderElement;

import java.net.MalformedURLException;
import java.net.URL;

public class JDService {
    public static String login() throws Exception {
        EASLoginProxyServiceLocator locator = new EASLoginProxyServiceLocator();
        URL url = new URL(Resource.URL_LOGIN_TEST);
        EASLoginSoapBindingStub soap = new EASLoginSoapBindingStub(url, locator);
        //设置头部
        soap.setHeader(new SOAPHeaderElement("http://login.webservice.bos.kingdee.com", "SessionId", ""));
        //获取sessionId
        WSContext ctx = soap.login("zengneng", "", "eas", "CS1116", "l2", 1);
        String sessionId = ctx.getSessionId();
        return sessionId;
    }
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
