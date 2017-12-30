package com.ch3xy.xronos.timetracking.model;

import com.ch3xy.xronos.common.AbstractDomainObject;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalTime;

@Entity
public class Item extends AbstractDomainObject {

    @ManyToOne
    @JoinColumn(name = "day")
    private Day day;

    private LocalTime dateFrom;

    private LocalTime dateTo;

    private String notes;

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public LocalTime getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalTime dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalTime getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalTime dateTo) {
        this.dateTo = dateTo;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
