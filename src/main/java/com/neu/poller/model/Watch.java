package com.neu.poller.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "watch")
public class Watch {
	
	@Id
	@Column
    String watch_id;
	@Column
    String user_id;
	@Column
    Date watch_created;
	@Column
    Date watch_updated;
	@Column
    String zipcode;
	@OneToMany(
			fetch = FetchType.EAGER,
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
    List<Alert> alerts=new ArrayList<Alert>();
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
	public List<Alert> getAlerts() {
		return alerts;
	}
	public void setAlerts(List<Alert> alerts) {
		this.alerts = alerts;
	}
	@Override
	public String toString() 
    { 
        return watch_id + " " + user_id + " " + zipcode + " " + alerts; 
    }
    
}
