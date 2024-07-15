package jpa.exception.list;


import jpa.domain.common.ResultCode;

/**
 * 데이터 save, update 예외 함께 처리
 */
public class DatabaseSaveException extends InsideException {

    public DatabaseSaveException(){
        this.code = ResultCode.DATABASE_SAVE.getCode();
        this.desc = ResultCode.DATABASE_SAVE.getDesc();
    }

    public DatabaseSaveException(String desc){
        this.code = ResultCode.DATABASE_SAVE.getCode();
        this.desc = desc;
    }

    public DatabaseSaveException(String code, String desc){
        this.code = code;
        this.desc = desc;
    }
}