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
    @PostMapping("/saveToumiaoApply")
    @ResponseBody
    public ResponseEntity<?> saveToumiaoApply(TouMiao touMiao) {
        try{
            boolean flag = touMiaoService.saveInfo(touMiao);
            return ResponseEntity.buildSuccess(Commonconst.Back_Success);
        }
        catch (Exception e)
        {
            return ResponseEntity.build(100, "无法保存数据");
        }
    }

    @ApiOperation("初始化投苗数据")
    @GetMapping("/queryTouMiaoInitData")
    @ResponseBody
    public ResponseEntity<TouMiao> queryTouMiaoInitData(String tokenid)
    {
        try{
            return ResponseEntity.buildSuccess(touMiaoService.queryListInitData(tokenid));
        }
        catch (Exception e)
        {
            return ResponseEntity.build(100, "无法查询到数据");
        }
    }

    @ApiOperation("根据用户id查询到投苗记录")
    @GetMapping("/queryTouMiaoRecordList")
    @ResponseBody
    public ResponseEntity<TouMiao> queryTouMiaoRecordList(String tokenid)
    {
        try{
            return ResponseEntity.buildSuccess(touMiaoService.queryInfoRecordList(tokenid));
        }
        catch (Exception e)
        {
            return ResponseEntity.build(100, "无法查询到数据");
        }
    }


    /**
     * 前台传入，用户登录id
     * 返回 对象列表数据“TouMiao”
     * @return
     */
    @ApiOperation("获取投苗记录列表")
    @GetMapping("/getList")
    @ResponseBody
    public ResponseEntity<List<TouMiao>> getList(String id){
        try{
            return ResponseEntity.buildSuccess(touMiaoService.queryListByUser(id));
        }
        catch (Exception e)
        {
            return ResponseEntity.build(100, "无法查询到数据");
        }

    }
}
