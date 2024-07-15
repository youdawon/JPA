package jpa.exception.list;

import jpa.domain.common.ResultCode;

public class BadRequestException extends CustomException {

	public BadRequestException(){
		this.code = ResultCode.BAD_REQUEST.getCode();
		this.desc = ResultCode.BAD_REQUEST.getDesc();
	}

	public BadRequestException(String desc){
		this.code = ResultCode.BAD_REQUEST.getCode();
		this.desc = desc;
	}

	public BadRequestException(String code, String desc){
		this.code = code;
		this.desc = desc;
	}

	public BadRequestException(ResultCode resultCode){
		this.code = resultCode.getCode();
		this.desc = resultCode.getDesc();
	}
}
