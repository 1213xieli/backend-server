package com.zb.byb.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 作者：谢李
 */
@Data
public class QuestionReportInfo implements Serializable {
    @ApiModelProperty("主键id")
    private String id;
    @ApiModelProperty("反馈日期")
    private Date fbizdate;
    @ApiModelProperty("养户id")
    private String fcustid;
    @ApiModelProperty("养户名称")
    private String fcustname;
    @ApiModelProperty("回复id")
    private String freplypersonid;
    @ApiModelProperty("回复名称")
    private String freplyersonname;
    @ApiModelProperty("问题")
    private String fdetails;
    @ApiModelProperty("问题答复")
    private String freplydetails;
    @ApiModelProperty("答复时期")
    private Date freplydate;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("排序")
    private String order;
    @ApiModelProperty("扩展字段")
    private String extendsOne;
}
