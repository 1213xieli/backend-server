package com.zb.byb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@ApiModel("饲喂记录")
public class FeedRecord {
    @ApiModelProperty("饲喂记录id")
    private String id;
    @ApiModelProperty("饲喂记录批次号")
    private String batchId;
     @ApiModelProperty("饲喂日期")
     private Date  feedDate;
     @ApiModelProperty("饲料信息")
     private Pigwash  pigwash;


}
