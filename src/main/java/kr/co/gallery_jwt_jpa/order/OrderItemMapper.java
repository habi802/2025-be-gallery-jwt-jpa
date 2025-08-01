package kr.co.gallery_jwt_jpa.order;

import kr.co.gallery_jwt_jpa.order.model.OrderItemPostDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderItemMapper {
    int save(OrderItemPostDto dto);
}
