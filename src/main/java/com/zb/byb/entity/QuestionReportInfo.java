package com.zb.byb.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 作者：谢李
 */
@Data
public class QuestionReportInfo implements Serializable {
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("问题")
    private String question;
}
