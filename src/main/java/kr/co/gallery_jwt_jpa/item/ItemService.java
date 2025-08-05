package kr.co.gallery_jwt_jpa.item;

import kr.co.gallery_jwt_jpa.entity.Items;
import kr.co.gallery_jwt_jpa.item.model.ItemGetRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public List<ItemGetRes> findAll(List<Long> ids){
        log.info("ids: {}", ids);

        List<Items> list = null;
        if (ids == null || ids.isEmpty()) {
            list = itemRepository.findAll();
        } else {
            list = itemRepository.findAllByIdIn(ids);
        }

        List<ItemGetRes> resultList = new ArrayList<>();

        for (Items item : list) {
            ItemGetRes itemGetRes = new ItemGetRes(item.getId(), item.getName(), item.getImgPath(), item.getPrice(), item.getDiscountPer());
            resultList.add(itemGetRes);
        }

        return resultList;
    }
}
