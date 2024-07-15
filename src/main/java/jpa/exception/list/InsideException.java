package jpa.exception.list;


import jpa.domain.common.ResultCode;

public class InsideException extends CustomException {

    public InsideException(){
        this.code = ResultCode.INTERNAL.getCode();
        this.desc = ResultCode.INTERNAL.getDesc();
    }

    public InsideException(String desc){
        this.code = ResultCode.INTERNAL.getCode();
        this.desc = desc;
    }

    public InsideException(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public InsideException(ResultCode resultCode){
        this.code = resultCode.getCode();
        this.desc = resultCode.getDesc();
    }
}