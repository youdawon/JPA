package jpa.exception.handler;

import jpa.domain.common.ResultCode;
import jpa.domain.res.common.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public final CommonResponse handleMethodNotSupportedException(HttpServletRequest request) {
        CommonResponse commonResDto = new CommonResponse(ResultCode.METHOD_NOT_ALLOWED);
        return commonResDto;
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public final CommonResponse handleUnsupportedMediaTypeException(HttpServletRequest request) {
        CommonResponse commonResDto = new CommonResponse(ResultCode.UNSUPPORTED_MEDIA_TYPE);
        return commonResDto;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final CommonResponse NoHandlerFoundException(HttpServletRequest request, Exception e) {
        CommonResponse commonResDto = new CommonResponse(ResultCode.NOT_FOUND);
        return commonResDto;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final CommonResponse handleException(HttpServletRequest request, Exception exception) {
        CommonResponse commonResDto = new CommonResponse(ResultCode.INTERNAL);
        return commonResDto;
    }
}
