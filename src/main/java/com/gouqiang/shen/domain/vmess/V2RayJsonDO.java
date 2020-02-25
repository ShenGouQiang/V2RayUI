package com.gouqiang.shen.domain.vmess;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.List;

/**
 * @author shengouqiang
 * @date 2020/2/25
 */
@NoArgsConstructor
@Data
public class V2RayJsonDO {

    private RoutingBean routing;
    private List<InboundsBean> inbounds;
    private List<OutboundsBean> outbounds;

    @NoArgsConstructor
    @Data
    public static class RoutingBean {
        private List<RulesBean> rules;

        @NoArgsConstructor
        @Data
        public static class RulesBean {
            private String type;
            private String outboundTag;
            private List<String> ip;
        }
    }

    @NoArgsConstructor
    @Data
    public static class InboundsBean {
        private int port;
        private String listen;
        private String protocol;
        private SettingsBean settings;
        private StreamSettingsBean streamSettings;


        @Override
        public InboundsBean clone(){
            // TODO Auto-generated method stub
            InboundsBean employss = null;

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(this);
                oos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            try {
                ObjectInputStream ois = new ObjectInputStream(bais);
                employss = (InboundsBean) ois.readObject();
                ois.close();
            } catch (IOException | ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return employss;
        }


        @NoArgsConstructor
        @Data
        public static class SettingsBean {
            private List<ClientsBean> clients;

            @NoArgsConstructor
            @Data
            public static class ClientsBean {
                private String id;
                private int level;
                private int alterId;
            }
        }

        @NoArgsConstructor
        @Data
        public static class StreamSettingsBean {
            private String network;
            private WsSettingsBean wsSettings;

            @NoArgsConstructor
            @Data
            public static class WsSettingsBean {
                private String path;
            }
        }
    }

    @NoArgsConstructor
    @Data
    public static class OutboundsBean {
        private String protocol;
        private SettingsBeanX settings;
        private String tag;

        @NoArgsConstructor
        @Data
        public static class SettingsBeanX {
        }
    }

}
