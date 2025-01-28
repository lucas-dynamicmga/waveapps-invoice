package com.gottlieb.sample.service.dto.motorCarrier;

public class MotorCarrierGeneralStatus {

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
