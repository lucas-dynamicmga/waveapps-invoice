package com.gottlieb.sample.service.dto.motorCarrier;

import java.util.List;

public class MotorCarrierGeneralContact {

    private List<MotorCarrierGeneralAddress> addresses;
    private List<MotorCarrierGeneralEmail> emails;
    private List<MotorCarrierGeneralName> names;
    private List<MotorCarrierGeneralPhone> phones;

    // Getters e Setters
    public List<MotorCarrierGeneralAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<MotorCarrierGeneralAddress> addresses) {
        this.addresses = addresses;
    }

    public List<MotorCarrierGeneralEmail> getEmails() {
        return emails;
    }

    public void setEmails(List<MotorCarrierGeneralEmail> emails) {
        this.emails = emails;
    }

    public List<MotorCarrierGeneralName> getNames() {
        return names;
    }

    public void setNames(List<MotorCarrierGeneralName> names) {
        this.names = names;
    }

    public List<MotorCarrierGeneralPhone> getPhones() {
        return phones;
    }

    public void setPhones(List<MotorCarrierGeneralPhone> phones) {
        this.phones = phones;
    }
}
