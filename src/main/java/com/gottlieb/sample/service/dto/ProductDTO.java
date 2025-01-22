package com.gottlieb.sample.service.dto;

public class ProductDTO {
    private String name;
    private String description;
    private String generalLedgerId;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGeneralLedgerId() {
        return this.generalLedgerId;
    }

    public void setGeneralLedgerId(String generalLedgerId) {
        this.generalLedgerId = generalLedgerId;
    }    

}
