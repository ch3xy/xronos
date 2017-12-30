package com.ch3xy.xronos.timetracking.model;

import java.util.ArrayList;
import java.util.List;

public class TimeTrackingInfo {

    private int vacationDays;

    private float overtimeCarryover;

    private List<Day> days;

    private TimeTrackingInfo(Builder builder) {
        this.vacationDays = builder.vacationDays;
        this.overtimeCarryover = builder.overTimeCarryOver;
        this.days = builder.days;
    }

    public int getVacationDays() {
        return vacationDays;
    }

    public float getOvertimeCarryover() {
        return overtimeCarryover;
    }

    public List<Day> getDays() {
        return days;
    }

    public static class Builder {

        private int vacationDays;
        private float overTimeCarryOver;
        private List<Day> days = new ArrayList<>();

        public Builder vacationDays(int vacationDays) {
            this.vacationDays = vacationDays;
            return this;
        }

        public Builder overTimeCarryOver(float overTimeCarryOver) {
            this.overTimeCarryOver = overTimeCarryOver;
            return this;
        }

        public Builder days(List<Day> days) {
            this.days = new ArrayList<>(days);
            return this;
        }

        public TimeTrackingInfo build() {
            return new TimeTrackingInfo(this);
        }
    }
}
