package com.zb.byb.controller;

import com.zb.byb.common.Constants;
import com.zb.byb.entity.MyInfo;
import com.zb.byb.util.JDService;
import com.zb.byb.util.MethodName;
import com.zb.byb.util.RequestUtils;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/api/myInfo")
public class MyInfoController {

    @ApiOperation("查看我的信息")
    @PostMapping("/list")
    public ResponseEntity<?> queryMyInfo(@RequestBody MyInfo myInfo, HttpServletRequest request) {
        String openId = RequestUtils.getCookieByName(request, Constants.OPEN_ID);
        String sessionId=null;
        try {
             sessionId= JDService.login();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将数据传入map
        Map map=new HashMap();
        map.put("openId",openId);
        map.put("sessionId",sessionId);
        map.put("data",myInfo);
        String data= JSONObject.fromObject(map).toString();
        //获取返回的json字符串
        String dataBack=null;
        try {
            dataBack=JDService.bybService(sessionId,data, MethodName.METHOD_NAME_QUERY_BATCHRECORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<MyInfo> list = new ArrayList<>();
        JSONObject obj=JSONObject.fromObject(dataBack);
        list.add((MyInfo)JSONObject.toBean(obj,MyInfo.class));

        return ResponseEntity.buildSuccess(list);
    }

}
