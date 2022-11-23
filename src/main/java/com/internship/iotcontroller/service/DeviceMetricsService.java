package com.internship.iotcontroller.service;

import com.internship.iotcontroller.dto.DeviceMetricsDto;
import com.internship.iotcontroller.entity.DeviceMetrics;
import com.internship.iotcontroller.entity.IotSession;
import com.internship.iotcontroller.mapper.DeviceMetricsMapper;
import com.internship.iotcontroller.repository.DeviceMetricsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceMetricsService {

    @Autowired
    private DeviceMetricsRepository deviceMetricsRepository;

    @Autowired
    private DeviceMetricsMapper deviceMetricsMapper;

    @Autowired
    private IotSessionService iotSessionService;

    public void save(Long id, DeviceMetricsDto deviceMetricsDto) throws Exception {
        DeviceMetrics deviceMetrics = deviceMetricsMapper.toEntity(deviceMetricsDto);
        IotSession iotSession = iotSessionService.findById(id);
        deviceMetrics.setIotSession(iotSession);
        deviceMetricsRepository.save(deviceMetrics);
    }
}

