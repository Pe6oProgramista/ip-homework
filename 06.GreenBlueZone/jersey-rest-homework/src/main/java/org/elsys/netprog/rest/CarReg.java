package org.elsys.netprog.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import org.json.JSONObject;

public class CarReg {
	private String regNumber;
	private boolean active;
	private String zone;
	private Calendar due;
	private Calendar lastAct;
	
	private static DateFormat dateFormat;
	
	public CarReg(String regNumber) {
		dateFormat = new SimpleDateFormat("HH:mm:ss z dd/MM/yyyy");
		dateFormat.setTimeZone(TimeZone.getTimeZone("EET"));
		this.regNumber = regNumber;
		this.zone = "";
		this.active = false;
		this.due = Calendar.getInstance();
		this.lastAct = Calendar.getInstance();
	}
	
	public void subscribe(String zone) {
		this.lastAct = Calendar.getInstance();
		
		if(this.zone.equals(zone)) {
			if(this.lastAct.compareTo(this.due) >= 0) {
				this.due = Calendar.getInstance();
			}
		} else {
			this.due = Calendar.getInstance();
		}
		due.add(Calendar.HOUR_OF_DAY, 1);
		this.active = true;
		this.zone = zone;
	}
	
	public JSONObject toJSON() {
		JSONObject myJsonObject = new JSONObject();
		myJsonObject.put("carReg", this.regNumber)
		.put("active", this.active)
		.put("zone", this.zone)
		.put("due", dateFormat.format(this.due.getTime()))
		.put("lastAction", dateFormat.format(this.lastAct.getTime()));
		
		return myJsonObject;
	}
	
	public String getRegNumber() {
		return regNumber;
	}
	
	public boolean getActive() {
		return active;
	}
	
	public String getZone() {
		return zone;
	}
	
	public Calendar getDue() {
		return due;
	}
	
	public Calendar getLastAct() {
		return lastAct;
	}
}
