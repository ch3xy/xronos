package com.ch3xy.xronos.api.timetracking.model;

import com.ch3xy.xronos.timetracking.model.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DayTO {

    private Long id;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull
    private Type type;

    private float targetWorkHours;

    @Valid
    private List<ItemTO> items;

    private DayTO() {
        //private Default constructor
    }

    private DayTO(Builder builder) {
        this.id = builder.id;
        this.date = builder.date;
        this.type = builder.type;
        this.targetWorkHours = builder.targetWorkHours;
        this.items = builder.items;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Type getType() {
        return type;
    }

    public float getTargetWorkHours() {
        return targetWorkHours;
    }

    public List<ItemTO> getItems() {
        return items;
    }

    public static class Builder {
        private Long id;
        private LocalDate date;
        private Type type;
        private float targetWorkHours;
        private List<ItemTO> items = new ArrayList<>();

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder type(Type type) {
            this.type = type;
            return this;
        }

        public Builder targetWorkHours(float targetWorkHours) {
            this.targetWorkHours = targetWorkHours;
            return this;
        }

        public Builder items(List<ItemTO> items) {
            this.items = items;
            return this;
        }

        public DayTO build() {
            return new DayTO(this);
        }
    }
}
