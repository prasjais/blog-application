package com.blogapplication.services;

import com.blogapplication.payloads.UserDTO;

import java.util.List;

public interface UserServices {

    public UserDTO createUser(UserDTO userDTO);

    public UserDTO updateUser(UserDTO userDTO, Integer id);

    public UserDTO getUser(Integer id);

    public List<UserDTO> getAllUser();

    public void deleteUser(Integer id);
}
