package com.gouqiang.shen.domain.vmess.inbounds;

import lombok.Data;

/**
 * @author shengouqiang
 * @date 2020/2/25
 */
@Data
public class InboundsBean {
    private int port;
    private String listen;
    private String protocol;
    private SettingsBean settings;
    private StreamSettingsBean streamSettings;
}