package kr.co.gallery_jwt_jpa.account;

import kr.co.gallery_jwt_jpa.account.model.AccountJoinReq;
import kr.co.gallery_jwt_jpa.account.model.AccountLoginReq;
import kr.co.gallery_jwt_jpa.account.model.AccountLoginRes;
import kr.co.gallery_jwt_jpa.config.model.JwtUser;
import kr.co.gallery_jwt_jpa.entity.Members;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
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
        Members members = accountRepository.findByLoginId(req.getLoginId());

        // 비밀번호 체크
        if (members == null || !BCrypt.checkpw(req.getLoginPw(), members.getLoginPw())) {
            return null; // 아이디가 없거나 비밀번호가 다르면 return null; 처리
        }

        // 로그인 성공 시 사용자의 role 가져오기
//        List<String> roles = accountMapper.findAllRolesByMemberId(res.getId());
//        JwtUser jwtUser = new JwtUser(res.getId(), roles);
//        res.setJwtUser(jwtUser);
//
//        return res;
        List<String> roles = members.getRoles()
                .stream()
                .map(item -> item.getMembersRolesIds().getRoleName())
                .collect(Collectors.toList());
        JwtUser jwtUser = new JwtUser(members.getId(), roles);

        AccountLoginRes accountLoginRes = new AccountLoginRes();
        accountLoginRes.setJwtUser(jwtUser);
        accountLoginRes.setId(members.getId());
        log.info("roles: {}", accountLoginRes.getJwtUser().getRoles());

        return accountLoginRes;
    }
}
