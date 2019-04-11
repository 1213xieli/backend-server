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
    @ApiModelProperty("结算申请id")
    private String id;
    @ApiModelProperty("结算申请批次号")
    private String batchId;
    @ApiModelProperty("进苗数量")
    private Integer jinMiaoAmount;
    @ApiModelProperty("进苗均重")
    private Double jinMiaoAvgWeight;
    @ApiModelProperty("进苗日期")
    private Date jinMiaoDate;
    @ApiModelProperty("上市正品数")
    private Integer goodAmount;
    @ApiModelProperty("上市正品均重")
    private Double goodAvgWeight;
    @ApiModelProperty("上市次品数")
    private Integer cipinAmount;
    @ApiModelProperty("上市次品均重")
    private Double cipinAvgWeight;
    @ApiModelProperty("上市均重")
    private Double AvgWeight;
    @ApiModelProperty("成活率")
    private Double aliveRate;
    @ApiModelProperty("料肉比")
    private Double meatRate;
    @ApiModelProperty("平均上市日龄")
    private Integer AvgDayAge;

}
