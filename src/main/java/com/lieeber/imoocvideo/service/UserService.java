package com.lieeber.imoocvideo.service;

import com.lieeber.imoocvideo.pojo.Users;
import kotlin.Unit;
import org.jetbrains.annotations.NotNull;

public interface UserService {
    boolean queryUserNameIsExit(String userName);
    void saveUser(Users user);
    Users queryUserForLogin(String username, String password);

    void updateUserInfo(@NotNull Users user);

    Users queryUserInfo(@NotNull String userId);
}
