package com.cheer.blogtest.service.impl;

import com.cheer.blogtest.dao.UsersMapper;
import com.cheer.blogtest.model.Users;
import com.cheer.blogtest.service.UsersService;
import com.cheer.blogtest.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Boolean checkLogin(String username, String password) {
        Users users = this.usersMapper.find(username);

        if (users == null){
            return false;
        }
        if (StringUtils.encrypt(password).equals(users.getPassword()))
        {
            return true;
        }

        return false;
    }

    @Override
    public Users find(String username) {
        return this.usersMapper.find(username);
    }

    @Override
    public int insert(Users users) {
        int i = this.usersMapper.insert(users);
        if(i>0){
            System.out.println("插入成功！");
            return i;
        }else {
            return i;
        }
    }
}
