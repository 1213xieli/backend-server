package com.zb.byb.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 作者：谢李
 */
@Data
public class DrugEntry {
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("applyDate")
    private String applyDate;
    @ApiModelProperty("materialId")
    private String materialId;
    @ApiModelProperty("materialName")
    private String materialName;
    @ApiModelProperty("qty")
    private String qty;
    @ApiModelProperty("price")
    private String price;
    @ApiModelProperty("单位")
    private String unit;
    @ApiModelProperty("amount")
    private String amount;
    @ApiModelProperty("isSelf")
    private String isSelf;
    @ApiModelProperty("entrustId")
    private String entrustId;
    @ApiModelProperty("entrustName")
    private String entrustName;
    @ApiModelProperty("model")
    private String model;
}
