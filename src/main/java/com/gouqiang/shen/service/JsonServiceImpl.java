package com.gouqiang.shen.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gouqiang.shen.config.JsonFileFormatConfig;
import com.gouqiang.shen.constant.ReturnConstantsEnum;
import com.gouqiang.shen.domain.vmess.V2RayJsonDO;
import com.gouqiang.shen.domain.vmess.inbounds.InboundsBean;
import com.gouqiang.shen.domain.vo.ShowBean;
import com.gouqiang.shen.exception.BusinessException;
import com.gouqiang.shen.util.json.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
            List<InboundsBean> inboundsBeanList = jsonDO.getInbounds();
            InboundsBean selectBean = inboundsBeanList.stream().filter(i -> i.getStreamSettings().getWsSettings().getPath().equals(path))
                    .filter(i -> i.getSettings().getClients().stream().anyMatch(c -> c.getId().equals(id))).findFirst().orElse(null);
            if (!Objects.isNull(selectBean)) {
                throw new BusinessException(ReturnConstantsEnum.WRITE_JSON_FILE_FAIL);
            }
            InboundsBean newBean  = new InboundsBean();
            BeanUtils.copyProperties(inboundsBeanList.get(0),newBean);
            newBean.setPort(port);
            newBean.getSettings().getClients().get(0).setId(id);
            newBean.getSettings().getClients().get(0).setAlterId(alterId);
            newBean.getStreamSettings().getWsSettings().setPath(path);
            inboundsBeanList.add(newBean);
            String resultJson =JSON.toJSONString(jsonDO, SerializerFeature.DisableCircularReferenceDetect);
            System.out.println(resultJson);
            JsonUtils.createJsonFile(jsonDO,config.getResultFilePath());
        } catch (Exception e) {
            log.error(ReturnConstantsEnum.WRITE_JSON_FILE_FAIL.getDesc(), e);
            throw new BusinessException(ReturnConstantsEnum.WRITE_JSON_FILE_FAIL);
        }
    }

    @Override
    public List<ShowBean> query() {
        try{
            String jsonStr = JsonUtils.readFile(config.getOriginFilePath());
            V2RayJsonDO jsonDO = JSONObject.parseObject(jsonStr, V2RayJsonDO.class);
            return jsonDO.getInbounds().stream().map(i ->{
                ShowBean bean = new ShowBean();
                bean.setPort(i.getPort());
                bean.setId(i.getSettings().getClients().get(0).getId());
                bean.setAlterId(i.getSettings().getClients().get(0).getAlterId());
                bean.setPath(i.getStreamSettings().getWsSettings().getPath());
                return bean;
            }).collect(Collectors.toList());
        }catch (Exception e) {
            log.error(ReturnConstantsEnum.READ_JSON_FILE_FAIL.getDesc(), e);
            throw new BusinessException(ReturnConstantsEnum.WRITE_JSON_FILE_FAIL);
        }
    }

    @Override
    public void deleteJson(String id,String path) {
        try {
            String jsonStr = JsonUtils.readFile(config.getOriginFilePath());
            V2RayJsonDO jsonDO = JSONObject.parseObject(jsonStr, V2RayJsonDO.class);
            List<InboundsBean> inboundsBeanList = jsonDO.getInbounds();
            InboundsBean selectBean = inboundsBeanList.stream().filter(i -> i.getStreamSettings().getWsSettings().getPath().equals(path))
                    .filter(i -> i.getSettings().getClients().stream().anyMatch(c -> c.getId().equals(id))).findFirst().orElse(null);
            if (Objects.isNull(selectBean)) {
                return;
            }
            Iterator<InboundsBean> iterator = inboundsBeanList.iterator();
            while(iterator.hasNext()){
                InboundsBean tmpBean =  iterator.next();
                if(tmpBean.getSettings().getClients().get(0).getId().equals(id) && tmpBean.getStreamSettings().getWsSettings().getPath().equals(path)){
                    iterator.remove();
                }
            }
            JsonUtils.createJsonFile(jsonDO,config.getResultFilePath());
        } catch (Exception e) {
            log.error(ReturnConstantsEnum.WRITE_JSON_FILE_FAIL.getDesc(), e);
            throw new BusinessException(ReturnConstantsEnum.WRITE_JSON_FILE_FAIL);
        }
    }
}
