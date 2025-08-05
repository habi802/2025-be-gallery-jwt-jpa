package kr.co.gallery_jwt_jpa.order.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class OrderPostDto {
    private int orderId;
    private long memberId;
    private String name;
    private String address;
    private String payment;
    private String cardNumber;
    private long amount;
}
