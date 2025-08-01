package kr.co.gallery_jwt_jpa.account;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.gallery_jwt_jpa.account.etc.AccountConstants;
import kr.co.gallery_jwt_jpa.account.model.AccountJoinReq;
import kr.co.gallery_jwt_jpa.account.model.AccountLoginReq;
import kr.co.gallery_jwt_jpa.account.model.AccountLoginRes;
import kr.co.gallery_jwt_jpa.config.util.HttpUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody AccountJoinReq req) {
        if (!StringUtils.hasLength(req.getName())
            || !StringUtils.hasLength(req.getLoginId())
            || !StringUtils.hasLength(req.getLoginPw())) {
            return ResponseEntity.badRequest().build();
        }

        int result = accountService.join(req);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccountLoginReq req, HttpServletRequest httpReq) {
        AccountLoginRes result = accountService.login(req);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }

        // 세션 처리
        HttpUtils.setSession(httpReq, AccountConstants.MEMBER_ID_NAME, result.getId());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/check")
    public ResponseEntity<?> check(HttpServletRequest httpReq) {
        Integer id = (Integer) HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        log.info("id={}", id);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest httpReq) {
        HttpUtils.removeSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        return ResponseEntity.ok(1);
    }
}
