package com.gottlieb.sample.service.adapter.cab;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gottlieb.sample.service.dto.motorCarrier.MotorCarrierVINResultDTO;
import java.util.ArrayList;

public class CABVINResponse {

    @JsonProperty("Count")
    public int count;

    @JsonProperty("Message")
    public String message;

    @JsonProperty("SearchCriteria")
    public String searchCriteria;

    @JsonProperty("Results")
    public ArrayList<MotorCarrierVINResultDTO> results;

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSearchCriteria() {
        return this.searchCriteria;
    }

    public void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public ArrayList<MotorCarrierVINResultDTO> getResults() {
        return this.results;
    }

    public void setResults(ArrayList<MotorCarrierVINResultDTO> results) {
        this.results = results;
    }
}
