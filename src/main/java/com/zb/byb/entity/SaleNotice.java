package com.zb.byb.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: shaoys
 * @Mail: 415992946@qq.com
 * @Date: Created in 10:02 2019/5/5
 **/
@Data
public class SaleNotice implements Serializable {
    @ApiModelProperty("主键id")
    private String id;
    @ApiModelProperty("记录id")
    private String rcordId;
    @ApiModelProperty("养户id")
    private String custId;
    public int pageNumber = 1;
    public int pageSize = 1000;
}
