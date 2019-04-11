package com.zb.byb.controller;

import com.zb.byb.common.Constants;
import com.zb.byb.entity.UserInfo;
import com.zb.byb.util.RequestUtils;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/register")
public class RegisterController {
    @ApiOperation("保存注册信息")
    @PostMapping("/save")
    public ResponseEntity<?> register(@RequestBody UserInfo userInfo, HttpServletRequest request) {

        return ResponseEntity.buildSuccess(null);
    }
    @ApiOperation("获取注册信息")
    @GetMapping("/list")
    public ResponseEntity<List<UserInfo>> getList(){
        ResponseEntity<List<UserInfo>>  ent=new ResponseEntity<>();
        return ent;
    }
}
