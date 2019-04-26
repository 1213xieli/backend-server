package com.zb.byb.controller;

import com.zb.byb.entity.DeathApply;
import com.zb.byb.service.SaleNoticeService;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 销售通知
 */
@RestController
@RequestMapping("/api/saleNotice")
public class SaleNoticeController {
    @Autowired
    private SaleNoticeService saleNoticeService;
    @ApiOperation("销售通知")
    @GetMapping("/list")
    public ResponseEntity<?> unbind(HttpServletRequest request){
        try {
            //
            String data = saleNoticeService.getSaleNotice((String) request.getSession().getAttribute("userId"));
            return ResponseEntity.buildSuccess(data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.build(100,"失败");
        }
    }
}
