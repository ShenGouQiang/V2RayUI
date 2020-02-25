package com.gouqiang.shen.domain.vmess;

import com.gouqiang.shen.domain.vmess.inbounds.InboundsBean;
import com.gouqiang.shen.domain.vmess.outbounds.OutboundsBean;
import com.gouqiang.shen.domain.vmess.routing.RoutingBean;
import lombok.Data;

import java.util.List;

/**
 * @author shengouqiang
 * @date 2020/2/25
 */
@Data
public class V2RayJsonDO {

    private RoutingBean routing;
    private List<InboundsBean> inbounds;
    private List<OutboundsBean> outbounds;

}
