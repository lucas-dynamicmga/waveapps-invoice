package com.gottlieb.sample.service;

import com.gottlieb.sample.service.adapter.MotorCarrierAdapterInterface;
import com.gottlieb.sample.service.dto.MotorCarrierDriverResponseDTO;
import com.gottlieb.sample.service.dto.MotorCarrierGeneralResponseDTO;
import com.gottlieb.sample.service.dto.MotorCarrierRequestDTO;
import com.gottlieb.sample.service.dto.MotorCarrierVINRequestDTO;
import com.gottlieb.sample.service.dto.MotorCarrierVINResponseDTO;
import com.gottlieb.sample.service.dto.MotorCarrierVehicleResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link com.gottlieb.sample.domain.CAB}.
 */
@Service
public class MotorCarrierService {

    @Value("${apis.motorCarrier.activeService}")
    private String activeMotorCarrier;

    private final ApplicationContext ctx;

    public MotorCarrierService(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    /**
     * Get Transport AnalysisS.
     *
     * @param request MotorCarrierRequestDTO.
     * @return the Transport Analysis MotorCarrierRequestDTO.
     */
    public Mono<MotorCarrierGeneralResponseDTO> getDOT(MotorCarrierRequestDTO request) {
        MotorCarrierAdapterInterface adapter = (MotorCarrierAdapterInterface) ctx.getBean(activeMotorCarrier);
        return adapter.getDOT(request);
    }

    /**
     * Get Transport AnalysisS.
     *
     * @param request MotorCarrierRequestDTO.
     * @return the Transport Analysis MotorCarrierRequestDTO.
     */
    public Mono<MotorCarrierDriverResponseDTO> getDriver(MotorCarrierRequestDTO request) {
        MotorCarrierAdapterInterface adapter = (MotorCarrierAdapterInterface) ctx.getBean(activeMotorCarrier);
        return adapter.getDriver(request);
    }

    /**
     * Get Transport AnalysisS.
     *
     * @param request MotorCarrierRequestDTO.
     * @return the Transport Analysis MotorCarrierRequestDTO.
     */
    public Mono<MotorCarrierVehicleResponseDTO> getVehicle(MotorCarrierRequestDTO request) {
        MotorCarrierAdapterInterface adapter = (MotorCarrierAdapterInterface) ctx.getBean(activeMotorCarrier);
        return adapter.getVehicle(request);
    }

    /**
     * Get Transport AnalysisS.
     *
     * @param request MotorCarrierRequestDTO.
     * @return the Transport Analysis MotorCarrierRequestDTO.
     */
    public Mono<MotorCarrierVINResponseDTO> getVehicleByVIN(MotorCarrierVINRequestDTO request) {
        MotorCarrierAdapterInterface adapter = (MotorCarrierAdapterInterface) ctx.getBean(activeMotorCarrier);
        return adapter.getVehicleByVIN(request);
    }
}
