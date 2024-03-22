package com.krith.reservation.service;

import com.krith.reservation.dto.UserDto;
import com.krith.reservation.entity.Users;
import com.krith.reservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto createUser(UserDto userDto){
        Users user = convertUserDtoToEntity(userDto);
        Users usr = userRepository.save(user);
        return convertUserEntityToUserDto(usr);
    }

    public UserDto getUserById(long id){
        Optional<Users> user =userRepository.findById(id);
        return user.map(this::convertUserEntityToUserDto).orElse(null);
    }

    public void deleteUserById(long id){
        userRepository.deleteById(id);
    }

    public Users convertUserDtoToEntity(UserDto userDto){
        return new Users(
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );
    }

    public UserDto convertUserEntityToUserDto(Users user){
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }
}
