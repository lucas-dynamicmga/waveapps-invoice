package com.gottlieb.sample.service.dto.motorCarrier;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MotorCarrierVehicleNonPU {

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
    public String vehicleType;
    public String driveType;
    public String axles;
    public String bodyType;

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

    public int getModelYear() {
        return this.modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public String getVehicleType() {
        return this.vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getDriveType() {
        return this.driveType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    public String getAxles() {
        return this.axles;
    }

    public void setAxles(String axles) {
        this.axles = axles;
    }

    public String getBodyType() {
        return this.bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }
}
