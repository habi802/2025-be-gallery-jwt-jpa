package kr.co.gallery_jwt_jpa.cart;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.gallery_jwt_jpa.account.etc.AccountConstants;
import kr.co.gallery_jwt_jpa.cart.model.CartDeleteReq;
import kr.co.gallery_jwt_jpa.cart.model.CartGetRes;
import kr.co.gallery_jwt_jpa.cart.model.CartPostReq;
import kr.co.gallery_jwt_jpa.config.model.UserPrincipal;
import kr.co.gallery_jwt_jpa.config.util.HttpUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CartPostReq req, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        log.info("req: {}", req);
        //long logginedMemberId = (int) HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        //req.setMemberId(logginedMemberId);
        req.setMemberId(userPrincipal.getMemberId());
        int result = cartService.save(req);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<?> findAll(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        //long logginedMemberId = (int) HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        long logginedMemberId = userPrincipal.getMemberId();
        log.info("memberId: {}", logginedMemberId);
        List<CartGetRes> result = cartService.findAll(logginedMemberId);
        log.info("result: {}", result);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<?> deleteMemberItem(@PathVariable int cartId, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        //long logginedMemberId = (int) HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        long logginedMemberId = userPrincipal.getMemberId();
        CartDeleteReq req = new CartDeleteReq(cartId, logginedMemberId);
        int result = cartService.removeItem(req);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteMemberCart(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        //long logginedMemberId = (int) HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        long logginedMemberId = userPrincipal.getMemberId();
        int result = cartService.removeCart(logginedMemberId);
        return ResponseEntity.ok(result);
    }
}
