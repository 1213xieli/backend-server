package com.zb.byb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@ApiModel("投苗申请")
public class TouMiao {
    @ApiModelProperty("申请投苗ID")
    private String id;
    @ApiModelProperty("养户信息")
    private Farmer farmer;
    @ApiModelProperty("申请投苗日期")
    private Date applyDate;
    @ApiModelProperty("申请投苗数量")
    private Integer applyAmount;
    @ApiModelProperty("实际投苗日期")
    private Date actualDate;
    @ApiModelProperty("实际投苗数量")
    private Integer actualAmount;
    @ApiModelProperty("实际投苗均重")
    private Double actualAvgWeight;
    @ApiModelProperty("清洗消毒工作是否完成")
    private Boolean disinfectFinished;
    @ApiModelProperty("保温箱、屋中屋是否准备")
    private Boolean incubatorReadied;
    @ApiModelProperty("保温灯是否准备")
    private Boolean heatLampReadied;
    @ApiModelProperty("发电机是否正常使用")
    private Boolean electricGeneratorReadied;
    @ApiModelProperty("圆盘料槽是否准备")
    private Boolean troughReadied;
    @ApiModelProperty("物资准备")
    private Boolean materialsReadied;
    @ApiModelProperty("是否准备加药桶")
    private Boolean barrelReadied;
    @ApiModelProperty("上猪台是否准备")
    private Boolean pigTableReadied;
    @ApiModelProperty("上猪台是否准备")
    private Boolean temperatureControlReadied;
}
