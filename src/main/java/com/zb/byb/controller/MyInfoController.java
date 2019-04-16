package com.zb.byb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zb.byb.common.Constants;
import com.zb.byb.entity.MyInfo;
import com.zb.byb.service.MyInfoService;
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

@RestController
@RequestMapping("/api/myInfo")
public class MyInfoController {
    @Autowired
    private MyInfoService myInfoService;
    @Autowired
    private ObjectMapper objectMapper;

    @ApiOperation("查看我的信息")
    @GetMapping("/list")
    public ResponseEntity<MyInfo> queryMyInfo(MyInfo myInfo,HttpServletRequest request) {
        //获取openid
        String openId = RequestUtils.getCookieByName(request, Constants.OPEN_ID);
        openId="oIWY8wahhrID4MLw68Ks3zIb1fq0";
        try {

            JSONObject jsonObject1 = JSONObject.fromObject(myInfoService.viewMyInfo(openId));
            System.out.println(jsonObject1.toString());
            JSONObject jsonObject=JSONObject.fromObject(jsonObject1.getString("data"));
            //赋值
            myInfo.setId(jsonObject.getString("id"));
            myInfo.setDept(jsonObject.getString("servicedep"));
            myInfo.setEntrustedIdentity("");//被委托人身份证
            myInfo.setEntrustedName("");//被委托人姓名
            myInfo.setGrowUp("");//我的成长
            myInfo.setManager(jsonObject.getString("manager"));
            myInfo.setManagerTelNum(jsonObject.getString("fcell"));
            myInfo.setName(jsonObject.getString("fname"));
            myInfo.setPiggeryAddress(jsonObject.getString("cfpigpen"));//猪舍地址
            myInfo.setRegisterDate(jsonObject.getString("fkhsj"));
            myInfo.setStatus(jsonObject.getString("cfraisestate"));
            myInfo.setTelNum(jsonObject.getString("ftelno"));
            System.out.println(myInfo.getName());

           /* System.out.println("json="+myInfoService.viewMyInfo(openId));
            JSONObject result = JSONObject.fromObject(myInfoService.viewMyInfo(openId));
            String dataInfo = result.getString("data");
            myInfo = objectMapper.readValue(dataInfo, MyInfo.class);*/
            ResponseEntity<MyInfo> resp=new ResponseEntity<>();
            resp.setData(myInfo);
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.build(500, "内部服务器错误");
        }

    }

}
