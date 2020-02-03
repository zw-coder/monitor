package cn.edu.dgut.service.impl;

import cn.edu.dgut.dao.UserDao;
import cn.edu.dgut.domain.User;
import cn.edu.dgut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public User findUserByName(String username){return  this.userDao.findUserByName(username); }

    @Override
    public Integer addUser(User user) {
        return  this.userDao.addUser(user);
    }

    @Override
    public Integer selectIdByName(String username) {
        return this.userDao.selectIdByName(username);
    }
}
