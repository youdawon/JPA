package jpa.exception.list;

import jpa.domain.common.ResultCode;

/**
 *  데이터 Find, select, get 예외 함께 처리
 */
public class DatabaseFindException extends InsideException {

    public DatabaseFindException(){
        this.code = ResultCode.DATABASE_SELECT.getCode();
        this.desc = ResultCode.DATABASE_SELECT.getDesc();
    }

    public DatabaseFindException(String desc){
        this.code = ResultCode.DATABASE_SELECT.getCode();
        this.desc = desc;
    }

    public DatabaseFindException(String code, String desc){
        this.code = code;
        this.desc = desc;
    }
}
