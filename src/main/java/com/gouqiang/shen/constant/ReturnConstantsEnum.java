package com.gouqiang.shen.constant;

import lombok.Getter;

/**
 * @author shengouqiang
 * @date 2020/2/25
 */
@Getter
public enum ReturnConstantsEnum {

    /**
     * 读取JSON文件失败
     */
    READ_JSON_FILE_FAIL("10001", "读取JSON文件失败"),
    /**
     * 写入JSON文件失败
     */
    WRITE_JSON_FILE_FAIL("10002", "写入JSON文件失败"),
    /**
     * 成功
     */
    SUCCESS("0000", "success");

    /**
     * 编码
     */
    private String code;


    /**
     * 描述
     */
    private String desc;

    ReturnConstantsEnum(String userCode, String userDesc) {
        this.code = userCode;
        this.desc = userDesc;
    }
}
