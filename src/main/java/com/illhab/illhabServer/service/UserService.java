package com.illhab.illhabServer.service;

import com.illhab.illhabServer.dto.UserDto;

public interface UserService {

    UserDto.JoinResponse join(UserDto.JoinRequest userDto);

    UserDto.ListResponse getUsers();

    UserDto.UserResponse getUser(Long userId);

    UserDto.UpdateResponse update(Long userId, UserDto.UpdateRequest userDto);

    UserDto.DeleteResponse delete(Long userId);

}
