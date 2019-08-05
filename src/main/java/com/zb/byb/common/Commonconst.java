package com.zb.byb.common;

import java.io.File;

/**
 * 公共使用的常量类型
 * @date 2019/8/1 17:20
 * @author xieli
 */
public class Commonconst
{
    public static final int LOGIN_FAIL=991;
    public static final int FailStatus = 400;

    // 设置一个固定的openid,测试数据使用
    public static final String OpenId="oIWY8wahhrID4MLw68Ks3zIb1fq0";
    public static final String WX_Flag = "WECHAT";
    public static final String TempPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "static" + File.separator + "template" + File.separator;

    public static final String OPEN_ID = "wxopenid";
    public static final String SESSIONID = "SessionId";
    public static final String EAS_LOGIN_NAMESPACE = "http://login.webservice.bos.kingdee.com";
    public static final String BUSFACADE = "WSWebServiceBusFacade";
    public static final String BIOSAFETYAPPHANDLER = "BioSafetyAppHandler";
    public static final String SWAQ = "生物安全APP";
    public static final String DATA = "data";

    public static final String SLN_NAME = "eas";
    public static final String LANGUAGE = "l2";
    public static final int DB_TYPE = 1;

}
