package com.lieeber.imoocvideo.service;

import com.lieeber.imoocvideo.mapper.UsersMapper;
import com.lieeber.imoocvideo.pojo.Users;
import org.apache.catalina.User;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUserNameIsExit(String userName) {
        Users user = new Users();
        user.setUsername(userName);
        Users result = usersMapper.selectOne(user);
        return result != null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveUser(Users user) {
        user.setId(sid.nextShort());
        usersMapper.insert(user);

    }
}
