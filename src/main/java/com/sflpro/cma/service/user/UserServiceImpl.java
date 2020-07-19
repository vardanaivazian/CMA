package com.sflpro.cma.service.user;

import com.sflpro.cma.exception.UserAlreadyExistException;
import com.sflpro.cma.exception.UserNotFoundException;
import com.sflpro.cma.model.Role;
import com.sflpro.cma.model.User;
import com.sflpro.cma.repository.UserRepository;
import com.sflpro.cma.service.dto.RoleDto;
import com.sflpro.cma.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl( UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder ) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<UserDto> getAll() {
        return to( userRepository.findAll() );
    }

    @Override
    public UserDto get( Long id ) {
        Optional<User> optionalUser = userRepository.findById( id );
        if( optionalUser.isPresent() ) {
            return to( optionalUser.get() );
        }
        throw new UserNotFoundException("User not found. id: "+  id);
    }

    @Override
    public UserDto create( UserDto userDto ) {
        if( emailExist(userDto.getEmail()) ) {
            throw new UserAlreadyExistException("User with the email address: "+  userDto.getEmail() + " is already exists");
        }
        return save( userDto );
    }

    @Override
    public UserDto save( UserDto userDto ) {
        User user = to( userDto );
        user.setPassword( bCryptPasswordEncoder.encode( userDto.getPassword() ) );
        return to( userRepository.save( user ) );
    }

    @Override
    public void delete( UserDto userDto ) {
        if( emailExist(userDto.getEmail()) ) {
            throw new UserNotFoundException("User with the email address: "+  userDto.getEmail() + " not found");
        }
        userRepository.delete( to( userDto ) );
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public UserDto findByEmail( String email ) {
        User user = userRepository.findByEmail( email );
        return to( user );
    }


    private boolean emailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }

    private Role to( RoleDto roleDto ) {
        return Role.builder()
                .id( roleDto.getId() )
                .name( roleDto.getName() )
                .build();
    }

    private RoleDto to( Role role ) {
        return RoleDto.builder()
                .id( role.getId() )
                .name( role.getName() )
                .build();
    }

    private User to( UserDto userDto ) {
        return User.builder()
                .id( userDto.getId() )
                .role( to( userDto.getRole() ) )
                .email( userDto.getEmail() )
                .firstName( userDto.getFirstName() )
                .lastName( userDto.getLastName() )
                .password( userDto.getPassword() )
                .build();
    }

    private UserDto to( User user ) {
        return UserDto.builder()
                .id( user.getId() )
                .role( to( user.getRole() ) )
                .email( user.getEmail() )
                .firstName( user.getFirstName() )
                .lastName( user.getLastName() )
                .password( user.getPassword() )
                .build();
    }

    private List<UserDto> to( List<User> users) {
        LinkedList<UserDto> userDtoList = new LinkedList<>();
        for( User user : users ) {
            userDtoList.add( to( user ) );
        }
        return userDtoList;
    }


}
