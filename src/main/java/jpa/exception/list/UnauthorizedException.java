package jpa.exception.list;

import jpa.domain.common.ResultCode;

public class UnauthorizedException extends CustomException {

	public UnauthorizedException(){
		this.code = ResultCode.UNAUTHORIZED.getCode();
		this.desc = ResultCode.UNAUTHORIZED.getDesc();
	}

	public UnauthorizedException(String desc){
		this.code = ResultCode.UNAUTHORIZED.getCode();
		this.desc = desc;
	}

	public UnauthorizedException(String code, String desc){
		this.code = code;
		this.desc = desc;
	}

	public UnauthorizedException(ResultCode resultCode){
		this.code = resultCode.getCode();
		this.desc = resultCode.getDesc();
	}

	public UnauthorizedException(ResultCode resultCode, String desc){
		this.code = resultCode.getCode();
		this.desc = resultCode.getDesc() + " " + desc;
	}
}
