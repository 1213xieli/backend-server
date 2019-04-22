package com.zb.byb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@ApiModel("交易明细")
public class DealDetail {
    @ApiModelProperty("业务日期")
    private Date dealDate;
    @ApiModelProperty("交易类型")
    private String dealType;
    @ApiModelProperty("交易金额")
    private Double dealMoney;
    @ApiModelProperty("余额")
    private Double yuE;
    @ApiModelProperty("当前页码")
    public int pageNumber = 1;
    @ApiModelProperty("每页大小")
    public int pageSize = 5;

}
