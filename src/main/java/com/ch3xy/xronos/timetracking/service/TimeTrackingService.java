package com.ch3xy.xronos.timetracking.service;

import com.ch3xy.xronos.auth.model.User;
import com.ch3xy.xronos.timetracking.model.Day;
import com.ch3xy.xronos.timetracking.model.TimeTrackingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TimeTrackingService {

    private DayService dayService;

    @Autowired
    public TimeTrackingService(DayService dayService) {
        this.dayService = dayService;
    }

    public TimeTrackingInfo getInfo(User user, LocalDate from, LocalDate to) {
        List<Day> days = dayService.getDaysForUserBetween(user, from, to);
        return new TimeTrackingInfo.Builder()
                .days(days)
                .build();
    }
}
