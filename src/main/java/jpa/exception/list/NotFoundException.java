package jpa.exception.list;

import jpa.domain.common.ResultCode;

public class NotFoundException extends CustomException{

	public NotFoundException(){
		this.code = ResultCode.NOT_FOUND.getCode();
		this.desc = ResultCode.NOT_FOUND.getDesc();
	}

	public NotFoundException(String desc){
		this.code = ResultCode.NOT_FOUND.getCode();
		this.desc = desc;
	}

	public NotFoundException(String code, String desc){
		this.code = code;
		this.desc = desc;
	}

	public NotFoundException(ResultCode resultCode){
		this.code = resultCode.getCode();
		this.desc = resultCode.getDesc();
	}
}
