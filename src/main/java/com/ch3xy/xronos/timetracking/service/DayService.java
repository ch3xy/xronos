package com.ch3xy.xronos.timetracking.service;

import com.ch3xy.xronos.auth.model.User;
import com.ch3xy.xronos.timetracking.model.Day;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DayService {

    private DayRepository dayRepository;

    @Autowired
    public DayService(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }

    public Day getDay(Long id) {
        return dayRepository.getOne(id);
    }

    @Transactional
    public Day save(Day day) {
        return dayRepository.save(day);
    }

    @Transactional
    public void delete(Day day) {
        dayRepository.delete(day);
    }

    public List<Day> getDaysForUserBetween(User user, LocalDate from, LocalDate to) {
        return dayRepository.getDaysForUserBetween(user, from, to);
    }
}
