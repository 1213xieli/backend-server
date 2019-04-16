package com.zb.byb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@ApiModel("我的信息")
public class MyInfo {
    //@JsonProperty("id")
    @ApiModelProperty("养户id")
    private String id;
    //@JsonProperty("fname")
    @ApiModelProperty("姓名")
    private String name;
    //@JsonProperty("ftelno")
    @ApiModelProperty("手机号")
    private String telNum;
    //@JsonProperty(value = "cfpigpen")
    @ApiModelProperty("猪舍地址")
    private String piggeryAddress;
    //@JsonProperty(value = "fkhsj")
    @ApiModelProperty("开户时间")
    private String registerDate;
    //@JsonProperty(value = "servicedep")
    @ApiModelProperty("所属服务部")
    private String dept;
    //@JsonProperty(value = "manager")
    @ApiModelProperty("管理员")
    private String manager;
    //@JsonProperty(value = "fcell")
    @ApiModelProperty("管理员电话")
    private String managerTelNum;
    //@JsonProperty(value = "cfraisestate")
    @ApiModelProperty("饲养状态")
    private String status;
    //@JsonProperty("growUp")
    @ApiModelProperty("我的成长")
    private String growUp;
    //@JsonProperty("entrustedName")
    @ApiModelProperty("被委托人姓名")
    private String entrustedName;
    //@JsonProperty("entrustedIdentity")
    @ApiModelProperty("被委托人身份证号")
    private String entrustedIdentity;



}
