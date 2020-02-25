package com.gouqiang.shen.service;

import com.gouqiang.shen.domain.vo.ShowBean;

import java.util.List;

/**
 * @author shengouqiang
 * @date 2020/2/25
 */
public interface JsonService {

    void save(Integer port,String id,Integer alterId,String path);

    List<ShowBean> query();

    void deleteJson(String id,String path);

}
