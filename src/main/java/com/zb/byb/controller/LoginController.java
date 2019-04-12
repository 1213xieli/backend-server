package com.zb.byb.controller;

import com.zb.byb.common.Constants;
import com.zb.byb.entity.UserInfo;
import com.zb.byb.service.MyInfoService;
import com.zb.byb.util.HttpUtils;
import com.zb.byb.util.JDService;
import com.zb.byb.util.RequestUtils;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private MyInfoService myInfoService;
    @ApiOperation("登入")
    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest request,UserInfo userInfo) {
        //sessionId
        String sessionId="";
        //openId
        String openId= RequestUtils.getCookieByName(request, Constants.OPEN_ID);
        //写死openId
        openId="oIWY8wahhrID4MLw68Ks3zIb1fq0";
        try {
            sessionId=JDService.login();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //验证是否获取到useId;
        String userId=null;
        try {
            String backData=myInfoService.viewMyInfo(openId);
            JSONObject jsonObject=JSONObject.fromObject(backData);
            userId=jsonObject.getString("id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (userId!=null && userId.length()>0){
            //养户id存入session
            request.getSession().setAttribute("userId",userId);
        }else {
            return ResponseEntity.build(401,"该用户未注册");
        }
        if(sessionId!=null && sessionId.length()>0){//成功,将sessionId保存
            request.getSession().setAttribute("sessionId",sessionId);
        }
        //失败
        return ResponseEntity.buildSuccess("登入成功");
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
