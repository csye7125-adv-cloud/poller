package com.neu.poller.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "alert")
public class Alert {
	
	@Id
   String alert_id;
   Date alert_created;
   Date alert_updated;
   @Enumerated(EnumType.STRING)
   FieldType field_type;
   @Enumerated(EnumType.STRING)
   Operator operator;
   int value;
public String getAlert_id() {
	return alert_id;
}
public void setAlert_id(String alert_id) {
	this.alert_id = alert_id;
}

public Date getAlert_created() {
	return alert_created;
}
public void setAlert_created(Date alert_created) {
	this.alert_created = alert_created;
}
public Date getAlert_updated() {
	return alert_updated;
}
public void setAlert_updated(Date alert_updated) {
	this.alert_updated = alert_updated;
}
public FieldType getField_type() {
	return field_type;
}
public void setField_type(FieldType field_type) {
	this.field_type = field_type;
}
public Operator getOperator() {
	return operator;
}
public void setOperator(Operator operator) {
	this.operator = operator;
}
public int getValue() {
	return value;
}
public void setValue(int value) {
	this.value = value;
}
   
}
