package com.zb.byb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@ApiModel("死亡申报")
public class DeathApply {
    @ApiModelProperty("死亡申报id")
    private String id;
    @ApiModelProperty("死亡申报批次号")
    private String batchId;
    @ApiModelProperty("死亡日期")
    private Date dieDate;
    @ApiModelProperty("死亡日龄")
    private Integer dayAge;
    @ApiModelProperty("申报死亡头数")
    private Integer ApplyDieNum;
    @ApiModelProperty("死亡均重")
    private Double avgWeight;
    @ApiModelProperty("实际死亡头数")
    private Integer actualDieNum;
    @ApiModelProperty("经度")
    private Double jing;
    @ApiModelProperty("维度")
    private Double wei;
    @ApiModelProperty("死猪照片")
    private String DiePic;

}
