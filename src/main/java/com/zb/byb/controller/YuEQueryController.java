package com.zb.byb.controller;


import com.zb.byb.entity.YuE;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 余额信息
 */
@RestController
@RequestMapping("/api/yuEQuery")
public class YuEQueryController {
    @ApiOperation("获取余额信息")
    @GetMapping("/query")
    public ResponseEntity<YuE> yuEQuery(){
        return null;
    }
}
