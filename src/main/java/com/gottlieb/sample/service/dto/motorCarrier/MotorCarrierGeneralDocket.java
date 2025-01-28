package com.gottlieb.sample.service.dto.motorCarrier;

public class MotorCarrierGeneralDocket {

    private String prefix;
    private Integer num;
    private MotorCarrierGeneralInsurance insurance;

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Integer getNum() {
        return this.num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public MotorCarrierGeneralInsurance getInsurance() {
        return this.insurance;
    }

    public void setInsurance(MotorCarrierGeneralInsurance insurance) {
        this.insurance = insurance;
    }
}
