package kr.co.gallery_jwt_jpa.account;

import kr.co.gallery_jwt_jpa.account.model.AccountJoinReq;
import kr.co.gallery_jwt_jpa.account.model.AccountLoginReq;
import kr.co.gallery_jwt_jpa.account.model.AccountLoginRes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
    int save(AccountJoinReq req);
    AccountLoginRes findByLoginId(AccountLoginReq req);
}
