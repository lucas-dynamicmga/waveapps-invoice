package com.gottlieb.sample.service.dto.motorCarrier;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MotorCarrierVehiclePU {

    @JsonProperty("VIN")
    public String vIN;

    public Integer crashes;
    public Integer inspections;
    public Integer oosInspections;
    public Integer oosViolations;
    public Integer violations;
    public Integer severeViolations;
    public String firstUsed;
    public String lastUsed;
    public boolean isTexas;
    public boolean isValid;
    public String manufacturer;
    public String make;
    public String model;
    public Integer modelYear;

    @JsonProperty("GVWR")
    public String gVWR;

    public String vehicleType;
    public String engineModel;
    public String driveType;
    public String bodyType;
    public Integer totalDOTs_prior;
    public Integer totalDOTs_during;
    public Integer totalDOTs_after;
    public Integer totalDOTs;
    public String engineManufacturer;

    public String getVIN() {
        return this.vIN;
    }

    public void setVIN(String vIN) {
        this.vIN = vIN;
    }

    public Integer getCrashes() {
        return this.crashes;
    }

    public void setCrashes(Integer crashes) {
        this.crashes = crashes;
    }

    public Integer getInspections() {
        return this.inspections;
    }

    public void setInspections(Integer inspections) {
        this.inspections = inspections;
    }

    public Integer getOosInspections() {
        return this.oosInspections;
    }

    public void setOosInspections(Integer oosInspections) {
        this.oosInspections = oosInspections;
    }

    public Integer getOosViolations() {
        return this.oosViolations;
    }

    public void setOosViolations(Integer oosViolations) {
        this.oosViolations = oosViolations;
    }

    public Integer getViolations() {
        return this.violations;
    }

    public void setViolations(Integer violations) {
        this.violations = violations;
    }

    public Integer getSevereViolations() {
        return this.severeViolations;
    }

    public void setSevereViolations(Integer severeViolations) {
        this.severeViolations = severeViolations;
    }

    public String getFirstUsed() {
        return this.firstUsed;
    }

    public void setFirstUsed(String firstUsed) {
        this.firstUsed = firstUsed;
    }

    public String getLastUsed() {
        return this.lastUsed;
    }

    public void setLastUsed(String lastUsed) {
        this.lastUsed = lastUsed;
    }

    public boolean isIsTexas() {
        return this.isTexas;
    }

    public boolean getIsTexas() {
        return this.isTexas;
    }

    public void setIsTexas(boolean isTexas) {
        this.isTexas = isTexas;
    }

    public boolean isIsValid() {
        return this.isValid;
    }

    public boolean getIsValid() {
        return this.isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMake() {
        return this.make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getModelYear() {
        return this.modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public String getGVWR() {
        return this.gVWR;
    }

    public void setGVWR(String gVWR) {
        this.gVWR = gVWR;
    }

    public String getVehicleType() {
        return this.vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getEngineModel() {
        return this.engineModel;
    }

    public void setEngineModel(String engineModel) {
        this.engineModel = engineModel;
    }

    public String getDriveType() {
        return this.driveType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    public String getBodyType() {
        return this.bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public Integer getTotalDOTs_prior() {
        return this.totalDOTs_prior;
    }

    public void setTotalDOTs_prior(Integer totalDOTs_prior) {
        this.totalDOTs_prior = totalDOTs_prior;
    }

    public Integer getTotalDOTs_during() {
        return this.totalDOTs_during;
    }

    public void setTotalDOTs_during(Integer totalDOTs_during) {
        this.totalDOTs_during = totalDOTs_during;
    }

    public Integer getTotalDOTs_after() {
        return this.totalDOTs_after;
    }

    public void setTotalDOTs_after(Integer totalDOTs_after) {
        this.totalDOTs_after = totalDOTs_after;
    }

    public Integer getTotalDOTs() {
        return this.totalDOTs;
    }

    public void setTotalDOTs(Integer totalDOTs) {
        this.totalDOTs = totalDOTs;
    }

    public String getEngineManufacturer() {
        return this.engineManufacturer;
    }

    public void setEngineManufacturer(String engineManufacturer) {
        this.engineManufacturer = engineManufacturer;
    }
}
