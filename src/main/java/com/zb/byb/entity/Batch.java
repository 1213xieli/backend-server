package com.zb.byb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("批次")
public class Batch {
    @ApiModelProperty("用户id")
    private String id;
    @ApiModelProperty("批次名")
    private String name;
    @ApiModelProperty("批次号")
    private String number;


}
