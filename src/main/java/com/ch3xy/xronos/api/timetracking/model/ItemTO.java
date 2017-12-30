package com.ch3xy.xronos.api.timetracking.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

public class ItemTO {

    private Long id;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime from;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime to;

    private String notes;

    private ItemTO() {
        //private default constructor
    }

    private ItemTO(Builder builder) {
        this.id = builder.id;
        this.from = builder.from;
        this.to = builder.to;
        this.notes = builder.notes;
    }

    public Long getId() {
        return id;
    }

    public LocalTime getFrom() {
        return from;
    }

    public LocalTime getTo() {
        return to;
    }

    public String getNotes() {
        return notes;
    }

    public static class Builder {
        private Long id;
        private LocalTime from;
        private LocalTime to;
        private String notes;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder from(LocalTime from) {
            this.from = from;
            return this;
        }

        public Builder to(LocalTime to) {
            this.to = to;
            return this;
        }

        public Builder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public ItemTO build() {
            return new ItemTO(this);
        }
    }
}
