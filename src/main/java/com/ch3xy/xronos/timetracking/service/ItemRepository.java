package com.ch3xy.xronos.timetracking.service;

import com.ch3xy.xronos.timetracking.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
