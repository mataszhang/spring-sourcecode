package com.matas.transaction;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

/**
 * @author matas
 * @date 2018/7/5 10:16
 * @email mataszhang@163.com
 */
public class UserServiceImpl implements UserService {
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update("insert into user(name,age,sex) values(?,?,?)",
                new Object[]{user.getName(), user.getAge(), user.getSex()},
                new int[]{Types.VARCHAR, Types.INTEGER, Types.VARCHAR});
        int i= 0 ;
        i= 1/i;
    }

    @Override
    public List<User> getUsers() {
        return this.jdbcTemplate.query("select * from user", new UserRowMapper());
    }
}
