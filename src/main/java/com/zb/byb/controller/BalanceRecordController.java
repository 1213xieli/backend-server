package com.zb.byb.controller;

import com.zb.byb.common.C;
import com.zb.byb.common.Commonconst;
import com.zb.byb.entity.Balance;
import com.zb.byb.entity.BalanceRecord;

import com.zb.byb.entity.FileEntry;
import com.zb.byb.service.BalanceService;
import com.zb.framework.common.entity.Message;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/balanceRecord")
public class BalanceRecordController {
    @Autowired
    private BalanceService balanceService;

    @ApiOperation("根据结算申请表单带出")
    @GetMapping("/initInfoByBatchId")
    public ResponseEntity<?> initInfoByBatchId(String batchId,HttpServletRequest request){
        String userId = (String) request.getSession().getAttribute("userId");
        userId="Va4AAABJzw/Mns7U";
        try {
            Balance balance=balanceService.initInfoByBatchId(batchId,userId);
            return ResponseEntity.buildSuccess(balance);
        } catch (Exception e) {
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
        }
    }

    @ApiOperation("保存结算申请")
    @PostMapping("/apply")
    public ResponseEntity<?> balanceApply(@RequestBody Balance balance,HttpServletRequest request){
        String userId = (String) request.getSession().getAttribute("userId");
        userId="Va4AAABJzw/Mns7U";

        try {
            String id=balanceService.balanceApply(balance,userId);
            return ResponseEntity.buildSuccess(id);
        } catch (Exception e) {
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
        }
    }

    @ApiOperation("获取结算记录")
    @GetMapping("/list")
    public ResponseEntity<List<BalanceRecord>> getList(String starttime,String endtime,String state,Integer pageNumber,Integer pageSize,HttpServletRequest request){
        String userId = (String) request.getSession().getAttribute("userId");
        userId="Va4AAABJzw/Mns7U";
        BalanceRecord balanceRecord=new BalanceRecord();
        balanceRecord.setStarttime(starttime);
        balanceRecord.setEndtime(endtime);
        balanceRecord.setState(state);
        balanceRecord.setPageNumber(pageNumber);
        balanceRecord.setPageSize(pageSize);
        try {
            List<BalanceRecord> list=balanceService.getBalanceRecord(balanceRecord,userId);
            return ResponseEntity.buildSuccess(list);
        } catch (Exception e) {
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
        }

    }

    @ApiOperation("查看结算申请详情")
    @GetMapping("/viewInfoById")
    public ResponseEntity<List<BalanceRecord>> viewInfoById(String recordId,HttpServletRequest request){
        String userId = (String) request.getSession().getAttribute("userId");
        try {
            BalanceRecord balanceRecord=balanceService.viewBalanceRecord(recordId);
            return ResponseEntity.buildSuccess(balanceRecord);
        } catch (Exception e) {
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
        }

    }

    @ApiOperation("取消结算申请")
    @GetMapping("/cancleApply")
    public ResponseEntity<List<BalanceRecord>> cancleApply(String recordId,HttpServletRequest request){
        String userId = (String) request.getSession().getAttribute("userId");
        try {
            String id=balanceService.cancelApply(recordId);
            return ResponseEntity.buildSuccess(id);
        } catch (Exception e) {
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
        }

    }

    @ApiOperation("签名提交")
    @GetMapping("/signer")
    public ResponseEntity<List<BalanceRecord>> signer(String rcordId,FileEntry fileEntry, HttpServletRequest request){
        String userId = (String) request.getSession().getAttribute("userId");
        BalanceRecord balanceRecord=new BalanceRecord();
        balanceRecord.setFileEntry(fileEntry);
        balanceRecord.setRcordId(rcordId);
        try {
            String id=balanceService.singer(balanceRecord );
            return ResponseEntity.buildSuccess(id);
        } catch (Exception e) {
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
        }

    }


}
