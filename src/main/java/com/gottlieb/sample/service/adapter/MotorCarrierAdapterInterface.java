package com.gottlieb.sample.service.adapter;

import com.gottlieb.sample.service.dto.MotorCarrierDriverResponseDTO;
import com.gottlieb.sample.service.dto.MotorCarrierGeneralResponseDTO;
import com.gottlieb.sample.service.dto.MotorCarrierRequestDTO;
import com.gottlieb.sample.service.dto.MotorCarrierVINRequestDTO;
import com.gottlieb.sample.service.dto.MotorCarrierVINResponseDTO;
import com.gottlieb.sample.service.dto.MotorCarrierVehicleResponseDTO;
import reactor.core.publisher.Mono;

public interface MotorCarrierAdapterInterface {
    Mono<MotorCarrierGeneralResponseDTO> getDOT(MotorCarrierRequestDTO request);
    Mono<MotorCarrierDriverResponseDTO> getDriver(MotorCarrierRequestDTO request);
    Mono<MotorCarrierVehicleResponseDTO> getVehicle(MotorCarrierRequestDTO request);
    Mono<MotorCarrierVINResponseDTO> getVehicleByVIN(MotorCarrierVINRequestDTO request);
}
