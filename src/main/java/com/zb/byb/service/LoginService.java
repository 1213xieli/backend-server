package com.zb.byb.service;

import com.zb.byb.entity.Introducer;
import com.zb.byb.entity.UserInfo;

import java.util.List;

public interface LoginService {
    /**
     * 绑定
     * @param userInfo 用户信息
     * @param openId
     * @return
     * @throws Exception
     */
    String bind(UserInfo userInfo,String openId) throws Exception;

    String getCheckCode();

    /**
     * 解除绑定
     * @param openId 唯一id
     * @return
     * @throws Exception
     */
    String unBind(String openId)throws Exception;
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

    /**
     * 获取介绍人及所在服务部
     * @param introducer 介绍人
     * @return
     */
    List<Introducer> getIntroducer(Introducer introducer) throws Exception;
}
