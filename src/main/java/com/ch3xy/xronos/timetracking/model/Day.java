package com.ch3xy.xronos.timetracking.model;

import com.ch3xy.xronos.auth.model.User;
import com.ch3xy.xronos.common.AbstractDomainObject;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class Day extends AbstractDomainObject {

    @ManyToOne
    private User user;

    private Date date;

    @Enumerated(EnumType.STRING)
    private Type type;

    private float targetWorkHours;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "day", orphanRemoval = true)
    private List<Item> items;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public float getTargetWorkHours() {
        return targetWorkHours;
    }

    public void setTargetWorkHours(float targetWorkHours) {
        this.targetWorkHours = targetWorkHours;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items.clear();
        this.items.addAll(items);
    }
}
