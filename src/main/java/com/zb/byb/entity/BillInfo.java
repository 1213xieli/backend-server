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
    private String rcordId;
    @ApiModelProperty("养户id")
    private String custId;
    @ApiModelProperty("养户名称")
    private String custName;
    @ApiModelProperty("批次")
    private String batchId;
    private String batchName;
    @ApiModelProperty("账单日期")
    private String bizdate;
    @ApiModelProperty("序号")
    private String number;
    @ApiModelProperty("状态码")
    private String billStatusIndex;
    @ApiModelProperty("状态")
    private String billStatus;
    @ApiModelProperty("endSurplus")
    private String endSurplus;
    @ApiModelProperty("期间")
    private String period;
    @ApiModelProperty("开始时间")
    private String starttime;
    @ApiModelProperty("结束时间")
    private String endtime;

    private PigEntry pigEntry;
    private FeedEntry feedEntry;
    private DrugEntry drugEntry;
    private EquipEntry equipEntry;


    public int pageNumber = 1;
    public int pageSize = 100;

}
