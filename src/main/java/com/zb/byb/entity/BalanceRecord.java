package com.zb.byb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ApiModel("结算记录")
public class BalanceRecord {
    @ApiModelProperty("结算记录id")
    private String id;
    @ApiModelProperty("结算批次号")
    private String batchId;
    @ApiModelProperty("品种")
    private String bread;
    @ApiModelProperty("投苗均重")
    private Double touMiaoAvgWeight;
    @ApiModelProperty("上市均重")
    private Double AvgWeight;
    @ApiModelProperty("成活率")
    private Double aliveRate;
    @ApiModelProperty("料肉比")
    private Double meatRate;
    @ApiModelProperty("正品率")
    private Double goodRate;
    @ApiModelProperty("结算明细")
    private BalanceDetail balanceDetail;



}
