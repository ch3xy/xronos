package com.ch3xy.xronos.timetracking.service;

import com.ch3xy.xronos.timetracking.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item getItem(Long id) {
        return itemRepository.findOne(id);
    }
}
