package jpa.database.auth.entity.pk;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class AuthCodePk implements Serializable {

    private Long authCodeKey;

    private Timestamp regDt;
}
