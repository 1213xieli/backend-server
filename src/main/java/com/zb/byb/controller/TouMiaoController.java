package com.zb.byb.controller;

import com.github.pagehelper.PageInfo;
import com.zb.byb.common.C;
import com.zb.byb.common.Commonconst;
import com.zb.byb.entity.TouMiao;
import com.zb.byb.service.TouMiaoService;
import com.zb.byb.util.BaseController;
import com.zb.framework.common.entity.Message;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 投苗申请
 */
@RestController
@RequestMapping("/api/toumiao")
public class TouMiaoController extends BaseController {
    @Autowired
    private TouMiaoService touMiaoService;

    @ApiOperation("保存投苗申请")
    @PostMapping("/saveToumiaoApply")
    @ResponseBody
    public ResponseEntity<?> saveToumiaoApply(HttpServletRequest request, HttpServletResponse response, TouMiao touMiao) {
        try{
            String custId = C.parseStr(request.getSession().getAttribute("custId"));
            touMiao.setCustId(custId);
            return ResponseEntity.buildSuccess(touMiaoService.saveInfo(touMiao));
        }
        catch (Exception e)
        {
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
        }
    }

    @ApiOperation("初始化投苗数据")
    @GetMapping("/queryTouMiaoInitData")
    @ResponseBody
    public ResponseEntity<TouMiao> queryTouMiaoInitData(HttpServletRequest request)
    {
        try{
            String custId = C.parseStr(request.getSession().getAttribute("custId"));
            return ResponseEntity.buildSuccess(touMiaoService.queryListInitData(custId));
        }
        catch (Exception e)
        {
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
        }
    }

    @ApiOperation("根据养户id查询到投苗记录")
    @GetMapping("/queryTouMiaoRecordList")
    @ResponseBody
    public ResponseEntity<?> queryTouMiaoRecordList(HttpServletRequest request, TouMiao touMiao)
    {
        try{
            String custId = C.parseStr(request.getSession().getAttribute("custId"));
//            String startDate = C.parseStr(request);

            List list = touMiaoService.queryInfoRecordList(custId, touMiao);
            PageInfo<TouMiao> info = new PageInfo(list);
            return ResponseEntity.build(200,new Message(), info);
        }
        catch (Exception e)
        {
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
        }
    }

    @ApiOperation("根据id查询到对象信息")
    @GetMapping("/queryInfoById")
    @ResponseBody
    public ResponseEntity<TouMiao> queryInfoById(String recordId)
    {
        try{
            if (C.checkNullOrEmpty(recordId))
                throw new Exception("未传入记录id");

            return ResponseEntity.buildSuccess(touMiaoService.queryInfoById(recordId));
        }
        catch (Exception e)
        {
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
        }
    }

}
