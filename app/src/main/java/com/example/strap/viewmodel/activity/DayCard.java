package com.example.strap.viewmodel.activity;

public class DayCard {
    private String day;
    private int date;

    public DayCard(String givenDay, int givenDate) {
        this.day = givenDay;
        this.date = givenDate;
    }

    public String getDay() {
        return day;
    }

    public int getDate() {
        return date;
    }
}
