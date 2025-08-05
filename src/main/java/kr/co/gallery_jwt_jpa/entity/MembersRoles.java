package kr.co.gallery_jwt_jpa.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode
public class MembersRoles {
    @EmbeddedId
    private MembersRolesIds membersRolesIds;

    // 관계 설정
    // CascadeType.PERSIST: 영속성 전이
    // CascadeType.REMOVE: 부모 영속성이 delete 되면 본인도 delete
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name="member_id") // 컬럼명
    @MapsId("memberId")
    private Members members;
}
