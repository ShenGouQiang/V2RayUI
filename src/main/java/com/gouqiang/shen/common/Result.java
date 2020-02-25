package com.gouqiang.shen.common;

import com.gouqiang.shen.constant.ReturnConstantsEnum;
import lombok.Data;

/**
 * @author shengouqiang
 * @date 2020/2/25
 */
@Data
public class Result<T> {


    public Result(){
        this.code = ReturnConstantsEnum.SUCCESS.getCode();
        this.desc = ReturnConstantsEnum.SUCCESS.getDesc();
    }
    public Result(ReturnConstantsEnum res){
        this.code = res.getCode();
        this.desc = res.getDesc();
    }

    public Result(ReturnConstantsEnum res, String desc){
        this.code = res.getCode();
        this.desc = desc;
    }

    public Result(T data){
        this.code = ReturnConstantsEnum.SUCCESS.getCode();
        this.desc = ReturnConstantsEnum.SUCCESS.getDesc();
        this.data = data;
    }

    public Result(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    private String code;
    private String desc;

    private T data;
}
