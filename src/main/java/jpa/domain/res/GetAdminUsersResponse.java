package jpa.domain.res;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class GetAdminUsersResponse {

    @JsonProperty("total_count")
    private Long totalCount;

    @JsonProperty("next_cursor")
    private String nextCursor;

    @JsonProperty("users")
    private List<GetAdminUserResponse> users;
}
