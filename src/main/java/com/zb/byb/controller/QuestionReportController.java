package com.zb.byb.controller;


import com.github.pagehelper.PageInfo;
import com.zb.byb.common.Commonconst;
import com.zb.byb.entity.QuestionReportInfo;
import com.zb.byb.service.QuestionReportInfoService;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
            return ResponseEntity.build(0000, Commonconst.Back_Fail);
        }
    }

    @ApiOperation("常见问题查询列表，通过养户id")
    @GetMapping("/queryQuestionList")
    public ResponseEntity<?> queryQuestionList(String yhid) {
        try {
            List<QuestionReportInfo> list = questionReportInfoService.queryNormalQuestionList(yhid);
            PageInfo page = new PageInfo(list);
            return ResponseEntity.buildSuccess(page);
        }
        catch (Exception e)
        {
            return ResponseEntity.build(0000, Commonconst.Back_Fail);
        }
    }

    @ApiOperation("问题查询通过问题id")
    @GetMapping("/queryQuestionInfoById")
    public ResponseEntity<?> queryQuestionInfoById(String id) {
        try {
            return ResponseEntity.buildSuccess(questionReportInfoService.queryQuestionInfoById(id));
        }
        catch (Exception e)
        {
            return ResponseEntity.build(0000, Commonconst.Back_Fail);
        }
    }

    @ApiOperation("删除问题通过问题id")
    @GetMapping("/deleteQuestionInfoById")
    public ResponseEntity<?> deleteQuestionInfoById(String id) {
        try {
            return ResponseEntity.buildSuccess(questionReportInfoService.deleteQuestionInfoById(id));
        }
        catch (Exception e)
        {
            return ResponseEntity.build(0000, Commonconst.Back_Fail);
        }
    }
}
