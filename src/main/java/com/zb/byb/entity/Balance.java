package com.zb.byb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@ApiModel("结算申请")
public class Balance {
    @ApiModelProperty("结算申请批次id")
    private String batchId;//批次id
    @ApiModelProperty("结算申请批次名")
    private String batchName;
    @ApiModelProperty("进苗数量")//缺少
    private Integer bthcnt;
    @ApiModelProperty("进苗均重")
    private Double avgKg;
    @ApiModelProperty("进苗日期")//缺少
    private String bizdate;
    @ApiModelProperty("上市正品数")//缺少
    private Integer cfsszps;
    @ApiModelProperty("上市正品均重")//缺少
    private Double zpjz;
    @ApiModelProperty("上市次品数")//缺少
    private Integer cfsscps;
    @ApiModelProperty("上市次品均重")//缺少
    private Double cpjz;
    @ApiModelProperty("上市均重")
    private Double shjz;
    @ApiModelProperty("成活率")//缺少
    private Double chl;
    @ApiModelProperty("料肉比")//缺少
    private Double lrb;
    @ApiModelProperty("平均上市日龄")
    private Integer ssrl;

    //{"code":"0000","data":{"avgKg":7.72,"batchId":"Va4AAAWIE6dSsdKc","batchName":"叶飞004","bizdate":"2018-10-11","bthcnt":1000,"cfsscps":0,"cfsszps":0,"chl":0,"cpjz":0,"ssrl":0,"zpjz":0},"msg":"获取成功!"}

}
