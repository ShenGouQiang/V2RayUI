package com.gouqiang.shen.domain.vmess.outbounds;

import lombok.Data;

/**
 * @author shengouqiang
 * @date 2020/2/25
 */
@Data
public class OutboundsBean {
    private String protocol;
    private SettingsBeanX settings;
    private String tag;
}
