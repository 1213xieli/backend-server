package com.zb.byb.controller;

import com.zb.byb.common.Constants;

import com.zb.byb.entity.Introducer;
import com.zb.byb.entity.ServiceDept;
import com.zb.byb.entity.UserInfo;
import com.zb.byb.service.LoginService;
import com.zb.byb.util.JDService;
import com.zb.byb.util.MethodName;
import com.zb.byb.util.RequestUtils;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 注册信息
 */
@RestController
@RequestMapping("/api/register")
public class RegisterController {
    @Autowired
    private LoginService loginService;
    @ApiOperation("保存注册信息")
    @PostMapping("/save")
    public ResponseEntity<?> register(@RequestBody UserInfo userInfo, HttpServletRequest request) {
        String openId = RequestUtils.getCookieByName(request, Constants.OPEN_ID);
        System.out.println("openId="+openId);
        try {
            String backData=loginService.register(userInfo,openId);
            System.out.println("data="+backData);
            return ResponseEntity.buildSuccess(backData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.build(100, "开户失败");
        }

    }


    @ApiOperation("介绍人获取")
    @GetMapping("/getInstroduce")
    public ResponseEntity<List<Introducer>> getInstroduce(Introducer introducer){

        try {
            List<Introducer> list=loginService.getIntroducer(introducer);
            ResponseEntity<List<Introducer>>  ent=new ResponseEntity<>();
            ent.setData(list);
            return ent;
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.build(100,"查询不到数据");
        }
    }

    @ApiOperation("获取服务部列表")
    @GetMapping("/getDept")
    public ResponseEntity<?> getServiceDept(Integer pageNumber,Integer pageSize,String name, HttpServletRequest request) {
        ServiceDept serviceDept=new ServiceDept();
        serviceDept.setPageNumber(pageNumber);
        serviceDept.setPageSize(pageSize);
        serviceDept.setName(name);
        try {
            List<ServiceDept> deptList = loginService.getServiceDept(serviceDept);
            return ResponseEntity.buildSuccess(deptList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.build(100,"无数据");
        }

    }
}
