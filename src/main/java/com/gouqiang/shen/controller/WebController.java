package com.gouqiang.shen.controller;

import com.alibaba.fastjson.JSONObject;
import com.gouqiang.shen.common.Result;
import com.gouqiang.shen.config.JsonFileFormatConfig;
import com.gouqiang.shen.domain.vmess.V2RayJsonDO;
import com.gouqiang.shen.domain.vo.ShowBean;
import com.gouqiang.shen.service.JsonService;
import com.gouqiang.shen.util.json.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author shengouqiang
 * @date 2020/2/25
 */
@RestController
@Slf4j
public class WebController {

    @Resource
    JsonService jsonService;

    @PostMapping("/add/v2ray/json/info")
    public Result<Void> saveModifyToJson(@RequestParam("port") Integer port,
                                         @RequestParam("id") String  id,
                                         @RequestParam("alertId") Integer  alterId,
                                         @RequestParam("path") String  path){
        jsonService.save(port,id,alterId,path);
        return new Result<>();
    }

    @GetMapping("/query/v2ray/json/info")
    public Result<List<ShowBean>> saveModifyToJson() {
        return new Result<>(jsonService.query());
    }

    @GetMapping("/delete/v2ray/json/info")
    public Result<Boolean> deleteJson(@RequestParam("id") String  id,
                                      @RequestParam("path") String  path){
        jsonService.deleteJson(id,path);
        return new Result<>(Boolean.TRUE);
    }

}
