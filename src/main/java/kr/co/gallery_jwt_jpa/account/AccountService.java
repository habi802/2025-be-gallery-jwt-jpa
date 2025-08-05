package kr.co.gallery_jwt_jpa.account;

import kr.co.gallery_jwt_jpa.account.model.AccountJoinReq;
import kr.co.gallery_jwt_jpa.account.model.AccountLoginReq;
import kr.co.gallery_jwt_jpa.account.model.AccountLoginRes;
import kr.co.gallery_jwt_jpa.config.model.JwtUser;
import kr.co.gallery_jwt_jpa.entity.Members;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;

    public void join(AccountJoinReq req) {
        String hashedPw = BCrypt.hashpw(req.getLoginPw(), BCrypt.gensalt());

        Members members = new Members();
        members.setName(req.getName());
        members.setLoginId(req.getLoginId());
        members.setLoginPw(hashedPw);

        members.addRole("ROLE_USER_1");

        accountRepository.save(members);
    }

    public AccountLoginRes login(AccountLoginReq req) {
        AccountLoginRes res = accountMapper.findByLoginId(req);

        // 비밀번호 체크
        if (res == null || !BCrypt.checkpw(req.getLoginPw(), res.getLoginPw())) {
            return null; // 아이디가 없거나 비밀번호가 다르면 return null; 처리
        }

        // 로그인 성공 시 사용자의 role 가져오기
        List<String> roles = accountMapper.findAllRolesByMemberId(res.getId());
        JwtUser jwtUser = new JwtUser(res.getId(), roles);
        res.setJwtUser(jwtUser);

        return res;
    }
}
