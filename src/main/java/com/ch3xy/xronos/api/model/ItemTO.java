package com.ch3xy.xronos.api.model;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ItemTO {

    private Long id;

    @NotNull
    private Date from;

    @NotNull
    private Date to;

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

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }

    public String getNotes() {
        return notes;
    }

    public static class Builder {
        private Long id;
        private Date from;
        private Date to;
        private String notes;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder from(Date from) {
            this.from = from;
            return this;
        }

        public Builder to(Date to) {
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
