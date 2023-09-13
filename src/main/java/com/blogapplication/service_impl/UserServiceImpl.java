package com.blogapplication.service_impl;

import com.blogapplication.entities.User;
import com.blogapplication.exceptions.ResourceNotFoundException;
import com.blogapplication.payloads.UserDTO;
import com.blogapplication.repositories.UserRepo;
import com.blogapplication.services.UserServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO){


        User user = this.dtoToUser(userDTO);
        User userSaved = this.userRepo.save(user);
        return this.userToDto(userSaved);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer id) {

        User user = this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id", id));

        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setAbout(userDTO.getAbout());

        User user1 = this.userRepo.save(user);

        return this.userToDto(user1);
    }

    @Override
    public UserDTO getUser(Integer id) {

        User user = this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id", id));

        return this.userToDto(user);
    }

    @Override
    public List<UserDTO> getAllUser() {

        List<User> user = this.userRepo.findAll();
        List<UserDTO> userDTOList = user.stream().map(u -> this.userToDto(u)).collect(Collectors.toList());
        return  userDTOList;

    }

    @Override
    public void deleteUser(Integer id) {

        User user = this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id", id));
        this.userRepo.delete(user);

    }


    public User dtoToUser(UserDTO userDTO)
    {
        User us = this.modelMapper.map(userDTO, User.class);
//        us.setId(userDTO.getId());
//        us.setName((userDTO.getName()));
//        us.setEmail(userDTO.getEmail());
//        us.setPassword(userDTO.getPassword());
//        us.setAbout(userDTO.getAbout());
        return us;
    }

    public UserDTO userToDto(User user)
    {
        UserDTO dto = this.modelMapper.map(user, UserDTO.class);
//        dto.setId(user.getId());
//        dto.setName(user.getName());
//        dto.setPassword(user.getPassword());
//        dto.setEmail(user.getEmail());
//        dto.setAbout(user.getAbout());
        return dto;
    }
}
