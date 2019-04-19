package com.zb.byb.controller;

import com.zb.byb.common.C;
import com.zb.byb.common.Constants;
import com.zb.byb.entity.UserInfo;
import com.zb.byb.service.LoginService;
import com.zb.byb.service.MyInfoService;
import com.zb.byb.util.JDService;
import com.zb.byb.util.JsonPluginsUtil;
import com.zb.byb.util.RequestUtils;
import com.zb.framework.common.entity.Message;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private MyInfoService myInfoService;
    @ApiOperation("登入")
    @GetMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest request) {
        HttpSession session=  request.getSession();

        String sessionId="";
        //获取openId,并存入session
        String openId= RequestUtils.getCookieByName(request, Constants.OPEN_ID);
        if (C.checkNullOrEmpty(openId))
            openId="oIWY8wahhrID4MLw68Ks3zIb1fq0";//为测试方便，先写死openId="oIWY8wahhrID4MLw68Ks3zIb1fq0"
        session.setAttribute("openId",openId);

        try {
            //获取操作业务权限的sessionId
            session.setAttribute("sessionId",JDService.login());
            String backData = JsonPluginsUtil.getSuccessData(myInfoService.viewMyInfo(openId));
            if (C.checkNullOrEmpty(backData))
                throw new Exception("登录失败，未获取个人信息");

            JSONObject jsonMap = JSONObject.fromObject(backData);
            String userId = jsonMap.getString("id");
            System.out.println("userId="+userId);

            if (C.checkNullOrEmpty(userId)){
                throw new Exception("登录失败");
            }
            //养户id存入session
            session.setAttribute("userId",userId);
            session.setAttribute("fname", jsonMap.getString("fname"));
            session.setAttribute("custId", userId);
            session.setAttribute("cfwinternum", C.parseStr(jsonMap.getString("cfwinternum")));

            return ResponseEntity.buildSuccess("登入成功");
        } catch (Exception e) {
            Message message = new Message();
            message.setCode("400");
            message.setMessage(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.build(400, message);
        }
//        return ResponseEntity.build(401,"该用户未绑定微信");
    }

    @ApiOperation("绑定")
    @PostMapping("/bind")
    public ResponseEntity<?> bind(@RequestBody(required = false) UserInfo userInfo,HttpServletRequest request){
        System.out.println("openId="+(String) request.getSession().getAttribute("openId"));
        String openId="aaassz";
        try {
            //传人绑定信息,返回信息
            //String data = loginService.bind(userInfo, (String) request.getSession().getAttribute("openId"));
            String data = loginService.bind(userInfo,openId );
            System.out.println("data="+data);
            return ResponseEntity.buildSuccess(data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.build(500,"服务器错误");
        }
    }

    @ApiOperation("解除绑定")
    @GetMapping("/unbind")
    public ResponseEntity<?> unbind(HttpServletRequest request){
        try {
            //
            String data = loginService.unBind((String) request.getSession().getAttribute("userId"));
            return ResponseEntity.buildSuccess(data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.build(500,"服务器错误");
        }
    }

    @ApiOperation("获取验证码")
    @PostMapping("/getCode")
    public ResponseEntity<?> getCode(@RequestBody UserInfo userInfo, HttpServletRequest request) {
        String sessionId="";
        //验证登入信息是否为养户
        Map map=new HashMap<>();
        map.put("data",userInfo);
        String json= JSONObject.fromObject(map).toString();
        String result=null;
        try {
            sessionId=JDService.login();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //失败
        if(result==null){
            return null;
        }
        //成功
        Random random = new Random();
        int checkCode = random.nextInt(9000)+1000;

        return ResponseEntity.buildSuccess(checkCode);
    }
}
