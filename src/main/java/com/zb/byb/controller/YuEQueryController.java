package com.zb.byb.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zb.byb.entity.DealDetail;
import com.zb.byb.entity.YuE;
import com.zb.byb.service.YuEService;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * 余额信息
 */
@RestController
@RequestMapping("/api/yuEQuery")
public class YuEQueryController {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private YuEService yuEService;
    @ApiOperation("获取余额信息")
    @GetMapping("/query")
    public ResponseEntity<YuE> yuEQuery(HttpServletRequest request){
        try {
            String backData = yuEService.queryYuE((String) request.getSession().getAttribute("userId"));
            String data=JSONObject.fromObject(backData).getString("data");
            YuE yuE = objectMapper.readValue(data,YuE.class);
            ResponseEntity<YuE> resp=new ResponseEntity<>();

            resp.setData(yuE);
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.build(500);
        }

    }
}
