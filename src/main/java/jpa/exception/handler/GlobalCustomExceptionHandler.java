package jpa.exception.handler;

import jpa.domain.res.common.CommonResponse;
import jpa.exception.list.BadRequestException;
import jpa.exception.list.CustomException;
import jpa.exception.list.InsideException;
import jpa.exception.list.NotFoundException;
import jpa.exception.list.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * custom exception 예외 처리
 *
 */
@Slf4j
@RestControllerAdvice
public class GlobalCustomExceptionHandler {
	
	@ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
	public CommonResponse badRequestException(HttpServletRequest request, CustomException ex) {
		return new CommonResponse(ex);
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public CommonResponse notFoundException(HttpServletRequest request, CustomException ex) {
		return new CommonResponse(ex);
	}

	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public CommonResponse unauthorizedException(HttpServletRequest request, CustomException ex) {
		return new CommonResponse(ex);
	}

	@ExceptionHandler(InsideException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public CommonResponse insideException(HttpServletRequest request, CustomException ex) {
		return new CommonResponse(ex);
	}
}
