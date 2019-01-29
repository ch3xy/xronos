package com.ch3xy.xronos.timetracking.service;

import com.ch3xy.xronos.auth.model.User;
import com.ch3xy.xronos.timetracking.model.Day;
import com.ch3xy.xronos.timetracking.model.Item;
import com.ch3xy.xronos.timetracking.model.TimeTrackingInfo;
import com.ch3xy.xronos.timetracking.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
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
        List<Day> daysBefore = dayService.getDaysForUserBefore(user, to);
        return new TimeTrackingInfo.Builder()
                .days(days)
                .vacationDays(getVacationDays(daysBefore))
                .overTimeCarryOver(getOvertimeCarryover(daysBefore))
                .build();
    }

    private int getVacationDays(List<Day> days) {
        return (int) (25 - days.stream().filter(day -> day.getType() == Type.VACATION).count());
    }

    private float getOvertimeCarryover(List<Day> days) {
        float carryover = 0;
        float target = 0;
        for (Day day : days) {
            target += day.getTargetWorkHours();
            for (Item item : day.getItems()) {
                Duration d = Duration.between(item.getDateFrom(), item.getDateTo());
                carryover += d.getSeconds() / 60.0 / 60.0;
            }
        }
        return target - carryover;
    }
}
