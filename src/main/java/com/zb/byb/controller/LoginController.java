package com.zb.byb.controller;

import com.zb.byb.common.Constants;
import com.zb.byb.entity.UserInfo;
import com.zb.byb.service.LoginService;
import com.zb.byb.service.MyInfoService;
import com.zb.byb.util.JDService;
import com.zb.byb.util.RequestUtils;
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
        //sessionId
        String sessionId="";
        //获取openId,并存入session
        String openId= RequestUtils.getCookieByName(request, Constants.OPEN_ID);
        //为测试方便，先写死openId="oIWY8wahhrID4MLw68Ks3zIb1fq0"
        openId="oIWY8wW3wZp81jvTvvfdwSenfh40";
        session.setAttribute("openId",openId);
        try {//获取操作业务权限的sessionId
            sessionId=JDService.login();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //验证是否获取到useId;
        String userId=null;
        try {
            String backData=myInfoService.viewMyInfo(openId);
            JSONObject jsonObject1=JSONObject.fromObject(backData);
            JSONObject jsonObject=JSONObject.fromObject(jsonObject1.getString("data"));
            userId=jsonObject.getString("id");
            System.out.println("userId="+userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (userId!=null && userId.length()>0){
            //养户id存入session
            session.setAttribute("userId",userId);
            /*
            在养养户
             */
            session.setAttribute("userId","mRkwGN6DQgGNsONd+yMkV8yeztQ=");

        }else {//查不到说明用户没绑定微信，跳转到绑定页面
            return ResponseEntity.build(401,"该用户未绑定微信");
        }
        if(sessionId!=null && sessionId.length()>0){//成功,将sessionId保存
            session.setAttribute("sessionId",sessionId);
        }
        return ResponseEntity.buildSuccess("登入成功");
    }

    @ApiOperation("绑定")
    @PostMapping("/bind")
    public ResponseEntity<?> bind(@RequestBody(required = false) UserInfo userInfo,HttpServletRequest request){
       // session.setAttribute("userId","mRkwGN6DQgGNsONd+yMkV8yeztQ=");
        request.getSession().setAttribute("userId","mRkwGN6DQgGNsONd+yMkV8yeztQ=");
        try {
            //传人绑定信息,返回信息
            String data = loginService.bind(userInfo, (String) request.getSession().getAttribute("openId"));
            System.out.println("data="+data);
            return ResponseEntity.build(200,"绑定成功");
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
            return ResponseEntity.build(100,"解绑定失败");
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
