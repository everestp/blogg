package com.offnine.blogg.ServiceImpl;

import com.offnine.blogg.Payload.UserDto;
import com.offnine.blogg.Repo.UserRepo;
import com.offnine.blogg.Services.UserService;
import com.offnine.blogg.entities.User;
import com.offnine.blogg.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
  
    @Autowired
private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDto createUser(UserDto userDto) {
      User map = this.modelMapper.map(userDto, User.class);
        User addedUser = this.userRepo.save(map);
        return this.modelMapper.map(addedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
       User cat = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User ID",userId));
       cat.setName(userDto.getName());
       cat.setEmail(userDto.getEmail());
       cat.setPassword(userDto.getPassword());
       cat.setAge(userDto.getAge());
       cat.setGender(userDto.getGender());
       User upatedUser = this.userRepo.save(cat);
       return this.modelMapper.map(upatedUser, UserDto.class);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User cat = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User ID",userId));


        return this.modelMapper.map(cat, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
      List<User> users = this.userRepo.findAll();
      List<UserDto> userDtos =users.stream().map((cat) -> this.modelMapper.map(cat, UserDto.class)).collect(Collectors.toList());
return  userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User cat = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User ID",userId));
this.userRepo.delete(cat);
    }
}
