package com.matas.transaction.nested;

import com.matas.transaction.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;

/**
 * @author matas
 * @date 2018/8/1 16:52
 * @email mataszhang@163.com
 */
public class Oper1 {
    private JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.NESTED, rollbackFor = RuntimeException.class)
    public void operation1(User user) {
        TransactionStatus transactionStatus = TransactionAspectSupport.currentTransactionStatus();
        System.err.println("com.matas.transaction.nested.Oper1.operation1()是否有savepoint=>" + transactionStatus.hasSavepoint() + ",tran-name=>" + TransactionSynchronizationManager.getCurrentTransactionName());
        int i = 1 / 0;
    }

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
