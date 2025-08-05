package kr.co.gallery_jwt_jpa.cart;

import kr.co.gallery_jwt_jpa.entity.Carts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Carts, Long> {
}
