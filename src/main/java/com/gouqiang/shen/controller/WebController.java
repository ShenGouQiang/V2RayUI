package com.gouqiang.shen.controller;

import com.alibaba.fastjson.JSONObject;
import com.gouqiang.shen.common.Result;
import com.gouqiang.shen.config.JsonFileFormatConfig;
import com.gouqiang.shen.domain.vmess.V2RayJsonDO;
import com.gouqiang.shen.service.JsonService;
import com.gouqiang.shen.util.json.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author shengouqiang
 * @date 2020/2/25
 */
@RestController
@Slf4j
public class WebController {

    @Resource
    JsonService jsonService;

    @PostMapping("/modify/v2ray/json/info")
    public Result<Void> saveModifyToJson(@RequestParam("port") Integer port,
                                         @RequestParam("id") String  id,
                                         @RequestParam("alertId") Integer  alterId,
                                         @RequestParam("path") String  path) throws IOException {
        jsonService.save(port,id,alterId,path);
        return new Result<>();
    }

}
