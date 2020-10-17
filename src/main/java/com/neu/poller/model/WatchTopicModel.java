package com.neu.poller.model;

import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WatchTopicModel {


    String watch_id;
    String user_id;
    Date watch_created;
    Date watch_updated;
    String zipcode;
    List<AlertTopicModel> alerts=new ArrayList<AlertTopicModel>();
    String action;

    public String getWatch_id() {
        return watch_id;
    }

    public void setWatch_id(String watch_id) {
        this.watch_id = watch_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Date getWatch_created() {
        return watch_created;
    }

    public void setWatch_created(Date watch_created) {
        this.watch_created = watch_created;
    }

    public Date getWatch_updated() {
        return watch_updated;
    }

    public void setWatch_updated(Date watch_updated) {
        this.watch_updated = watch_updated;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public List<AlertTopicModel> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<AlertTopicModel> alerts) {
        this.alerts = alerts;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
