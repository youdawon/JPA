package jpa.util;

import org.apache.commons.lang3.StringUtils;

public class PaginationUtil {

    public static int getOffSet(String cursor){

        int offset = 0;

        if(!StringUtils.isEmpty(cursor) && cursor.startsWith("PAGE@@")){
            offset = Integer.parseInt(cursor.replace("PAGE@@", ""));
        }
        return offset;
    }
}
