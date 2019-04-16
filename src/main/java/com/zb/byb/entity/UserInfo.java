package com.zb.byb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@ApiModel("注册开户")
public class UserInfo {
    @ApiModelProperty("申请条件")
    private String requirements;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("猪舍地址")
    private String piggeryAddress;
    @ApiModelProperty("身份证号")
    private String identity;
    @ApiModelProperty("手机号码")
    private String telNum;
    @ApiModelProperty("介绍人")
    private String introducer;
    @ApiModelProperty("介绍人所在服务部")
    private String introducerDept;
    @ApiModelProperty("邀请码")
    private String invitationCode;


}
