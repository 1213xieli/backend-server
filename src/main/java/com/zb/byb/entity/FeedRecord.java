package com.zb.byb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Data
@ApiModel("饲喂记录")
public class FeedRecord {
    @ApiModelProperty(value = "开始时间")
    private String starttime;
    @ApiModelProperty(value = "结束时间")
    private String endtime;
    @ApiModelProperty(value = "单据状态")//保存
    private String billStatus;
    @ApiModelProperty(value = "状态id")//10
    private String billStatusIndex;
    @ApiModelProperty("当前页码")
    public Integer pageNumber = 1;
    @ApiModelProperty("每页大小")
    public Integer pageSize = 10;
    //@JsonProperty(value = "bb")
    @ApiModelProperty("饲喂记录id")
    private String rcordId;
    @ApiModelProperty("饲喂批次号")
    private String batchName;
    @ApiModelProperty("饲喂批次id")
    private String batchId;//养猪批次id
    //private String batchName;//养猪批次号
     @ApiModelProperty("饲喂日期")
     private String feedDate;
     @ApiModelProperty("饲喂列表")
     private List<Pigwash>  feedList;
    @ApiModelProperty("任务状态")
    private String state;
    @ApiModelProperty("单据编号")
    private String number;//




   /*public String userName;//用户id--
    public String userId;//用户id--
    public String pigfarmerCode;//养户编号--
    @ApiModelProperty("饲喂记录批次号")
    public String batchName;//批次号--
    public String pollingDate;//巡检日期--
    @ApiModelProperty("饲喂日期")
    public String feedDate;//饲喂日期--
    public String dayOld;//猪只日龄--
    public String Curcnt;//存栏头数--
    public String freedDay;//饲养天数--
    public String feedCnt;//饲喂头数--
    public String dieAvg;//饲喂均重--
    public String feedNotes;//饲喂备注--
    public String feedScheme;//饲喂方案id--
    public String custid;//养户id--
    public String batchid;//批次id--
    public String rcordId;//单据记录id
    public String  billState;//单据状态
    public String  Cust;//养户--
    public String Create;//操作员
    public List<FeedTypeBean> feedList;//物料品种列表
    public String  bizdate;//饲喂日期--
    public String eveningRemainingInventory;//今夜剩余库存--*/

}
