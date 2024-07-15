package jpa.domain.req;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class UpdateAdminUserRequest {

    @JsonProperty("uid")
    private String uid;

    @JsonProperty("passwd")
    @NotEmpty
    private String passwd;

    @JsonProperty("type")
    @NotEmpty
    private String type;

    @JsonProperty("name")
    @NotEmpty
    private String UserName;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("status")
    private String status;

    @JsonProperty("company_name")
    @NotEmpty
    private String companyName;
}
