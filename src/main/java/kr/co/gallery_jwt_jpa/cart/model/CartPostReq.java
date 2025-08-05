package kr.co.gallery_jwt_jpa.cart.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartPostReq {
    private long memberId;
    private int itemId;
}
