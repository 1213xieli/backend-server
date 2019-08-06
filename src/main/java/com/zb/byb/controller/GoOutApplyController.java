package com.zb.byb.controller;

import com.zb.byb.entity.GoOutApply;
import com.zb.byb.service.GoOutApplyService;
import com.zb.framework.common.constant.StatusCode;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: 周作为
 * @Date: 2019/7/15 09:46
 * @Description: 人员外出申请
 */
@Api(value = "人员外出申请模块")
@Slf4j
@RequestMapping({"/api/personGoOut"})
@RestController
public class GoOutApplyController {


    @Autowired
    private GoOutApplyService goOutApplyService;


    @PostMapping(value = "/addApply")
    @ApiOperation(value = "新增外出申请", notes = "新增外出申请")
    public ResponseEntity addGoOutApply(@RequestBody GoOutApply record, final HttpServletRequest request) {
        try {
            return ResponseEntity.buildSuccess(goOutApplyService.addGoOutApply(record, request));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.build(StatusCode.SERVER_ERROR, e.getMessage(), null);
        }
    }

    @PostMapping(value = "/getApplyById")
    @ApiOperation(value = "获取消毒单据根据id", notes = "获取消毒单据根据id")
    public ResponseEntity getApplyById(@RequestBody GoOutApply record, final HttpServletRequest request) {
        try {
            return ResponseEntity.build(StatusCode.OK, "", goOutApplyService.getApplyById(record, request));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.build(StatusCode.SERVER_ERROR, e.getMessage(), null);
        }
    }

    @GetMapping(value = "/queryClearStep")
    @ApiOperation(value = "A-生物安全-洗消步骤", notes = "A-生物安全-洗消步骤")
    public ResponseEntity queryClearStep(HttpServletRequest request) {
        try {
            return ResponseEntity.build(StatusCode.OK, "", goOutApplyService.queryClearStep(request));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.build(StatusCode.SERVER_ERROR, e.getMessage(), null);
        }
    }

    @PostMapping(value = "/getBiologySafeList")
    @ApiOperation(value = "获取生物安全列表", notes = "获取生物安全列表")
    public ResponseEntity getBiologySafeList(@RequestBody GoOutApply record, final HttpServletRequest request) {
        try {
            return ResponseEntity.build(StatusCode.OK, "", goOutApplyService.getBiologySafeList(record, request));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.build(StatusCode.SERVER_ERROR, e.getMessage(), null);
        }
    }

}
