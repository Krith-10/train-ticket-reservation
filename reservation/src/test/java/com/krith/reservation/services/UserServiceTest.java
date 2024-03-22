package com.krith.reservation.services;


import com.krith.reservation.dto.UserDto;
import com.krith.reservation.entity.Users;
import com.krith.reservation.repository.UserRepository;
import com.krith.reservation.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void createUserTest(){
        UserDto dto = new UserDto(1,"Kri","S","kri@gmail.com");
        Users user = new Users(1,"Kri","S","kri@gmail.com");
        when(userRepository.save(any(Users.class))).thenReturn(user);
        UserDto result = userService.createUser(dto);
        verify(userRepository, times(1)).save(Mockito.isA(Users.class));
        assertEquals(dto.getId(), result.getId());
        assertEquals(dto.getFirstName(), result.getFirstName());
        assertEquals(dto.getLastName(), result.getLastName());
        assertEquals(dto.getEmail(), result.getEmail());
    }

    @Test
    void getUserByIdTest(){
        Optional<Users> user = Optional.of(new Users(1,"A", "B", "abc@gmail.com"));
        doReturn(user).when(userRepository).findById(Mockito.isA(Long.class));
        UserDto result = userService.getUserById(1);
        assertEquals(user.get().getId(), result.getId());
        assertEquals(user.get().getFirstName(), result.getFirstName());
        assertEquals(user.get().getLastName(), result.getLastName());
        assertEquals(user.get().getEmail(), result.getEmail());
    }

    @Test
    void deleteUserByIdTest(){
        userService.deleteUserById(Mockito.isA(Long.class));
        verify(userRepository, times(1)).deleteById(Mockito.isA(Long.class));
    }

    @Test
    void convertUserDtoToEntityTest(){
         Users user = new Users(1,"Kri","S","kri@gmail.com");
         UserDto result= userService.convertUserEntityToUserDto(user);
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getFirstName(), result.getFirstName());
        assertEquals(user.getLastName(), result.getLastName());
        assertEquals(user.getEmail(), result.getEmail());
    }

    @Test
    void convertUserEntityToUserDto(){
        UserDto dto = new UserDto(1,"Kri","S","kri@gmail.com");
        Users result = userService.convertUserDtoToEntity(dto);
        assertEquals(dto.getFirstName(), result.getFirstName());
        assertEquals(dto.getLastName(), result.getLastName());
        assertEquals(dto.getEmail(), result.getEmail());
    }
}
