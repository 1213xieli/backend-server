package com.zb.byb.service;

import com.zb.byb.entity.UserInfo;

public interface LoginService {
    /**
     * 登入绑定用户
     * @param userInfo 用户信息
     * @return
     */
    String login(UserInfo userInfo,String openId) throws Exception;

    /**
     * 注册开户
     * @param userInfo 用户信息
     * @return
     */
    String register(UserInfo userInfo,String openId) throws Exception;
}
