package com.zb.byb.controller;


import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/questionReport")
public class QuestionReportController {
    @ApiOperation("保存问题反馈信息")
    @PostMapping("/save")
    public ResponseEntity<?> questionReport(@RequestBody String question) {

        return ResponseEntity.buildSuccess(null);
    }
}
