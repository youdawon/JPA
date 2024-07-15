package jpa.exception.list;

import jpa.domain.common.ResultCode;

/**
 * Ok Exception 클래스
 * @author pineone
 *
 */

public class OkException extends CustomException {

	public OkException(){
		this.code = ResultCode.OK_SUCCESS.getCode();
		this.desc = ResultCode.OK_SUCCESS.getDesc();
	}

	public OkException(String desc){
		this.code = ResultCode.OK_SUCCESS.getCode();
		this.desc = desc;
	}

	public OkException(String code, String desc){
		this.code = code;
		this.desc = desc;
	}

	public OkException(ResultCode resultCode){
		this.code = resultCode.getCode();
		this.desc = resultCode.getDesc();
	}
}
