package com.example.strap.viewmodel.activity;

public class Routine implements AdapterType {
    private String name;
    private int weight, count, set;
    private int viewType = 0;

    public Routine(String name, int weight, int count, int set) {
        this.name = name;
        this.weight = weight;
        this.count = count;
        this.set = set;
    }

    public String getName() {
        return this.name;
    }

    public int getWeight() {
        return this.weight;
    }

    public int getCount() {
        return this.count;
    }

    public int getSet() {
        return this.set;
    }

    public int getViewType() {
        return this.viewType;
    }
}