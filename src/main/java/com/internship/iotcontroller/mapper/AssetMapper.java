package com.internship.iotcontroller.mapper;


import com.internship.iotcontroller.dto.AssetDto;
import com.internship.iotcontroller.entity.Asset;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface AssetMapper extends EntityMapper<Asset, AssetDto> {

}