package com.internship.iotcontroller.repository;

import com.internship.iotcontroller.entity.DeviceMetrics;
import com.internship.iotcontroller.entity.IotSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceMetricsRepository extends JpaRepository<DeviceMetrics, Long> {
}
