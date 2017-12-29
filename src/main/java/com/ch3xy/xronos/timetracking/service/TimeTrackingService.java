package com.ch3xy.xronos.timetracking.service;

import com.ch3xy.xronos.auth.model.User;
import com.ch3xy.xronos.timetracking.model.Day;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimeTrackingService {

    private DayService dayService;

    @Autowired
    public TimeTrackingService(DayService dayService) {
        this.dayService = dayService;
    }

    public List<Day> getDaysFor(User user, Date from, Date to) {
        return new ArrayList<>();
    }
}
