package com.gouqiang.shen.service;

import com.alibaba.fastjson.JSONObject;
import com.gouqiang.shen.config.JsonFileFormatConfig;
import com.gouqiang.shen.constant.ReturnConstantsEnum;
import com.gouqiang.shen.domain.vmess.V2RayJsonDO;
import com.gouqiang.shen.exception.BusinessException;
import com.gouqiang.shen.util.json.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author shengouqiang
 * @date 2020/2/25
 */
@Service
@Slf4j
public class JsonServiceImpl implements JsonService {

    @Resource
    JsonFileFormatConfig config;

    @Override
    public void save(Integer port, String id, Integer alterId, String path) {
        try {
            String jsonStr = JsonUtils.readFile(config.getOriginFilePath());
            V2RayJsonDO jsonDO = JSONObject.parseObject(jsonStr, V2RayJsonDO.class);
            List<V2RayJsonDO.InboundsBean> inboundsBeanList = jsonDO.getInbounds();
            V2RayJsonDO.InboundsBean selectBean = inboundsBeanList.stream().filter(i -> i.getStreamSettings().getWsSettings().getPath().equals(path))
                    .filter(i -> i.getSettings().getClients().stream().anyMatch(c -> c.getId().equals(id))).findFirst().orElse(null);
            if (!Objects.isNull(selectBean)) {
                throw new BusinessException(ReturnConstantsEnum.WRITE_JSON_FILE_FAIL);
            }
            V2RayJsonDO.InboundsBean newBean = inboundsBeanList.get(0).clone();
         //   BeanUtils.copyProperties(inboundsBeanList.get(0),newBean);
            newBean.setPort(port);
            newBean.getSettings().getClients().get(0).setId(id);
            newBean.getSettings().getClients().get(0).setAlterId(alterId);
            newBean.getStreamSettings().getWsSettings().setPath(path);
            inboundsBeanList.add(newBean);
            JsonUtils.createJsonFile(jsonDO,config.getResultFilePath());
        } catch (Exception e) {
            log.error(ReturnConstantsEnum.WRITE_JSON_FILE_FAIL.getDesc(), e);
            throw new BusinessException(ReturnConstantsEnum.WRITE_JSON_FILE_FAIL);
        }
    }
}
