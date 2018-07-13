package com.matas.transaction;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

/**
 * 通过硬编码的方式来实现事务
 *
 * @author matas
 * @date 2018/7/13 17:41
 * @email mataszhang@163.com
 */
public class HardCodeTransaction implements UserService {
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;


    @Override
    public void save(User user) {
        TransactionDefinition td = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(td);
        try {
            doSave(user);
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
        transactionManager.commit(status);
    }

    private void doSave(User user) {
        jdbcTemplate.update("insert into user(name,age,sex) values(?,?,?)",
                new Object[]{user.getName(), user.getAge(), user.getSex()},
                new int[]{Types.VARCHAR, Types.INTEGER, Types.VARCHAR});
        int i = 0;
        i = 1 / i;
    }

    @Override
    public List<User> getUsers() {
        return this.jdbcTemplate.query("select * from user", new UserRowMapper());
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
