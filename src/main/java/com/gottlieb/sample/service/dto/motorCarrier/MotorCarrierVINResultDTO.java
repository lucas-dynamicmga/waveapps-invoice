package com.gottlieb.sample.service.dto.motorCarrier;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MotorCarrierVINResultDTO {

    @JsonProperty("Value")
    public String value;

    @JsonProperty("ValueId")
    public String valueId;

    @JsonProperty("Variable")
    public String variable;

    @JsonProperty("VariableId")
    public int variableId;

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueId() {
        return this.valueId;
    }

    public void setValueId(String valueId) {
        this.valueId = valueId;
    }

    public String getVariable() {
        return this.variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public int getVariableId() {
        return this.variableId;
    }

    public void setVariableId(int variableId) {
        this.variableId = variableId;
    }
}
