package com.becafe.security.mapper;

import com.becafe.model.Costumer;
import com.becafe.model.User;
import com.becafe.security.dto.AuthenticatedUserDto;
import com.becafe.security.dto.RegistrationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

// made by Ayoub Youb with ❤️
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CostumerMapper {

	CostumerMapper INSTANCE = Mappers.getMapper(CostumerMapper.class);

	Costumer convertToCostumer(RegistrationRequest registrationRequest);

}
