package com.offnine.blogg.Services;

import com.offnine.blogg.Payload.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,Integer userId);
    UserDto getUserById(Integer userId);
   List<UserDto> getAllUsers();
    void deleteUser(Integer userId);

}
