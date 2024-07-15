package jpa.util;

public final class Consts {

    /** 검색 구분자**/
    public final static String SEARCH_COND_SUB_SEPARATOR = "--";

    public final static String SEARCH_TYPE_ST001 = "ST001";
    public final static String SEARCH_TYPE_ST002 = "ST002";
    public final static String SEARCH_TYPE_ST003 = "ST003";
    public final static String SEARCH_TYPE_ST004 = "ST004";
    public final static String SEARCH_TYPE_ST005 = "ST005";

    /** 검색 정렬 타입 **/
    public final static String SEARCH_SORT_TYPE_REG_DT_DESC = "SS001";
    public final static String SEARCH_SORT_TYPE_USER_UID_ASC = "SS002";

    /** 사용자 유형 정의 */
    public final static String USER_TYPE_ADMIN = "A"; //관리자

    /** 사용자 상태 */
    public final static String USER_STATUS_STAND_BY = "B1";	//대기
    public final static String USER_STATUS_DELETE = "D";	//삭제

    /** 페이지별 게시글 수 */
    public final static int DEFAULT_PAGE_MAX_COUNT = 10;
}