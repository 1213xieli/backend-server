package com.zb.byb.com.zb.byb;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.zb.byb.common.Constants;
import com.zb.byb.entity.UserInfo;
import com.zb.byb.util.*;
import net.sf.json.JSONObject;
import org.apache.axis.message.SOAPHeaderElement;

import _1._0._0._127.ormrpc.services.WSCustWechatAppFacade.WSCustWechatAppFacadeSoapBindingStub;
import _1._0._0._127.ormrpc.services.WSCustWechatAppFacade.WSCustWechatAppFacadeSrvProxyProxy;
import _1._0._0._127.ormrpc.services.WSCustWechatAppFacade.WSCustWechatAppFacadeSrvProxyServiceLocator;
import _35._91._168._192.ormrpc.services.EASLogin.EASLoginProxy;
import _35._91._168._192.ormrpc.services.EASLogin.EASLoginProxyProxy;
import _35._91._168._192.ormrpc.services.EASLogin.EASLoginProxyServiceLocator;
import _35._91._168._192.ormrpc.services.EASLogin.EASLoginSoapBindingStub;
import client.WSContext;
import org.springframework.beans.factory.annotation.Value;

public class TestMain {

	public static void main(String[] args) throws Exception {

		//登入获取sessionId
        String sessionId = JDService.login();
        //获取用户
        System.out.println("sessionId:"+sessionId);
        Map<String,Object> map=new HashMap();
        UserInfo userInfo=new UserInfo();
        userInfo.setIdentity("12346789");
        userInfo.setName("lisi");
        map.put("sessionId",sessionId);
        map.put("data",userInfo);
        map.put("openId","wwwsss_2223");
        String data=JSONObject.fromObject(map).toString();
		String bybHandler = JDService.bybService(sessionId,data,MethodName.METHOD_NAME_SAVE_CUSTSTART);

		System.out.println(bybHandler);

	}

}
