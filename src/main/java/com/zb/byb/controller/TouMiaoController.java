package com.zb.byb.controller;

import com.zb.byb.common.Commonconst;
import com.zb.byb.entity.TouMiao;
import com.zb.byb.entity.UserInfo;
import com.zb.byb.service.TouMiaoService;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 投苗申请
 */
@RestController
@RequestMapping("/api/toumiao")
public class TouMiaoController {
    @Autowired
    private TouMiaoService touMiaoService;

    @ApiOperation("保存投苗申请")
    @PostMapping("toumiaoApply")
    public ResponseEntity<?> toumiaoApply(@RequestBody TouMiao touMiao) {
        try{
            boolean flag = touMiaoService.saveInfo(touMiao);
            return ResponseEntity.buildSuccess(flag);
        }
        catch (Exception e)
        {
            return ResponseEntity.build(100, "无法保存数据");
        }

    }

    /**
     * 前台传入，用户登录id
     * 返回 对象列表数据“TouMiao”
     * @return
     */
    @ApiOperation("获取投苗记录列表")
    @GetMapping("/getList")
    public ResponseEntity<List<TouMiao>> getList(@RequestBody UserInfo userInfo){
        try{
            return ResponseEntity.buildSuccess(touMiaoService.queryListByUser(userInfo.getIdentity()));
        }
        catch (Exception e)
        {
            return ResponseEntity.build(100, "无法查询到数据");
        }

    }
}
