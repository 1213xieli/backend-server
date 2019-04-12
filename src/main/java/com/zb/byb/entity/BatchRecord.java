package com.zb.byb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("批次记录")
public class BatchRecord{
    @ApiModelProperty("批次号")
    private String batchId;
    @ApiModelProperty("品种")
    private String bread;
    @ApiModelProperty("投苗数")
    private String toumiaoAmount;
    @ApiModelProperty("投苗时间")
    private Date toumiaoDate;
    @ApiModelProperty("日龄")
    private Integer dayAge;
    @ApiModelProperty("管理员名称")
    private String mangerName;
    @ApiModelProperty("在养头数")
    private Integer inNum;
    @ApiModelProperty("死亡头数")
    private Integer dieNum;






}
