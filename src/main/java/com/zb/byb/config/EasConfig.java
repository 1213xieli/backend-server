package com.zb.byb.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/* *
 * @description EAS属性配置注入
 * @author xieli
 * @date  16:15 2019/8/1
 **/
@ConfigurationProperties(prefix = "eas")
@Data
public class EasConfig {
    private String loginUrl;
    private String taskUrl;
    private String batchUrl;
    private String dcName;
    private String userName;
    private String password;

    private String ip;
    private String port;
    private String commonUrl;
}
