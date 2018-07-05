package com.matas.mybaits.mapper;

import com.matas.jdbc.User;

import java.util.List;

/**
 * @author matas
 * @date 2018/7/5 14:21
 * @email mataszhang@163.com
 */
public interface UserMapper {
    void insertUser(User user);
    User getUser(Integer id);
    List<User> queryUserList();
}
