package kr.co.gallery_jwt_jpa.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemMapper orderItemMapper;
}
