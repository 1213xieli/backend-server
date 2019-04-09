package com.zb.byb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel("药品信息")
public class Drug implements Serializable {

    @ApiModelProperty("药名")
    private String name;
    @ApiModelProperty("数量")
    private Integer amount;
    @ApiModelProperty("单价")
    private Double price;
    @ApiModelProperty("金额")
    private Double payment;



}
