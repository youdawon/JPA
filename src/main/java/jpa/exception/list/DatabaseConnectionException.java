package jpa.exception.list;


import jpa.domain.common.ResultCode;

public class DatabaseConnectionException extends InsideException {

    public DatabaseConnectionException(){
        this.code = ResultCode.DATABASE_CONNECTION.getCode();
        this.desc = ResultCode.DATABASE_CONNECTION.getDesc();
    }

    public DatabaseConnectionException(String desc){
        this.code = ResultCode.DATABASE_CONNECTION.getCode();
        this.desc = desc;
    }

    public DatabaseConnectionException(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public DatabaseConnectionException(ResultCode resultCode){
        this.code = resultCode.getCode();
        this.desc = resultCode.getDesc();
    }
}