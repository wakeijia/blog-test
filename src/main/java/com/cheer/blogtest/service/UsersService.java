package com.cheer.blogtest.service;

import com.cheer.blogtest.model.Users;


public interface UsersService {

    Boolean checkLogin(String username,String password);

    Users find(String username);

    int insert(Users users);
}
