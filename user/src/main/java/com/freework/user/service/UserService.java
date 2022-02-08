package com.freework.user.service;

import com.freework.user.dto.CreateUserRequest;
import com.freework.user.dto.UpdateUserRequest;
import com.freework.user.dto.UserDto;
import com.freework.user.dto.UserDtoConverter;
import com.freework.user.exception.UserNotFoundException;
import com.freework.user.model.User;
import com.freework.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userDtoConverter::convert).collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        User user=userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User couldn't be found by following id: "+id));
        return userDtoConverter.convert(user);
    }

    public UserDto createUser(CreateUserRequest userRequest) {
        User user=new User(null,userRequest.getFirstName(),userRequest.getMail(),userRequest.getLastName(),userRequest.getMiddleName());
        return userDtoConverter.convert(userRepository.save(user));
    }

    public UserDto updateUser(Long id,UpdateUserRequest updateUserRequest) {
        User user=findUserById(id);
        User updatedUser=new User(user.getId(),user.getMail(),updateUserRequest.getFirstName(),updateUserRequest.getLastName(),updateUserRequest.getMiddleName());
        return userDtoConverter.convert(userRepository.save(updatedUser));
    }
    private User findUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException("User couldn't be found by following id: "+id));
    }
}
