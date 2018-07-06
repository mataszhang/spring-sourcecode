package com.matas.transaction;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author matas
 * @date 2018/7/5 10:13
 * @email mataszhang@163.com
 */
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
public interface UserService {
    void save(User user);

    List<User> getUsers();
}
