package kr.co.gallery_jwt_jpa.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.co.gallery_jwt_jpa.config.model.JwtUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountLoginRes {
    private long id;
    @JsonIgnore
    private String loginPw;
    @JsonIgnore
    private JwtUser jwtUser;
}
