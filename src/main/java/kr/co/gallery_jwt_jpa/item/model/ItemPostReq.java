package kr.co.gallery_jwt_jpa.item.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemPostReq {
    //private int id; //mapper 에서 pk를 가져오기 위해 사용함
    private String name;
    //private String imgPath; //mapper에서 사진 경로 저장하기 위해 사용함
    private int price;
    private int discountPer;
}
