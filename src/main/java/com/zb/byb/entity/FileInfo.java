package com.zb.byb.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * FileInfo 附件信息
 * time:2019/7/25
 * author:xieli
 */
@ApiModel(value = "外出申请")
@Data
public class FileInfo {
    private String name;
    private String itemId;
    private List<FileEntry> data = new ArrayList<FileEntry>();
}
