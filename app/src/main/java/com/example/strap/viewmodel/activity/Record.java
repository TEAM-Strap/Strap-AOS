package com.example.strap.viewmodel.activity;

public class Record {
    private int setName;
    private String clock;

    public Record(int givenSetName, String givenClock) {
        this.setName = givenSetName;
        this.clock = givenClock;
    }

    public int getSetName() {
        return this.setName;
    }

    public String getClock() {
        return this.clock;
    }
}
