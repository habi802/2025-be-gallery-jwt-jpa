package kr.co.gallery_jwt_jpa.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = { "member_id", "item_id" }
        )
    }
)
public class Carts extends Created {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Members members;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Items items;
}
