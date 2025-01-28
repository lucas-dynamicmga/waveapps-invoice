package com.gottlieb.sample.service.adapter.cab;

import java.util.List;

public class Scores {

    private List<Basic> basics;

    public List<Basic> getBasics() {
        return this.basics;
    }

    public void setBasics(List<Basic> basics) {
        this.basics = basics;
    }
}

class Basic {

    private String name;
    private double measure;
    private boolean aboveThreshold;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMeasure() {
        return this.measure;
    }

    public void setMeasure(double measure) {
        this.measure = measure;
    }

    public boolean isAboveThreshold() {
        return this.aboveThreshold;
    }

    public boolean getAboveThreshold() {
        return this.aboveThreshold;
    }

    public void setAboveThreshold(boolean aboveThreshold) {
        this.aboveThreshold = aboveThreshold;
    }
}
