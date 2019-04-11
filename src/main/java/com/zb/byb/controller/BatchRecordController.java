package com.zb.byb.controller;

import com.zb.byb.common.Constants;
import com.zb.byb.entity.BatchRecord;
import com.zb.byb.entity.DeathApply;
import com.zb.byb.util.HttpUtils;
import com.zb.byb.util.JDService;
import com.zb.byb.util.MethodName;
import com.zb.byb.util.RequestUtils;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 批次记录
 */
@RestController
@RequestMapping("/api/batchRecord")
public class BatchRecordController {
    @ApiOperation("获取批次记录")
    @GetMapping("/list")
    public ResponseEntity<List<BatchRecord>> getList(HttpServletRequest request,BatchRecord batchRecord){
        //获取openid
        String openId = RequestUtils.getCookieByName(request, Constants.OPEN_ID);
        //登入获取sessionId
        String sessionId=null;
        try {
            sessionId = JDService.login();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将数据传入map
        Map map=new HashMap();
        map.put("openId",openId);
        map.put("sessionId",sessionId);
        map.put("data",batchRecord);
        String data=JSONObject.fromObject(map).toString();
        //获取返回的json字符串
        String dataBack=null;
        try {
            dataBack=JDService.bybService(sessionId,data, MethodName.METHOD_NAME_QUERY_BATCHRECORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<BatchRecord> list = new ArrayList<>();
        JSONObject obj=JSONObject.fromObject(dataBack);
        list.add((BatchRecord)JSONObject.toBean(obj,BatchRecord.class));
        return ResponseEntity.buildSuccess(list);
    }
}
