package com.zb.byb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zb.byb.common.Commonconst;
import com.zb.byb.common.Func;
import com.zb.byb.entity.MyInfo;
import com.zb.byb.service.MyInfoService;
import com.zb.byb.util.HttpUtils;
import com.zb.byb.util.JsonPluginsUtil;
import com.zb.framework.common.entity.Message;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/myInfo")
public class MyInfoController {
    @Autowired
    private MyInfoService myInfoService;
    @Autowired
    private ObjectMapper objectMapper;

    @ApiOperation("查看我的信息")
    @GetMapping("/list")
    public ResponseEntity<MyInfo> queryMyInfo(HttpServletRequest request) {
        String openId = HttpUtils.getCookieByName(request, Commonconst.OPEN_ID);
        if (Func.checkNullOrEmpty(openId)) {
            openId=(String)request.getSession().getAttribute("openId");
        }
        try {
            if (Func.checkNull(openId)){
                throw new Exception("未获取openId");
            }
            String jsonback = myInfoService.viewMyInfo(openId);
            //"data":{"cflevel":"B","cfpigpen":"泗阳县王集镇人民路","cfraisestate":"0","cfraisestateText":"在养","cfwinternum":"1000","fcell":"18344895755","fkhsj":"2017-08-26","fname":"叶飞","ftelno":"18370533970","id":"Va4AAABJzw/Mns7U","manager":"王相","servicedep":"洋河服务部"}
            MyInfo myInfo= JsonPluginsUtil.jsonToBean(jsonback,MyInfo.class);
            //ResponseEntity responseEntity=new ResponseEntity();
            //responseEntity.setData(myInfo);
            return ResponseEntity.buildSuccess(myInfo);
        } catch (Exception e) {
        	e.printStackTrace();
            Message message = new Message();
            message.setCode(Func.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
        }

    }
}
