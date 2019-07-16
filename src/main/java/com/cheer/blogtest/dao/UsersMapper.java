package com.cheer.blogtest.dao;

import com.cheer.blogtest.model.Users;

public interface UsersMapper {

    Users find(String username);

    int insert(Users users);
}
