package com.zb.byb.config;

import com.zb.byb.common.Commonconst;
import com.zb.byb.util.BackTransmitUtil;
import com.zb.byb.webService.EASLogin.EASLoginProxyServiceLocator;
import com.zb.byb.webService.EASLogin.EASLoginSoapBindingStub;
import com.zb.byb.webService.client.WSContext;
import org.apache.axis.message.SOAPHeaderElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;

/**
 * @author xieli
 * @date 2019/8/1 17:26
 * @description 从微信获取session配置
 */
@Component
public class EasSession {
    private static final Logger logger = LoggerFactory.getLogger(EasSession.class);
    @Autowired
    private EasConfig easConfig;
    @Autowired
    private BackTransmitUtil backTransmitUtil;
    private String sessionId;
    private long lastAccessTime = 0;

    public synchronized String getSessionId() throws Exception {
        if (sessionId == null) {
            sessionId = getSessionIdFromEas();
            lastAccessTime = System.currentTimeMillis();
        } else if ((System.currentTimeMillis() - lastAccessTime) > 10 * 60 * 1000) {
            sessionId = getSessionIdFromEas();
            lastAccessTime = System.currentTimeMillis();
        }
        return sessionId;
    }

    public synchronized String getSessionIdDirect() throws Exception {
        sessionId = getSessionIdFromEas();
        lastAccessTime = System.currentTimeMillis();
        return sessionId;
    }

    private String getSessionIdFromEas() throws Exception {
        EASLoginProxyServiceLocator locator = new EASLoginProxyServiceLocator();
        URL url = new URL(easConfig.getLoginUrl());
        EASLoginSoapBindingStub soap = new EASLoginSoapBindingStub(url, locator);
        //设置头部
        soap.setHeader(new SOAPHeaderElement(Commonconst.EAS_LOGIN_NAMESPACE, Commonconst.SESSIONID, ""));
        //获取sessionId
        WSContext ctx = soap.login(easConfig.getUserName(), easConfig.getPassword(), Commonconst.SLN_NAME, easConfig.getDcName(), Commonconst.LANGUAGE, Commonconst.DB_TYPE);
        String sessionId = ctx.getSessionId();
        logger.info("Get new sessionId from EAS: " + sessionId);
        return sessionId;
    }
}
