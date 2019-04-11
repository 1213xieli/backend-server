package com.zb.byb.com.zb.byb;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.axis.message.SOAPHeaderElement;

import _1._0._0._127.ormrpc.services.WSCustWechatAppFacade.WSCustWechatAppFacadeSoapBindingStub;
import _1._0._0._127.ormrpc.services.WSCustWechatAppFacade.WSCustWechatAppFacadeSrvProxyProxy;
import _1._0._0._127.ormrpc.services.WSCustWechatAppFacade.WSCustWechatAppFacadeSrvProxyServiceLocator;
import _35._91._168._192.ormrpc.services.EASLogin.EASLoginProxy;
import _35._91._168._192.ormrpc.services.EASLogin.EASLoginProxyProxy;
import _35._91._168._192.ormrpc.services.EASLogin.EASLoginProxyServiceLocator;
import _35._91._168._192.ormrpc.services.EASLogin.EASLoginSoapBindingStub;
import client.WSContext;

public class TestMain {

	public static void main(String[] args) throws IOException {
		//登入获取sessionId
		EASLoginProxyServiceLocator locator = new EASLoginProxyServiceLocator();
		URL url = new URL("http://192.168.91.35:56898/ormrpc/services/EASLogin");
		EASLoginSoapBindingStub soap = new EASLoginSoapBindingStub(url, locator);
		//设置头部
		soap.setHeader(new SOAPHeaderElement("http://login.webservice.bos.kingdee.com", "SessionId", ""));
		//获取用户
		WSContext ctx = soap.login("zengneng","", "eas", "CS1116", "l2", 1);
		String sessionId = ctx.getSessionId();
		System.out.println("sessionId:"+sessionId);
		
		//操作
		WSCustWechatAppFacadeSrvProxyServiceLocator locator2=new WSCustWechatAppFacadeSrvProxyServiceLocator();
		URL url2 = new URL("http://192.168.91.35:56898/ormrpc/services/WSCustWechatAppFacade");
		WSCustWechatAppFacadeSoapBindingStub soap2 = new WSCustWechatAppFacadeSoapBindingStub(url2, locator2);
		//设置头部
		soap2.setHeader(new SOAPHeaderElement("http://login.webservice.bos.kingdee.com", "SessionId", sessionId));
		String bybHandler = soap2.bybHandler("xxx", "Sss");
		System.out.println(bybHandler);
		
		
	}

}
