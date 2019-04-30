package com.zb.byb.task;

import com.zb.byb.common.AccessToken;
import com.zb.byb.common.Ticket;
import com.zb.byb.common.WxCache;
import com.zb.byb.util.WeixinUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class WxJobs {
    private static final Logger logger = LoggerFactory.getLogger(WxJobs.class);
    @Value("${wx.appId}")
    private String appId;
    @Value("${wx.appsecret}")
    private String appsecret;
    //@Scheduled(fixedRate= 90 * 60 * 1000)
    public void refreshAccessToken(){
        AccessToken accesstoken = WeixinUtils.getAccessToken(appId, appsecret);
        if(null != accesstoken) {
            logger.info("获取accesstoken成功，accesstoken：" + accesstoken.getToken() + " 有效时间为" + accesstoken.getExpiresIn());
            WxCache.getInstance().setAccessToken(accesstoken);
            Ticket ticket = WeixinUtils.getJsApiTicket(accesstoken.getToken());
            if(null != ticket) {
                WxCache.getInstance().setTicket(ticket);
                logger.info("获取ticket成功，ticket：" + ticket.getTicket() + " 有效时间为" + ticket.getExpiresIn());
            }
        }
    }
}
