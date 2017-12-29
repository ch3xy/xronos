package com.ch3xy.xronos.timetracking.service;

import com.ch3xy.xronos.auth.model.User;
import com.ch3xy.xronos.timetracking.model.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface DayRepository extends JpaRepository<Day, Long> {

    @Query(value = "select d from Day d where d.user=?1 and d.date >= ?2 and d.date <= ?3")
    List<Day> getDaysForUserBetween(User user, Date from, Date to);
}
