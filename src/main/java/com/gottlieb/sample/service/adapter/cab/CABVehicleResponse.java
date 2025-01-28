package com.gottlieb.sample.service.adapter.cab;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gottlieb.sample.service.dto.motorCarrier.MotorCarrierVehicleNonPU;
import com.gottlieb.sample.service.dto.motorCarrier.MotorCarrierVehiclePU;
import java.util.ArrayList;

public class CABVehicleResponse {

    @JsonProperty("PU")
    public ArrayList<MotorCarrierVehiclePU> pu;

    public ArrayList<MotorCarrierVehicleNonPU> nonPU;
    public ArrayList<Object> unknown;

    public ArrayList<MotorCarrierVehiclePU> getPu() {
        return this.pu;
    }

    public void setPu(ArrayList<MotorCarrierVehiclePU> pu) {
        this.pu = pu;
    }

    public ArrayList<MotorCarrierVehicleNonPU> getNonPU() {
        return this.nonPU;
    }

    public void setNonPU(ArrayList<MotorCarrierVehicleNonPU> nonPU) {
        this.nonPU = nonPU;
    }

    public ArrayList<Object> getUnknown() {
        return this.unknown;
    }

    public void setUnknown(ArrayList<Object> unknown) {
        this.unknown = unknown;
    }
}
