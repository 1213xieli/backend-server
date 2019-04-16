package com.zb.byb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel("饲料信息")
public class Pigwash implements Serializable {
    @ApiModelProperty("饲料id")
    private String feedId;
    @ApiModelProperty("饲料品种")
    private String feedName;
    @ApiModelProperty("数量（包数）")
    private Integer consumeQty;
    @ApiModelProperty("单价")
    private Double price;
    @ApiModelProperty("金额")
    private Double payment;
    @ApiModelProperty("已领")
    private Double received;
    @ApiModelProperty("定额")
    private Double quota;
}
