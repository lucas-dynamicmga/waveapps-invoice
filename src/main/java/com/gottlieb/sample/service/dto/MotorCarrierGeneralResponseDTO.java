package com.gottlieb.sample.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gottlieb.sample.service.dto.motorCarrier.MotorCarrierGeneralContact;
import com.gottlieb.sample.service.dto.motorCarrier.MotorCarrierGeneralDocket;
import com.gottlieb.sample.service.dto.motorCarrier.MotorCarrierGeneralMCS150;
import com.gottlieb.sample.service.dto.motorCarrier.MotorCarrierGeneralScores;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class MotorCarrierGeneralResponseDTO {

    private MotorCarrierGeneralContact contact;

    private MotorCarrierGeneralScores scores;

    private List<MotorCarrierGeneralDocket> dockets;

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

    public MotorCarrierGeneralScores getScores() {
        return scores;
    }

    public void setScores(MotorCarrierGeneralScores scores) {
        this.scores = scores;
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

    public void setMCS150(MotorCarrierGeneralMCS150 mcs150) {
        this.mcs150 = mcs150;
    }

    public Integer getYIB() {
        return yib;
    }

    public void setYIB(Integer yib) {
        this.yib = yib;
    }
}
