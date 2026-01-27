package com.apple.shop.item;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> list(){
        List<Item> result = itemRepository.findAll();
        return result;
    }

    public void saveItem(String title, Integer price, String image){

        if (title.length() > 100){
            throw new RuntimeException("너무 김");
        }

        if(price < 0){
            throw new RuntimeException("음수 안됩니다");
        }

        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        item.setImage(image);
        itemRepository.save(item);
    }

    public Optional<Item> detail(Long id){
        Optional<Item> result = itemRepository.findById(id);
        return result;

    }

    public void editItem(Long id, String title, Integer price){

        if (title.length() > 100){
            throw new RuntimeException("너무 김");
        }

        if(price < 0){
            throw new RuntimeException("음수 안됩니다");
        }

        Item item = new Item();
        item.setId(id);
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
    }

    public void deleteItem(Long id){
        itemRepository.deleteById(id);

    }

    public Page<Item> getListPage(Integer pages){

        Page<Item> result = itemRepository.findPageBy(PageRequest.of(pages-1, 5));

        return result;
    }
}
