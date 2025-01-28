package com.gottlieb.sample.service.adapter.cab;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gottlieb.sample.service.dto.motorCarrier.MotorCarrierGeneralContact;
import com.gottlieb.sample.service.dto.motorCarrier.MotorCarrierGeneralDocket;
import com.gottlieb.sample.service.dto.motorCarrier.MotorCarrierGeneralMCS150;
import com.gottlieb.sample.service.dto.motorCarrier.MotorCarrierGeneralScores;
import java.util.List;

public class CABGeneralResponse {

    private MotorCarrierGeneralContact contact;

    private List<MotorCarrierGeneralDocket> dockets;

    private MotorCarrierGeneralScores scores;

    @JsonProperty("MCS150")
    private MotorCarrierGeneralMCS150 mcs150;

    @JsonProperty("YIB")
    private Integer yib;

    // Getters e Setters
    public MotorCarrierGeneralContact getContact() {
        return contact;
    }

    public void setContact(MotorCarrierGeneralContact contact) {
        this.contact = contact;
    }

    public Integer getYIB() {
        return yib;
    }

    public void setYIB(Integer yib) {
        this.yib = yib;
    }

    public List<MotorCarrierGeneralDocket> getDockets() {
        return dockets;
    }

    public void setDockets(List<MotorCarrierGeneralDocket> dockets) {
        this.dockets = dockets;
    }

    public MotorCarrierGeneralMCS150 getMcs150() {
        return mcs150;
    }

    public void setMcs150(MotorCarrierGeneralMCS150 mcs150) {
        this.mcs150 = mcs150;
    }

    public MotorCarrierGeneralScores getScores() {
        return scores;
    }

    public void setScores(MotorCarrierGeneralScores scores) {
        this.scores = scores;
    }
}
