package com.internship.iotcontroller.api;

import com.internship.iotcontroller.dto.AssetDto;
import com.internship.iotcontroller.dto.DeviceMetricsDto;
import com.internship.iotcontroller.dto.IotSessionDto;
import com.internship.iotcontroller.mapper.DeviceMetricsMapper;
import com.internship.iotcontroller.service.AssetService;
import com.internship.iotcontroller.service.DeviceMetricsService;
import com.internship.iotcontroller.service.IotSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assets")
@Slf4j
public class AssetController {

    @Autowired
    private AssetService assetService;

    @Autowired
    private IotSessionService iotSessionService;

    @Autowired
    private DeviceMetricsService deviceMetricsService;


    @PostMapping
    public ResponseEntity<AssetDto> createAsset(AssetDto assetDto) throws Exception {
        if (assetDto == null) {
            throw new Exception("Asset data is missing");
        }
        return ResponseEntity
                .ok()
                .body(assetService.save(assetDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetDto> getAssetById(@PathVariable Long id) throws Exception {
        AssetDto assetFound = assetService.findById(id);
        return ResponseEntity
                .ok()
                .body(assetFound);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AssetDto>> getAllAssets() {
        return ResponseEntity
                .ok()
                .body(assetService.findAll());
    }

    @PutMapping()
    public ResponseEntity<AssetDto> assetUpdate(@RequestBody AssetDto assetDto) throws Exception {
        if (assetDto == null) {
            throw new Exception("Asset data is missing");
        }
        return ResponseEntity
                .ok()
                .body(assetService.update(assetDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAsset(@PathVariable Long id) throws Exception {
        assetService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/iotsession/{machineId}/{deviceId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IotSessionDto> startIotSession(@PathVariable Long machineId,@PathVariable Long deviceId) throws Exception {
        log.info("Session between machine#{} & device#{} started!", machineId, deviceId);
        return ResponseEntity.ok(new IotSessionDto( iotSessionService.startSession(machineId, deviceId)));
    }

    @PostMapping("/iotsession/{id}")
    public ResponseEntity<?> endIotSession(@PathVariable Long id) throws Exception {
        log.info("Session#{} ended", id);
        iotSessionService.endSession(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/iotsession/{id}/device_metrics")
    public ResponseEntity<?> saveDeviceMetrics (@PathVariable Long id,  @RequestBody DeviceMetricsDto deviceMetricsDto) throws Exception {
        log.info("Got some metrics for session#{} - value: {}", id,  deviceMetricsDto.getValue());
        deviceMetricsService.save(id, deviceMetricsDto);
        return ResponseEntity.noContent().build();
    }

}
