package com.zb.byb.util;

import _1._0._0._127.ormrpc.services.WSCustWechatAppFacade.WSCustWechatAppFacadeSoapBindingStub;
import _1._0._0._127.ormrpc.services.WSCustWechatAppFacade.WSCustWechatAppFacadeSrvProxyServiceLocator;
import _35._91._168._192.ormrpc.services.EASLogin.EASLoginProxyServiceLocator;
import _35._91._168._192.ormrpc.services.EASLogin.EASLoginSoapBindingStub;
import client.WSContext;
import client.WSWSAppDataDealFacadeSoapBindingStub;
import client.WSWSAppDataDealFacadeSrvProxyServiceLocator;
import org.apache.axis.message.SOAPHeaderElement;

import java.net.URL;

/**
 * 后台传输数据公共方法
 * 作者：谢李
 */
public class BackTransmitUtil {

    /**
     * 公共调用方法，与后台交互数据
     * @param jsonData json格式数据
     * @param methodName 调用方法
     * @return 返回JSON格式数据
     * @throws Exception 异常
     */
    public static String invokeFunc(String jsonData,String methodName) throws Exception
    {

        EASLoginProxyServiceLocator locator = new EASLoginProxyServiceLocator();
        URL url = new URL(Resource.URL_LOGIN_TEST);
        EASLoginSoapBindingStub soap = new EASLoginSoapBindingStub(url, locator);
        //设置头部
        soap.setHeader(new SOAPHeaderElement("http://login.webservice.bos.kingdee.com", "SessionId", ""));
        //获取sessionId
        WSContext ctx = soap.login("zengneng", "", "eas", "CS1116", "l2", 1);
        String sessionId = ctx.getSessionId();
        System.out.println(DateUtil.getCurrentDateFolder());

        WSCustWechatAppFacadeSrvProxyServiceLocator locator2 = new WSCustWechatAppFacadeSrvProxyServiceLocator();
        URL url2 = new URL(Resource.URL_TASK_TEST);
        WSCustWechatAppFacadeSoapBindingStub soap2 = new WSCustWechatAppFacadeSoapBindingStub(url2, locator2);
        //设置头部
        soap2.setHeader(new SOAPHeaderElement("http://login.webservice.bos.kingdee.com", "SessionId", sessionId));
        return soap2.bybHandler(methodName, jsonData);
    }

    /**
     * 接口调用方法
     * @param jsonData
     * @param methodName
     * @return
     * @throws Exception
     */
    public static String newInvokeFunc(String jsonData,String methodName,String url1) throws Exception {
        EASLoginProxyServiceLocator locator = new EASLoginProxyServiceLocator();
        URL url = new URL(Resource.URL_LOGIN_TEST);
        EASLoginSoapBindingStub soap = new EASLoginSoapBindingStub(url, locator);
        //设置头部
        soap.setHeader(new SOAPHeaderElement("http://login.webservice.bos.kingdee.com", "SessionId", ""));
        //获取sessionId
        WSContext ctx = soap.login("zengneng", "", "eas", "CS1116", "l2", 1);
        String sessionId = ctx.getSessionId();
        System.out.println(sessionId);

        WSWSAppDataDealFacadeSrvProxyServiceLocator locator2 = new WSWSAppDataDealFacadeSrvProxyServiceLocator();
        URL url2 = new URL(url1);
        WSWSAppDataDealFacadeSoapBindingStub soap2 = new WSWSAppDataDealFacadeSoapBindingStub(url2, locator2);
        //设置头部
        soap2.setHeader(new SOAPHeaderElement("http://login.webservice.bos.kingdee.com", "SessionId", sessionId));
        return soap2.getData(methodName,jsonData);
    }
}
