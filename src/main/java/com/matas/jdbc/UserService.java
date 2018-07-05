package com.matas.jdbc;

import java.util.List;

/**
 * @author matas
 * @date 2018/7/5 10:13
 * @email mataszhang@163.com
 */
public interface UserService {
    void save(User user);

    List<User> getUsers();
}
