package com.zb.byb.controller;


import com.zb.byb.common.Commonconst;
import com.zb.byb.entity.QuestionReportInfo;
import com.zb.byb.service.QuestionReportInfoService;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 问题反馈信息
 */
@RestController
@RequestMapping("/api/questionReport")
public class QuestionReportController {
    @Autowired
    private QuestionReportInfoService questionReportInfoService;

    @ApiOperation("保存问题反馈信息")
    @PostMapping("/saveQuestionReport")
    public ResponseEntity<?> saveQuestionReport(QuestionReportInfo info) {
        try {
            questionReportInfoService.saveQuestionReport(info);

            return ResponseEntity.buildSuccess(Commonconst.Back_Success);
        }
        catch (Exception e)
        {
            return ResponseEntity.build(0000, Commonconst.Back_Success);
        }
    }

    @ApiOperation("常见问题查询列表")
    @GetMapping("/queryNormalQuestionList")
    public ResponseEntity<?> queryNormalQuestionList(String id) {
        try {
            Map map = new HashMap();
            map.put("id", id);
            questionReportInfoService.queryNormalQuestionList(map);

            return ResponseEntity.buildSuccess(Commonconst.Back_Success);
        }
        catch (Exception e)
        {
            return ResponseEntity.build(0000, Commonconst.Back_Success);
        }
    }
}
