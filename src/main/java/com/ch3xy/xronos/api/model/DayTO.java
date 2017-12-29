package com.ch3xy.xronos.api.model;

import com.ch3xy.xronos.timetracking.model.Type;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DayTO {

    private Long id;

    @NotNull
    private Date date;

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

    public Date getDate() {
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
        private Date date;
        private Type type;
        private float targetWorkHours;
        private List<ItemTO> items = new ArrayList<>();

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder date(Date date) {
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
