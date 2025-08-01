package kr.co.gallery_jwt_jpa.cart;

import kr.co.gallery_jwt_jpa.cart.model.CartDeleteReq;
import kr.co.gallery_jwt_jpa.cart.model.CartGetRes;
import kr.co.gallery_jwt_jpa.cart.model.CartPostReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartMapper cartMapper;

    public int save(CartPostReq req) {
        return cartMapper.save(req);
    }

    public List<CartGetRes> findAll(int memberId) {
        return cartMapper.findAllWithItemByMemberId(memberId);
    }

    public int removeItem(CartDeleteReq req) {
        return cartMapper.deleteByCartIdAndMemberId(req);
    }

    public int removeCart(int memberId) {
        return cartMapper.deleteByMemberId(memberId);
    }
}
