package com.ch3xy.xronos.api.timetracking.model;

import java.util.ArrayList;
import java.util.List;

public class TimeTrackingInfoTO {

    private int vacationDays;

    private float overtimeCarryover;

    private List<DayTO> days;

    private TimeTrackingInfoTO(Builder builder) {
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

    public List<DayTO> getDays() {
        return days;
    }

    public static class Builder {

        private int vacationDays;
        private float overTimeCarryOver;
        private List<DayTO> days = new ArrayList<>();

        public Builder vacationDays(int vacationDays) {
            this.vacationDays = vacationDays;
            return this;
        }

        public Builder overTimeCarryOver(float overTimeCarryOver) {
            this.overTimeCarryOver = overTimeCarryOver;
            return this;
        }

        public Builder days(List<DayTO> days) {
            this.days = new ArrayList<>(days);
            return this;
        }

        public TimeTrackingInfoTO build() {
            return new TimeTrackingInfoTO(this);
        }
    }
}
