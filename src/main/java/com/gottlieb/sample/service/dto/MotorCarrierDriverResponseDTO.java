package com.gottlieb.sample.service.dto;

public class MotorCarrierDriverResponseDTO {

    private String licSt;
    private Integer insp;
    private Integer vios;
    private Integer driverVios;
    private Integer oos;
    private Integer timeSpent;
    private Integer crashes;
    private Integer inj;
    private Integer fat;
    private Integer tow;

    public String getLicSt() {
        return this.licSt;
    }

    public void setLicSt(String licSt) {
        this.licSt = licSt;
    }

    public Integer getInsp() {
        return this.insp;
    }

    public void setInsp(Integer insp) {
        this.insp = insp;
    }

    public Integer getVios() {
        return this.vios;
    }

    public void setVios(Integer vios) {
        this.vios = vios;
    }

    public Integer getDriverVios() {
        return this.driverVios;
    }

    public void setDriverVios(Integer driverVios) {
        this.driverVios = driverVios;
    }

    public Integer getOos() {
        return this.oos;
    }

    public void setOos(Integer oos) {
        this.oos = oos;
    }

    public Integer getTimeSpent() {
        return this.timeSpent;
    }

    public void setTimeSpent(Integer timeSpent) {
        this.timeSpent = timeSpent;
    }

    public Integer getCrashes() {
        return this.crashes;
    }

    public void setCrashes(Integer crashes) {
        this.crashes = crashes;
    }

    public Integer getInj() {
        return this.inj;
    }

    public void setInj(Integer inj) {
        this.inj = inj;
    }

    public Integer getFat() {
        return this.fat;
    }

    public void setFat(Integer fat) {
        this.fat = fat;
    }

    public Integer getTow() {
        return this.tow;
    }

    public void setTow(Integer tow) {
        this.tow = tow;
    }
}
