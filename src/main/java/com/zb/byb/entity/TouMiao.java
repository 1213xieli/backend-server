package com.zb.byb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@ApiModel("投苗申请")
public class TouMiao {
    @ApiModelProperty("申请投苗ID")
    private String rcordId;
    @ApiModelProperty("管理员")
    private String userName;
    @ApiModelProperty("养户id")
    private String custId;
    @ApiModelProperty("养户名称")
    private String custName;
    @ApiModelProperty("养户信息")
    private Farmer farmer;
    @ApiModelProperty("申请投苗日期")
    private Date applyDate;
    @ApiModelProperty("申请投苗数量")
    private Integer applyAmount;
    @ApiModelProperty("身份证")
    private  String  identityCards;
    @ApiModelProperty("保证金余额")
    private String balance;
    @ApiModelProperty("投苗数量")
    private int num;
    @ApiModelProperty("赁栏舍名称")
    private String villageName;
    @ApiModelProperty("赁栏舍villId")
    private String villId;
    @ApiModelProperty("养户单头承担租赁费")
    private double oneHandRent;
    @ApiModelProperty("赁栏舍名称")
    private String billStatus = "";
    @ApiModelProperty("状态")
    private int billStatusIndex = STATUS_KEEP;//1：表示待审核（10保存，20提交）  2：表示已审核（30审核）
    @ApiModelProperty("备注")
    private String remark = "";
    @ApiModelProperty("sessionId")
    private String sessionId;

    @ApiModelProperty("实际投苗日期")
    private Date actualDate;
    @ApiModelProperty("实际投苗数量")
    private Integer actualAmount;
    @ApiModelProperty("实际投苗均重")
    private Double actualAvgWeight;
    @ApiModelProperty("清洗消毒工作是否完成")
    private Boolean disinfectFinished;
    @ApiModelProperty("保温箱、屋中屋是否准备")
    private Boolean incubatorReadied;
    @ApiModelProperty("保温灯是否准备")
    private Boolean heatLampReadied;
    @ApiModelProperty("发电机是否正常使用")
    private Boolean electricGeneratorReadied;
    @ApiModelProperty("圆盘料槽是否准备")
    private Boolean troughReadied;
    @ApiModelProperty("物资准备")
    private Boolean materialsReadied;
    @ApiModelProperty("是否准备加药桶")
    private Boolean barrelReadied;
    @ApiModelProperty("上猪台是否准备")
    private Boolean pigTableReadied;
    @ApiModelProperty("加温/降温设施是否达到标准")
    private Boolean temperatureControlReadied;
    @ApiModelProperty("图片")
    private Object pigpenInside;

    //待审核
    public final static int STATUS_KEEP = 1;
    //表示已审核（30审核）
    public final static  int STATUS_APPROVE = 2;

    public int pageNumber = 1;
    public int pageSize = 1000;

}
