package com.gouqiang.shen.domain.vmess.routing;

import lombok.Data;

import java.util.List;

/**
 * @author shengouqiang
 * @date 2020/2/25
 */
@Data
public class RulesBean {
    private String type;
    private String outboundTag;
    private List<String> ip;
}
