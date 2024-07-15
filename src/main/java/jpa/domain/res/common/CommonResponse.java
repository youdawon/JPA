package jpa.domain.res.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import jpa.exception.list.CustomException;
import jpa.domain.common.ResultCode;
import lombok.*;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse {

    @JsonProperty("code")
    private String code = ResultCode.OK_SUCCESS.getCode();

    @JsonProperty("desc")
    private String desc = ResultCode.OK_SUCCESS.getDesc();

    @JsonProperty("result")
    private Object result;

    public CommonResponse(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public CommonResponse(CustomException ce) {
        code = ce.getCode();
        desc = ce.getDesc();
    }

    public CommonResponse(ResultCode resultCode){
        this.code = resultCode.getCode();
        this.desc = resultCode.getDesc();
    }

    public void setException(CustomException ce){
        this.code = ce.getCode();
        this.desc = ce.getDesc();
    }
}