package com.lieeber.imoocvideo.service;

import com.lieeber.imoocvideo.pojo.Users;

public interface UserService {
    boolean queryUserNameIsExit(String userName);
    void saveUser(Users user);
}
