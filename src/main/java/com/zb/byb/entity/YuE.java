package com.zb.byb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("余额")
public class YuE {
    @ApiModelProperty("当前余额")
    private Double currentYuE;
    @ApiModelProperty("交易明细")
    private DealDetail dealDetail;

}
