package kr.co.gallery_jwt_jpa.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode
public class Members extends Created {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String loginId;

    @Column(nullable = false, length = 100)
    private String loginPw;
}
