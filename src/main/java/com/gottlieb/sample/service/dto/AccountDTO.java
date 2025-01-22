package com.gottlieb.sample.service.dto;

public class AccountDTO {
    private String name;
    private String description;
    private String generalLedgerId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGeneralLedgerId() {
        return generalLedgerId;
    }

    public void setGeneralLedgerId(String generalLedgerId) {
        this.generalLedgerId = generalLedgerId;
    }

}
