package kr.co.gallery_jwt_jpa.order;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.gallery_jwt_jpa.account.etc.AccountConstants;
import kr.co.gallery_jwt_jpa.config.model.UserPrincipal;
import kr.co.gallery_jwt_jpa.config.util.HttpUtils;
import kr.co.gallery_jwt_jpa.order.model.OrderDetailGetReq;
import kr.co.gallery_jwt_jpa.order.model.OrderDetailGetRes;
import kr.co.gallery_jwt_jpa.order.model.OrderGetRes;
import kr.co.gallery_jwt_jpa.order.model.OrderPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody OrderPostReq req, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        //long logginedMemberId = (int) HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        //log.info("req: {}", req);
        long logginedMemberId = userPrincipal.getMemberId();
        int result = orderService.saveOrder(req, logginedMemberId);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<?> findAll(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        //long logginedMemberId = (int) HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        long logginedMemberId = userPrincipal.getMemberId();
        List<OrderGetRes> result = orderService.findAll(logginedMemberId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> findDetail(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable int orderId) {
        //long logginedMemberId = (int) HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        long logginedMemberId = userPrincipal.getMemberId();
        OrderDetailGetReq req = new OrderDetailGetReq();
        req.setOrderId(orderId);
        req.setMemberId(logginedMemberId);
        OrderDetailGetRes result = orderService.detail(req);
        return ResponseEntity.ok(result);
    }
}
