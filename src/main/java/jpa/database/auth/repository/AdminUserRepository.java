package jpa.database.auth.repository;

import jpa.database.auth.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * JpaRepository<entity 명, 테이블 PK 자료형>
 * AdminUser의 경우 PK가 user_uid 이기 때문에 String으로 지정
 * @Transactional(readOnly = true) 지정 시 데이터 조회 시 dirty checking 생략되어 속도 향상
 */
@Transactional(readOnly = true)
public interface AdminUserRepository extends JpaRepository<AdminUser, String>, AdminUserCustomRepository {

    public AdminUser findAdminUserByUserUidAndUserNameAndPhoneNumber(String userUid, String userName, String phoneNumber);

    /**
     * 데이터 변경이 일어날 경우 @Transactional, @Modifying 어노테이션 필수
     * nativeQuery = false (JPQL 사용을 명시하며 테이블에 alias 필수)
     * nativeQuery = true (일반적인 SQL 사용)
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE AdminUser au1 " +
            "SET au1.userStatus = :#{#adminUser.userStatus}, " +
            "au1.passwdFailCnt = :#{#adminUser.passwdFailCnt} " +
            "WHERE au1.userUid = :#{#adminUser.userUid}",
            nativeQuery = false)
    public void updateAdminUserLoginFailCnt(AdminUser adminUser);
}