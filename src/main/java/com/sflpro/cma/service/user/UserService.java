package com.sflpro.cma.service.user;

import com.sflpro.cma.service.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAll();

    UserDto get( Long id );

    UserDto create( UserDto user );

    UserDto save( UserDto user );

    void delete( UserDto user );

    void deleteAll( );

    UserDto findByEmail( String email );

}
