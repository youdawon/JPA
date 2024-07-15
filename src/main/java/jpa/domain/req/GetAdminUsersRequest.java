package jpa.domain.req;

import jpa.util.Consts;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class GetAdminUsersRequest {

    private String searchType;

    private String searchExtra;

    private String cursor;

    private Integer count;

    private String sort;

    public Integer getCount(){
        return count == null ? Consts.DEFAULT_PAGE_MAX_COUNT : count;
    }
}