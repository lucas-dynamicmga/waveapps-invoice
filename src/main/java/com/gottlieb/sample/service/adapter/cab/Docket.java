package com.gottlieb.sample.service.adapter.cab;

import java.util.List;

public class Docket {

    private String prefix;
    private long num;
    private Insurance insurance;

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public long getNum() {
        return this.num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public Insurance getInsurance() {
        return this.insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }
}

class Insurance {

    private List<History> history;

    // Getters e Setters

    public List<History> getHistory() {
        return this.history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }
}

class History {

    private String frmCd;
    private String insType;
    private String closeAction;
    private String polNum;

    public String getFrmCd() {
        return this.frmCd;
    }

    public void setFrmCd(String frmCd) {
        this.frmCd = frmCd;
    }

    public String getInsType() {
        return this.insType;
    }

    public void setInsType(String insType) {
        this.insType = insType;
    }

    public String getCloseAction() {
        return this.closeAction;
    }

    public void setCloseAction(String closeAction) {
        this.closeAction = closeAction;
    }

    public String getPolNum() {
        return this.polNum;
    }

    public void setPolNum(String polNum) {
        this.polNum = polNum;
    }
}
