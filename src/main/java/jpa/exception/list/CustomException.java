package jpa.exception.list;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class CustomException extends Exception {
	protected String code;
	protected String desc;
}