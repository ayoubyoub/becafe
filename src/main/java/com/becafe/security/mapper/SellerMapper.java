package com.becafe.security.mapper;

import com.becafe.model.Seller;
import com.becafe.security.dto.RegistrationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

// made by Ayoub Youb with ❤️
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SellerMapper {

	SellerMapper INSTANCE = Mappers.getMapper(SellerMapper.class);

	Seller convertToSeller(RegistrationRequest registrationRequest);

}
