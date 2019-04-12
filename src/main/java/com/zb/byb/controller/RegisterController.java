package com.zb.byb.controller;

import com.zb.byb.common.Constants;

import com.zb.byb.entity.UserInfo;
import com.zb.byb.service.LoginService;
import com.zb.byb.util.JDService;
import com.zb.byb.util.MethodName;
import com.zb.byb.util.RequestUtils;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 注册信息
 */
@RestController
@RequestMapping("/api/register")
public class RegisterController {
    private LoginService loginService;
    @ApiOperation("保存注册信息")
    @PostMapping("/save")
    public ResponseEntity<?> register(@RequestBody UserInfo userInfo, HttpServletRequest request) {
        String openId = RequestUtils.getCookieByName(request, Constants.OPEN_ID);
        try {
            return ResponseEntity.buildSuccess(loginService.register(userInfo,openId));
        } catch (Exception e) {
            return ResponseEntity.build(100, "注册失败");
        }

    }
    /*@ApiOperation("获取注册信息")
    @GetMapping("/list")
    public ResponseEntity<List<UserInfo>> getList(){
        ResponseEntity<List<UserInfo>>  ent=new ResponseEntity<>();
        return ent;
    }*/


}
