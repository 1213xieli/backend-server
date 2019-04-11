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

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPiggeryAddress() {
        return piggeryAddress;
    }

    public void setPiggeryAddress(String piggeryAddress) {
        this.piggeryAddress = piggeryAddress;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getIntroducer() {
        return introducer;
    }

    public void setIntroducer(String introducer) {
        this.introducer = introducer;
    }

    public String getIntroducerDept() {
        return introducerDept;
    }

    public void setIntroducerDept(String introducerDept) {
        this.introducerDept = introducerDept;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }
}
