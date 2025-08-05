package kr.co.gallery_jwt_jpa.cart;

import kr.co.gallery_jwt_jpa.cart.model.CartDeleteReq;
import kr.co.gallery_jwt_jpa.cart.model.CartGetRes;
import kr.co.gallery_jwt_jpa.cart.model.CartPostReq;
import kr.co.gallery_jwt_jpa.entity.Carts;
import kr.co.gallery_jwt_jpa.entity.Items;
import kr.co.gallery_jwt_jpa.entity.Members;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartMapper cartMapper;
    private final CartRepository cartRepository;

    public void save(CartPostReq req) {
        try {
            //return cartMapper.save(req);
            Members members = new Members();
            members.setId(req.getMemberId());

            Items items = new Items();
            items.setId(req.getItemId());

            Carts carts = new Carts();
            carts.setMembers(members);
            carts.setItems(items);

            cartRepository.save(carts);
        } catch (DuplicateKeyException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 장바구니에 등록되어 있습니다.");
        }
    }

    public List<CartGetRes> findAll(long memberId) {
        return cartMapper.findAllWithItemByMemberId(memberId);
    }

    public int removeItem(CartDeleteReq req) {
        return cartMapper.deleteByCartIdAndMemberId(req);
    }

    public int removeCart(long memberId) {
        return cartMapper.deleteByMemberId(memberId);
    }
}
