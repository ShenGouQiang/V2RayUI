package com.gouqiang.shen.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author shengouqiang
 * @date 2020/2/25
 */
@Configuration
@Data
public class LocFileFormatConfig {

    @Value("${nginx.filePath}")
    private String outPutFilePath;

    @Value("${nginx.templateFile}")
    private String templateFile;
}
