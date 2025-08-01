package kr.co.gallery_jwt_jpa.order;

import kr.co.gallery_jwt_jpa.order.model.OrderDetailGetReq;
import kr.co.gallery_jwt_jpa.order.model.OrderDetailGetRes;
import kr.co.gallery_jwt_jpa.order.model.OrderGetRes;
import kr.co.gallery_jwt_jpa.order.model.OrderPostDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    int save(OrderPostDto dto);
    List<OrderGetRes> findAllByMemberIdOrderByIdDesc(int memberId);
    OrderDetailGetRes findByOrderIdAndMemberId(OrderDetailGetReq req);
}
