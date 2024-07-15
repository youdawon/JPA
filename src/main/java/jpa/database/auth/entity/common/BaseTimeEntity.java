package jpa.database.auth.entity.common;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

/**
 * MappedSuperclass : entity 의 상위 클래스가 되는 class에 지정
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    /**
     * @CreatedDate : insert 시 자동으로 현재시간이 추가됨
     * @LastModifiedDate : update 시 자동으로 현재시간이 추가됨
     *
     * updatable : false
     * 데이터 업데이트 시 해당 컬럼 제외
     */

    @CreatedDate
    @Column(updatable=false)
    private Timestamp regDt;
    @CreatedDate
    @LastModifiedDate
    private Timestamp modDt;
}
