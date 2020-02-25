package com.gouqiang.shen.domain.vmess.inbounds;

import lombok.Data;

import java.util.List;

/**
 * @author shengouqiang
 * @date 2020/2/25
 */
@Data
public class SettingsBean {
    private List<ClientsBean> clients;
}
