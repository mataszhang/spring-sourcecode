package com.matas.transaction.nested;

import com.matas.transaction.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * @author matas
 * @date 2018/8/1 16:52
 * @email mataszhang@163.com
 */
public class Oper2 {
    private JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    public void operation2(User user) {

        TransactionStatus transactionStatus = TransactionAspectSupport.currentTransactionStatus();
        System.err.println("com.matas.transaction.nested.Oper2.operation2()是否有savepoint=>" + transactionStatus.hasSavepoint() + ",tran-name=>"+TransactionSynchronizationManager.getCurrentTransactionName());
        this.doSave(user);
    }


    private void doSave(User user) {
        this.jdbcTemplate.update("insert into user(name,age,sex) values(?,?,?)",
                new Object[]{user.getName(), user.getAge(), user.getSex()},
                new int[]{Types.VARCHAR, Types.INTEGER, Types.VARCHAR});
    }

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
