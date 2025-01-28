package com.gottlieb.sample.service.dto.motorCarrier;

import java.util.List;

public class MotorCarrierGeneralInsurance {

    private List<MotorCarrierGeneralHistory> history;

    public List<MotorCarrierGeneralHistory> getHistory() {
        return this.history;
    }

    public void setHistory(List<MotorCarrierGeneralHistory> history) {
        this.history = history;
    }
}
