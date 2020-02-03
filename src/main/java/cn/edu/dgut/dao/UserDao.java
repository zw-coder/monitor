package cn.edu.dgut.dao;

import cn.edu.dgut.domain.User;

public interface UserDao {
    /**
     * 根据用户名查询密码
     */
    public User findUserByName(String username);

    /**
     * 添加用户
     * @param user
     * @return
     */
    public int addUser(User user);

    /**
     * 查询id
     * @param username
     * @return
     */
    public int selectIdByName(String username);
}
