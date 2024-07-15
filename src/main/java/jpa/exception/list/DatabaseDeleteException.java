package jpa.exception.list;


import jpa.domain.common.ResultCode;

public class DatabaseDeleteException extends InsideException {

	public DatabaseDeleteException(){
		this.code = ResultCode.DATABASE_DELETE.getCode();
		this.desc = ResultCode.DATABASE_DELETE.getDesc();
	}

	public DatabaseDeleteException(String desc){
		this.code = ResultCode.DATABASE_DELETE.getCode();
		this.desc = desc;
	}

	public DatabaseDeleteException(String code, String desc){
		this.code = code;
		this.desc = desc;
	}
}
