package kr.co.gallery_jwt_jpa.account;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.gallery_jwt_jpa.account.etc.AccountConstants;
import kr.co.gallery_jwt_jpa.account.model.AccountJoinReq;
import kr.co.gallery_jwt_jpa.account.model.AccountLoginReq;
import kr.co.gallery_jwt_jpa.account.model.AccountLoginRes;
import kr.co.gallery_jwt_jpa.config.jwt.JwtTokenManager;
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
    private final JwtTokenManager jwtTokenManager;

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
    public ResponseEntity<?> login(@RequestBody AccountLoginReq req, HttpServletResponse response) {
        AccountLoginRes result = accountService.login(req);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }

        // 세션 처리
        //HttpUtils.setSession(httpReq, AccountConstants.MEMBER_ID_NAME, result.getId());
        jwtTokenManager.issue(response, result.getJwtUser());

        return ResponseEntity.ok(result);
    }

//    @GetMapping("/check")
//    public ResponseEntity<?> check(HttpServletRequest httpReq) {
//        Integer id = (Integer) HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
//        log.info("id={}", id);
//        return ResponseEntity.ok(id);
//    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        jwtTokenManager.reissue(request, response);
        return ResponseEntity.ok(1);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        //HttpUtils.removeSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        jwtTokenManager.logout(response);
        return ResponseEntity.ok(1);
    }
}
