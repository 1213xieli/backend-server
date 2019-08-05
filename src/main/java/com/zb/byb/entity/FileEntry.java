package com.zb.byb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("文件实体")
public class FileEntry {

    @ApiModelProperty(value = "服务器的图片id")
    private String serverId;

    @ApiModelProperty(value = "名称")
    private String fileName;

    @ApiModelProperty(value = "图片字符串")
    private String imgContent;

    @ApiModelProperty(value = "文件类型")
    private String imgType;
}
