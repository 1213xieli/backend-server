package com.zb.byb.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel("领料申请")
public class FeedApply {
    @ApiModelProperty("领料申请id")
    private String id;
    @ApiModelProperty("领料申请批次号")
    private String batchId;
    @ApiModelProperty("计划拉料日期")
    private Date planDate;
    @ApiModelProperty("拉料司机姓名")
    private String driverName;
    @ApiModelProperty("拉料司机身份证号")
    private String identity;
    @ApiModelProperty("拉料司机车牌号")
    private String carNumber;
    @ApiModelProperty("申请类别：1：领料，0：退料")
    private String applyType;
    @ApiModelProperty("申请领料明细")
    private List<Pigwash> applyList;
    @ApiModelProperty("实际领料明细")
    private List<Pigwash>  actualList;

}
