package com.internship.iotcontroller.mapper;

import com.internship.iotcontroller.dto.DeviceMetricsDto;
import com.internship.iotcontroller.entity.DeviceMetrics;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface DeviceMetricsMapper extends EntityMapper<DeviceMetrics, DeviceMetricsDto> {

}