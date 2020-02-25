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
public class JsonFileFormatConfig {

    @Value("${json.file.originPath}")
    private String originFilePath;

    @Value("${json.file.resultPath}")
    private String resultFilePath;
}
