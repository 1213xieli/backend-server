package com.zb.byb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ApiModel("领药申请")
public class DrugApply {
    @ApiModelProperty("领药申请id")
    private String id;
    @ApiModelProperty("领药申请批次号")
    private String batchId;
     @ApiModelProperty("领药申请日期")
    private Date applyDate;
     @ApiModelProperty("领药申请原因")
    private String reason;
     @ApiModelProperty("语言说话")
    private String voice;
    @ApiModelProperty("是否委托他人领取")
    private Boolean entrust;
    @ApiModelProperty("被委托人姓名")
    private String entrustedName;
    @ApiModelProperty("被委托人身份证号")
    private String entrustedIdentity;
    @ApiModelProperty("实际领药明细")
    private List<Drug> actualList;

}
