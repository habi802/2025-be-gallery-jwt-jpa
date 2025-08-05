package kr.co.gallery_jwt_jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
@Embeddable // 포함될 수 있는..
@AllArgsConstructor
@NoArgsConstructor
public class MembersRolesIds implements Serializable { // 복합키 역할의 class는 Serializable을 implements 해야 한다.
    private long memberId;

    @Column(length = 20)
    private String roleName;

    public MembersRolesIds(String roleName) {
        this.roleName = roleName;
    }
}
