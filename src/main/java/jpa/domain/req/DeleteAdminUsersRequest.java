package jpa.domain.req;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class DeleteAdminUsersRequest {

    @JsonProperty("uids")
    @NotEmpty
    private List<String> uids;
}
