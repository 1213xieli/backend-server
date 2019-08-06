package com.zb.byb.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * ItemInfo
 * time:2019/7/24
 * author:xieli
 */
@Data
public class ItemInfo implements Serializable {
    private String itemId;
    private String name;//":"雾化消毒三分钟自拍"
    private String isNewFoto;
    private String maxQty;//":2,
    private String minQty;//":1,
}
