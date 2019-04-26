package com.zb.byb.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zb.byb.common.C;
import com.zb.byb.common.Commonconst;
import com.zb.byb.common.Constants;
import com.zb.byb.entity.Batch;
import com.zb.byb.entity.ServiceDept;
import com.zb.byb.entity.UserInfo;
import com.zb.byb.service.BatchRecordService;
import com.zb.byb.service.LoginService;
import com.zb.byb.service.MyInfoService;
import com.zb.byb.util.JDService;
import com.zb.byb.util.JsonPluginsUtil;
import com.zb.byb.util.RequestUtils;
import com.zb.framework.common.entity.Message;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private LoginService loginService;
    @Autowired
    private MyInfoService myInfoService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private BatchRecordService batchRecordService;


    @ApiOperation("登入")
    @GetMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest request) {

        HttpSession session=  request.getSession();

        String sessionId="";
        //获取openId,并存入session
        String openId= RequestUtils.getCookieByName(request, Constants.OPEN_ID);
        if (C.checkNullOrEmpty(openId))
            openId="8838939";//为测试方便，先写死openId*/
        session.setAttribute("openId",openId);

        try {
            //获取操作业务权限的sessionId
            session.setAttribute("sessionId",JDService.login());
            String json=myInfoService.viewMyInfo(openId);
            logger.info("-------通过openId获取的个人信息json串-------："+json);
            String myInfoStr = JsonPluginsUtil.getSuccessData(json);

            if (C.checkNullOrEmpty(myInfoStr))
                throw new Exception("登录失败，未获取个人信息");

            JSONObject jsonMap = JSONObject.parseObject(myInfoStr);

            String userId = jsonMap.getString("id");
            System.out.println("userId="+userId);

            if (C.checkNullOrEmpty(userId)){
                throw new Exception("登录失败");
            }
            //养户id存入session
            session.setAttribute("userId",userId);
            session.setAttribute("fname", jsonMap.getString("fname"));
            session.setAttribute("custId", userId);
            session.setAttribute("cfwinternum", jsonMap.getString("cfwinternum"));
            session.setAttribute("servicedep", jsonMap.getString("servicedep"));
            //  批次
            Batch batch = new Batch();
            batch.setPageNumber(1);
            batch.setPageSize(50);
            String str=batchRecordService.getBatchList(userId,batch);
            String batchIdlist=JSONObject.parseObject(str).getString("data");
            if (!C.checkNullOrEmpty(batchIdlist))
            {

                System.out.println("登录初始化批次方法-------"+batchIdlist);
                List<Batch> list=objectMapper.readValue(batchIdlist,List.class);
                session.setAttribute("pcList", list);
            }
            return ResponseEntity.buildSuccess("登入成功");
        } catch (Exception e) {
            Message message = new Message();
            message.setCode("991");
            message.setMessage(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.build(Commonconst.LOGIN_FAIL, message);
        }
    }

    @ApiOperation("绑定")
    @PostMapping("/bind")
    public ResponseEntity<?> bind(@RequestBody(required = false) UserInfo userInfo,HttpServletRequest request){
        String openId= RequestUtils.getCookieByName(request, Constants.OPEN_ID);
        logger.info("-------openId-------："+openId);
        String code=userInfo.getInvitationCode();//验证码
        String phone=userInfo.getTelNum();//电话号码
        logger.info("---验证码---："+code);
        logger.info("---电话号码---："+phone);
        if (!loginService.check(phone,code)){
           return ResponseEntity.build(100,"验证码错误");
        };
        logger.info("-----验证success---");

        try {
            //传人绑定信息,返回信息
            boolean id = loginService.bind(userInfo, openId);
            logger.info("-----绑定成功与否---："+id);
            System.out.println("id="+id);
            if(id){
                logger.info("-------------绑定成功-----------------");
                return ResponseEntity.build(200,"绑定成功");
            }
            return ResponseEntity.build(100,"不是养户,绑定失败");
        } catch (Exception e) {
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            logger.info("-----异常信息---："+e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
        }

    }

    @ApiOperation("解除绑定")
    @GetMapping("/unbind")
    public ResponseEntity<?> unbind(HttpServletRequest request){
        try {
            String data = loginService.unBind((String) request.getSession().getAttribute("userId"));
            return ResponseEntity.buildSuccess(data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.build(100,"解绑定失败");
        }
    }
    @ApiOperation("获取验证码")
    @GetMapping("/getCode")
    public ResponseEntity<?> getCode(String telNum, HttpServletRequest request) {
        String status = loginService.getCheckCode(telNum);
        return ResponseEntity.buildSuccess(status);
    }

}
