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
     * 模板文件不存在
     */
    TEMPLATE_FEIL_NOT_EXIT("10004", "模板文件不存在"),
    /**
     * 写入LOC文件失败
     */
    WRITE_LOC_FILE_FAIL("10005", "写入LOC文件失败"),

    /**
     * 执行脚本失败
     */
    EXECUTE_COMMAND_SHELL_FAIL("10006", "执行脚本失败"),

    /**
     * 成功
     */
    SUCCESS("0000", "success"),

    /**
     * 当前路径非法
     */
    NOT_ALLOW_PATH_NAME("10003", "当前路径非法");

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
