package com.zb.byb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@ApiModel("我的信息")
public class MyInfo {
    @ApiModelProperty("养户id")
    private String id;
    @ApiModelProperty("姓名")
    private String Name;
    @ApiModelProperty("手机号")
    private String telNum;
    @ApiModelProperty("猪舍地址")
    private String piggeryAddress;
    @ApiModelProperty("开户时间")
    private String registerDate;
    @ApiModelProperty("所属服务部")
    private String dept;
    @ApiModelProperty("管理员")
    private String manager;
    @ApiModelProperty("管理员电话")
    private String managerTelNum;
    @ApiModelProperty("饲养状态")
    private String status;
    @ApiModelProperty("我的成长")
    private String growUp;
    @ApiModelProperty("被委托人姓名")
    private String entrustedName;
    @ApiModelProperty("被委托人身份证号")
    private String entrustedIdentity;



}
