package com.zb.byb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 对账单实体类
 * 作者：谢李
 */
@ApiModel("对账单实体类")
@Data
public class BillInfo {

    @ApiModelProperty("主键id")
    private String id;
    @ApiModelProperty("记录id")
    private String recordId;
    @ApiModelProperty("养户id")
    private String custId;
    @ApiModelProperty("养户名称")
    private String custName;
    @ApiModelProperty("批次")
    private String pc;
    @ApiModelProperty("账单日期")
    private String billDate;


}
