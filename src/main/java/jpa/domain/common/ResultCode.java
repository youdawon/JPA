package jpa.domain.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {

    //200
    OK_SUCCESS("20000000", "요청 성공"),
    OK_ALREADY_PROCESSED("20000001", "이미 처리됨"),
    OK_EXISTS("20000002", "이미 등록된 사용자"),
    OK_EXPIRE("20000006", "인증시간 만료"),

    //400
    BAD_REQUEST("40000000", "BAD REQUEST, 클라이언트의 잘못된 요청으로 처리할 수 없음"),
    BAD_REQUEST_INVALID_HEADER("40000001", "유효하지 않은 HTTP HEADER"),
    BAD_REQUEST_INVALID_PARAM("40000002", "유효하지 않은 PARAMETER"),
    BAD_REQUEST_DUPLICATED_PARAM("40000003", "중복 파라미터"),

    //401
    UNAUTHORIZED("40100000", "인증 실패"),
    UNAUTHORIZED_NO_PERMISSION("40100001", "인증 실패 - 권한 없음"),
    UNAUTHORIZED_EXPIRED("40100002", "인증 실패 - 토큰 만료"),
    UNAUTHORIZED_NEED_REGIST("40100003", "인증 실패 - NCAS 미등록 STB"),
    UNAUTHORIZED_NOT_REGISTED_UID("40100004", "인증 실패 - 미가입자"),
    UNAUTHORIZED_FAIL_AUTH_NCAS("40100005", "인증 실패 - NCAS 연동 실패"),
    UNAUTHORIZED_MISSMATCH_PASSWD("40100006", "인증 실패 - 비밀번호 불일치"),
    UNAUTHORIZED_MISSMATCH_AUTHCODE("40100007", "인증 실패 - 인증코드 불일치"),
    UNAUTHORIZED_EXCEED_COUNT_AUTHCODE("40100008", "인증 실패 - 인증시도 횟수 초과"),
    UNAUTHORIZED_EXPIRED_AUTHCODE_TIME("40100009", "인증 실패 - 인증 시간 만료"),
    UNAUTHORIZED_SMS_LEGACY_FAIL("40100010", "인증 실패 - LEGACY SMS 연동 에러"),
    UNAUTHORIZED_SMS_LEGACY_LOCKED_USER("40100011", "인증 실패 - LEGACY SMS 잠긴 사용자"),
    UNAUTHORIZED_EXCEED_LOGIN_COUNT("40100012", "인증 실패 - 로그인 시도 횟수 초과"),
    UNAUTHORIZED_STANDBY("40100013", "인증 실패 - 대기 상태"),
    UNAUTHORIZED_LOCKED_USER("40100014", "인증 실패 - 관리자 차단"),
    UNAUTHORIZED_SAME_PASSWD_NOT_AVAILABLE("40100015", "인증 실패 - 동일한 비밀번호로 변경불가"),

    //404
    NOT_FOUND("40400000", "서버에 요청을 수행할 수 있는 기능 없음(URL)"),
    NOT_FOUND_ODATA("40400001", "정보 없음"),

    //405
    METHOD_NOT_ALLOWED("40500000", "서버에 요청을 수행할 수 있는 기능 없음(METHOD)"),

    //415
    UNSUPPORTED_MEDIA_TYPE("41500000", "CONTENT-TYPE 유효하지 않음(APPLICATION/JSON만 허용)"),

    //500
    INTERNAL("50000000", "internal exception"),
    MAINTENANCE("50000001", "작업공지"),
    DATABASE_CONNECTION("50010000", "DB CONNECTION 실패"),
    DATABASE_SAVE("50010001", "DB 관련 실패 – INSERT, UPDATE 에러"),
    DATABASE_SELECT("50010002", "DB 관련 실패 – SELECT 에러"),
    DATABASE_DELETE("50010003", "DB 관련 에러 – DELETE 에러");

    private String code;
    private String desc;
}
