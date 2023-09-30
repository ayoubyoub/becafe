package com.becafe.security.mapper;

import com.becafe.model.User;
import com.becafe.security.dto.AuthenticatedUserDto;
import com.becafe.security.dto.RegistrationRequest;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-30T23:48:06+0100",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230814-2020, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public User convertToUser(RegistrationRequest registrationRequest) {
        if ( registrationRequest == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.email( registrationRequest.getEmail() );
        user.name( registrationRequest.getName() );
        user.password( registrationRequest.getPassword() );
        user.role( registrationRequest.getRole() );
        user.username( registrationRequest.getUsername() );

        return user.build();
    }

    @Override
    public AuthenticatedUserDto convertToAuthenticatedUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        AuthenticatedUserDto authenticatedUserDto = new AuthenticatedUserDto();

        authenticatedUserDto.setName( user.getName() );
        authenticatedUserDto.setPassword( user.getPassword() );
        authenticatedUserDto.setRole( user.getRole() );
        authenticatedUserDto.setUsername( user.getUsername() );

        return authenticatedUserDto;
    }

    @Override
    public User convertToUser(AuthenticatedUserDto authenticatedUserDto) {
        if ( authenticatedUserDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.name( authenticatedUserDto.getName() );
        user.password( authenticatedUserDto.getPassword() );
        user.role( authenticatedUserDto.getRole() );
        user.username( authenticatedUserDto.getUsername() );

        return user.build();
    }
}
