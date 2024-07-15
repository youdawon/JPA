package jpa.domain.res;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class GetAdminUserResponse {

    @JsonProperty("uid")
    private String uid;

    @JsonProperty("type")
    private String type;

    @JsonProperty("name")
    private String UserName;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("reg_dt")
    private Timestamp regDt;

    @JsonProperty("mod_dt")
    private Timestamp modDt;

    @JsonProperty("passwd_expire_dt")
    private Timestamp passwdExpireDt;

    @JsonProperty("login_dt")
    private Timestamp loginDt;

    @JsonProperty("status")
    private String status;

    @JsonProperty("company_name")
    private String companyName;
}