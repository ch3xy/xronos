package com.ch3xy.xronos.api.timetracking;

import com.ch3xy.xronos.api.timetracking.model.DayTO;
import com.ch3xy.xronos.api.timetracking.model.ItemTO;
import com.ch3xy.xronos.auth.model.User;
import com.ch3xy.xronos.auth.service.UserService;
import com.ch3xy.xronos.timetracking.model.Day;
import com.ch3xy.xronos.timetracking.model.Item;
import com.ch3xy.xronos.timetracking.service.DayService;
import com.ch3xy.xronos.timetracking.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DayMapper {

    private DayService dayService;

    private ItemService itemService;

    private UserService userService;

    @Autowired
    public DayMapper(DayService dayService,
                     ItemService itemService,
                     UserService userService) {
        this.dayService = dayService;
        this.itemService = itemService;
        this.userService = userService;
    }

    public Day map(DayTO dayTO, String userName) {
        User user = userService.getUser(userName);
        Day day = new Day();
        if (dayTO.getId() != null) {
            day = dayService.getDay(dayTO.getId());
        }
        day.setId(dayTO.getId());
        day.setUser(user);
        day.setDate(dayTO.getDate());
        day.setTargetWorkHours(dayTO.getTargetWorkHours());
        day.setType(dayTO.getType());
        day.setItems(mapItems(dayTO.getItems(), day));
        return day;
    }

    private List<Item> mapItems(List<ItemTO> itemTOS, Day day) {
        return itemTOS.stream()
                .map(i -> mapItem(i, day))
                .collect(Collectors.toList());
    }

    private Item mapItem(ItemTO itemTO, Day day) {
        Item item = new Item();
        if (itemTO.getId() != null) {
            item = itemService.getItem(itemTO.getId());
        }
        item.setDay(day);
        item.setId(itemTO.getId());
        item.setDateFrom(itemTO.getFrom());
        item.setDateTo(itemTO.getTo());
        item.setNotes(itemTO.getNotes());
        return item;
    }

    public List<DayTO> mapDays(List<Day> days) {
        return days.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public DayTO map(Day day) {
        return new DayTO.Builder()
                .id(day.getId())
                .date(day.getDate())
                .type(day.getType())
                .targetWorkHours(day.getTargetWorkHours())
                .items(mapItems(day.getItems()))
                .build();
    }

    private List<ItemTO> mapItems(List<Item> items) {
        return items.stream()
                .map(this::mapItem)
                .collect(Collectors.toList());
    }

    private ItemTO mapItem(Item item) {
        return new ItemTO.Builder()
                .id(item.getId())
                .from(item.getDateFrom())
                .to(item.getDateTo())
                .notes(item.getNotes())
                .build();
    }
}
