package com.zb.byb.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * A-生物安全-洗消步骤
 * time:2019/7/24
 * author:xieli
 */
@Data
public class ClearStepInfo implements Serializable {

    private String id;
    private String name;
    private String bizType;
    private String type; // 固定"2"
    private List<Map<String, Object>> item;
    private List<ItemInfo> itemInfoList;

}

