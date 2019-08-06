package com.zb.byb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 周作为
 * @Date: 2019/7/15 09:53
 * @Description: 外出申请
 */
@ApiModel(value = "外出申请")
@Data
public class GoOutApply {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "单据id")
    private String rcordId;

    @ApiModelProperty(value = "base64单据id")
    private String base64RcordId;

    @ApiModelProperty(value = "姓名")
    private String custName;

    @ApiModelProperty(value = "出场时间")
    private String planOutDate;

    @ApiModelProperty(value = "返场时间")
    private String planReturnDate;

    @ApiModelProperty(value = "实际结束时间")
    private String planResultDate;

    @ApiModelProperty(value = "养户id")
    private String custId;

    @ApiModelProperty(value = "出场事由")
    private String reason;

    @ApiModelProperty(value = "bizType")
    private String bizType;//":"userBackClean_save",

    //submit
    @ApiModelProperty(value = "提交")
    private String submit;

    @ApiModelProperty(value = "清洗步骤id")
    private String cleanTypeId;//Va4AAAm+DJMVJ5tj',

    @ApiModelProperty(value = "当前时间", hidden = true)
    private String bizDate;//":"2019-07-22",

    @ApiModelProperty(value = "图片")
    private List<FileInfo> imgs = new ArrayList<>();

    @ApiModelProperty(value = "清洗步骤列表")
    private List<ClearStepInfo> steps = new ArrayList<>();

    private String billStatus;
    private String billStatusIndex;
    private int pageNumber = 1;
    private int pageSize = 10;
    private String approveStatus;
    private String approveStatusIndex;
    private String approveAdminId; // 审核人id
    private String approveAdminName;//审核人
    private String approveTime;
    private String approve;
    private String serviceOrgUnitId;
    private String serviceOrgUnitName;
    private String startDate; // 筛选条件 开始时间
    private String endDate; // 筛选条件 结束时间
}
