package com.gouqiang.shen.exception;

import com.gouqiang.shen.constant.ReturnConstantsEnum;

/**
 * @author shengouqiang
 * @date 2020/2/25
 */
public class BusinessException extends RuntimeException {

    private Boolean logError = true;

    private ReturnConstantsEnum errorEnums;

    public BusinessException(ReturnConstantsEnum res,Throwable e){
        super(res.getDesc(),e);
    }

    public BusinessException(String msg){
        super(msg);
    }

    public BusinessException(ReturnConstantsEnum res){
        super(res.getDesc());
        this.errorEnums = res;
    }

    public BusinessException(ReturnConstantsEnum res, String msg){
        super(msg);
        this.errorEnums = res;
    }

    public BusinessException(ReturnConstantsEnum res, String msg, Boolean logError){
        super(msg);
        this.errorEnums = res;
        this.logError = logError;
    }

    public BusinessException(ReturnConstantsEnum res, Boolean logError){
        super(res.getDesc());
        this.errorEnums = res;
        this.logError = logError;
    }


}
