package com.zb.byb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@ApiModel("领药申请")
public class DrugApply {
    @ApiModelProperty("领药申请id")
    private String id;
    @ApiModelProperty("记录id")
    private String rcordId;
    @ApiModelProperty("custId")
    private String custId;

    @ApiModelProperty("领药申请批次号")
    private String batchId;
    @ApiModelProperty("批次")
    private String batchNo;
    @ApiModelProperty("天数")
    private String curday;
     @ApiModelProperty("领药申请日期")
    private String applydate;
     @ApiModelProperty("领药申请原因")
    private String reason;
    @ApiModelProperty("curcnt")
    private String curcnt;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("状态")
    private int billStatusIndex = STATUS_KEEP;//1：表示待审核（10保存，20提交）  2：表示已审核（30审核）

    @ApiModelProperty("是否委托他人领取")
    private Boolean isEntrust;
    @ApiModelProperty("被委托人姓名")
    private String entrustName;
    @ApiModelProperty("被委托人id")
    private String entrustNameId;
    @ApiModelProperty("被委托人身份证号")
    private String entrustIdcard;
    @ApiModelProperty("申请领药明细")
    private List<Drug> entrys;
    @ApiModelProperty("实际领药明细")
    private List<Drug> actEntrys;
    public int pageNumber = 1;
    public int pageSize = 10;
    @ApiModelProperty("领药申请编号")
    private String number;
    @ApiModelProperty("签名图片")
    private List<FileEntry> signerList;

    @ApiModelProperty("微信录音")
    private List<FileEntry> wxRecordList;
    @ApiModelProperty("微信录音路径")
    private List<ActFileEntry> voiceUrl;

    @ApiModelProperty("记录状态")
    private String state;
    @ApiModelProperty(value = "开始时间")
    private String starttime;
    @ApiModelProperty(value = "结束时间")
    private String endtime;
    @ApiModelProperty(value = "状态")
    private String billStatus;

    //待审核
    public final static int STATUS_KEEP = 1;
    //表示已审核（30审核）
    public final static  int STATUS_APPROVE = 2;

}
