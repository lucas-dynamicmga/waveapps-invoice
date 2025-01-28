package com.gottlieb.sample.service.dto.motorCarrier;

import java.util.List;

public class MotorCarrierGeneralMCS150 {

    private String date;
    private Integer mileage;
    private String status;
    private List<String> operationClass;
    private boolean passengers;

    // Getters e Setters
    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getMileage() {
        return this.mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getOperationClass() {
        return operationClass;
    }

    public void setOperationClass(List<String> operationClass) {
        this.operationClass = operationClass;
    }

    public boolean isPassengers() {
        return passengers;
    }

    public void setPassengers(boolean passengers) {
        this.passengers = passengers;
    }
}
