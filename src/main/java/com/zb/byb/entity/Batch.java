package com.zb.byb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("批次")
public class Batch {
    @ApiModelProperty("批次id")
    private String id;
    @ApiModelProperty("批次名")//展示
    private String name;
    @ApiModelProperty("批次号")
    private String number;
    @ApiModelProperty("当前页码")
    public int pageNumber = 1;
    @ApiModelProperty("每页大小")
    public int pageSize = 5;

}
