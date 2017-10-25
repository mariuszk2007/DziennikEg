package com.eurogeo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class DziennikAll {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate day;
	private String robota;
	private String uwagi;
	private int month;
	private int year; 
	private String user;
	

	public DziennikAll() {

	}

	public DziennikAll(long id, LocalDate day, String robota, String uwagi, String user) {
		this.id = id;
		this.day = day;
		this.robota = robota;
		this.setUwagi(uwagi);
		this.month = day.getMonthValue();
		this.year = day.getYear();
		this.user=user;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@JsonSerialize(using = SerializedLocalDate.class)
	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}

	public String getRobota() {
		return robota;
	}

	public void setRobota(String robota) {
		this.robota = robota;
	}

	public String getUwagi() {
		return uwagi;
	}

	public void setUwagi(String uwagi) {
		this.uwagi = uwagi;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Dziennik [id=" + id + ", day=" + day + ", robota=" + robota + "]";
	}

}
