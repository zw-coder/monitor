package cn.edu.dgut.service;

import cn.edu.dgut.domain.User;

public interface UserService {
    public User findUserByName(String username);
    public Integer addUser(User user);
    public Integer selectIdByName(String username);
}

