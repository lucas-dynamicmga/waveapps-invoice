package com.gottlieb.sample.service.adapter.cab;

public class MCS150 {

    private String date;
    private int mileage;
    private Status status;

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMileage() {
        return this.mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

class Status {

    private String operationClass;
    private boolean passengers;

    public String getOperationClass() {
        return this.operationClass;
    }

    public void setOperationClass(String operationClass) {
        this.operationClass = operationClass;
    }

    public boolean isPassengers() {
        return this.passengers;
    }

    public boolean getPassengers() {
        return this.passengers;
    }

    public void setPassengers(boolean passengers) {
        this.passengers = passengers;
    }
}
