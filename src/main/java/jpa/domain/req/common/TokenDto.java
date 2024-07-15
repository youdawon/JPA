package jpa.domain.req.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
public class TokenDto {

    private String token;
    private String type;
    private String uid;
    private String role;
    private Map<String, Object> profiles;

    public TokenDto() {
        this.profiles = new HashMap<>();
    }
}

