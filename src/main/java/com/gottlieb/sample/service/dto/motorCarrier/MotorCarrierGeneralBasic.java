package com.gottlieb.sample.service.dto.motorCarrier;

public class MotorCarrierGeneralBasic {

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
