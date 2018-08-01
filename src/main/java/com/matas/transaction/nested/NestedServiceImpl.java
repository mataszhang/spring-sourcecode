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
 * 测试TransactionDefinition.PROPAGATION_NESTED
 *
 * @author matas
 * @date 2018/8/1 16:52
 * @email mataszhang@163.com
 */
public class NestedServiceImpl {
    private JdbcTemplate jdbcTemplate;
    private Oper1 oper1;
    private Oper2 oper2;


    @Transactional(propagation = Propagation.REQUIRED)
    public void save(User user) {
        this.doSave(user);
        TransactionStatus transactionStatus = TransactionAspectSupport.currentTransactionStatus();
        System.err.println("com.matas.transaction.nested.NestedServiceImpl.save()是否有savepoint=>" + transactionStatus.hasSavepoint() + ",tran-name=>" + TransactionSynchronizationManager.getCurrentTransactionName());
        user.setName(user.getName() + "-2");
        try {
            this.oper1.operation1(user);
        } catch (RuntimeException e) {
            System.err.println("第一个操作失败了");
            this.oper2.operation2(user);
        }
    }

    private void doSave(User user) {
        this.jdbcTemplate.update("insert into user(name,age,sex) values(?,?,?)",
                new Object[]{user.getName(), user.getAge(), user.getSex()},
                new int[]{Types.VARCHAR, Types.INTEGER, Types.VARCHAR});
    }

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setOper1(Oper1 oper1) {
        this.oper1 = oper1;
    }

    public void setOper2(Oper2 oper2) {
        this.oper2 = oper2;
    }
}
