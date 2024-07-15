package jpa.database.auth.entity;

import jpa.database.auth.entity.common.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = AdminUser.TABLE_NAME)
@ToString
@Getter
@Setter
public class AdminUser extends BaseTimeEntity {

    public static final String TABLE_NAME = "admin_user";

    /**
     * camel -> snake 자동 변환 미설정 시 아래와 같이 컬럼별로 DB 필드명 명시 필요
     * 예)
     * @Column(name="user_id")
     * private String userUid;
     */
    @Id
    private String userUid;
    private String userPasswd;
    private String userType;
    private String userName;
    private String userNickname;
    private String companyName;
    private String phoneNumber;
    private String userEmail;
    private String userStatus;
    private Timestamp loginDt;
    private Long passwdFailCnt;
    private Timestamp passwdExpireDt;
 }