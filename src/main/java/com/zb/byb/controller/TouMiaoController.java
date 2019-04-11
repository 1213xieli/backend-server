package com.zb.byb.controller;

import com.zb.byb.entity.TouMiao;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 投苗申请
 */
@RestController
@RequestMapping("/api/toumiao")
public class TouMiaoController {
    @ApiOperation("保存投苗申请")
    @PostMapping
    public ResponseEntity<?> toumiaoApply(@RequestBody TouMiao touMiao) {
        TouMiao info = new TouMiao();
        info.setId("id2323");
        info.setActualAmount(3232);
        info.setApplyDate(new Date());
        info.setActualAvgWeight(3232.43);
        info.setBarrelReadied(true);
        info.setPigTableReadied(true);
        info.setTroughReadied(true);
        info.setMaterialsReadied(true);

        return ResponseEntity.buildSuccess(info);
    }

    /**
     * 前台传入，用户登录id
     * 返回 对象列表数据“TouMiao”
     * @return
     */
    @ApiOperation("获取投苗记录列表")
    @GetMapping
    public ResponseEntity<List<TouMiao>> getList(){
        List<TouMiao> list = new ArrayList<>();
        TouMiao info = new TouMiao();
        info.setId("3213xieli");
        info.setMaterialsReadied(true);
        info.setTroughReadied(true);
        info.setActualDate(new Date());
        info.setActualAmount(3232);
        info.setElectricGeneratorReadied(true);
        info.setApplyAmount(3243543);
        info.setDisinfectFinished(true);
        list.add(info);
        return ResponseEntity.buildSuccess(list);
    }
}
