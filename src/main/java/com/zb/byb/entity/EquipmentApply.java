package com.zb.byb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ApiModel("设备领用申请")
public class EquipmentApply {
    @ApiModelProperty("养户姓名")
    private String farmerName;
    @ApiModelProperty("申请日期")
    private Date applyDate;
    @ApiModelProperty("累计已领取设备金额")
    private Double totalPayment;
    @ApiModelProperty("设备申请明细")
    private List<Equipment> applyList;
    @ApiModelProperty("设备实际领用明细")
    private List<Equipment>  actualList;
    @ApiModelProperty("是否委托他人领取")
    private Boolean entrust;
    @ApiModelProperty("被委托领取人姓名")
    private String entrustedName;
    @ApiModelProperty("被委托领取人身份证号")
    private String entrustedIdentity;

}
