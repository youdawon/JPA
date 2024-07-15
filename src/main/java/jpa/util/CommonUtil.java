package jpa.util;

import com.google.common.base.Strings;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

@Component
public final class CommonUtil {

    /**
     * 전달받은 두개의 String을 비교한다
     * @param str1
     * @param str2
     * @return 같을 경우 true, 다를 경우 false.
     */
    public static boolean compareString(String str1, String str2)
    {
        return str1 == null ? str2 == null : str1.equals(str2);
    }
}