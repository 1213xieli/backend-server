package com.zb.byb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ApiModel("结算记录")
public class BalanceRecord {
    @ApiModelProperty("结算记录id")
    private String id;
    @ApiModelProperty("结算批次号")
    private String batchId;
    @ApiModelProperty("品种")
    private String bread;
    @ApiModelProperty("投苗均重")
    private Double touMiaoAvgWeight;
    @ApiModelProperty("上市均重")
    private Double AvgWeight;
    @ApiModelProperty("成活率")
    private Double aliveRate;
    @ApiModelProperty("料肉比")
    private Double meatRate;
    @ApiModelProperty("正品率")
    private Double goodRate;
    @ApiModelProperty("结算明细")
    private BalanceDetail balanceDetail;
    @ApiModelProperty("当前页码")
    public int pageNumber = 1;
    @ApiModelProperty("每页大小")
    public int pageSize = 5;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBread() {
        return bread;
    }

    public void setBread(String bread) {
        this.bread = bread;
    }

    public Double getTouMiaoAvgWeight() {
        return touMiaoAvgWeight;
    }

    public void setTouMiaoAvgWeight(Double touMiaoAvgWeight) {
        this.touMiaoAvgWeight = touMiaoAvgWeight;
    }

    public Double getAvgWeight() {
        return AvgWeight;
    }

    public void setAvgWeight(Double avgWeight) {
        AvgWeight = avgWeight;
    }

    public Double getAliveRate() {
        return aliveRate;
    }

    public void setAliveRate(Double aliveRate) {
        this.aliveRate = aliveRate;
    }

    public Double getMeatRate() {
        return meatRate;
    }

    public void setMeatRate(Double meatRate) {
        this.meatRate = meatRate;
    }

    public Double getGoodRate() {
        return goodRate;
    }

    public void setGoodRate(Double goodRate) {
        this.goodRate = goodRate;
    }

    public BalanceDetail getBalanceDetail() {
        return balanceDetail;
    }

    public void setBalanceDetail(BalanceDetail balanceDetail) {
        this.balanceDetail = balanceDetail;
    }
}
