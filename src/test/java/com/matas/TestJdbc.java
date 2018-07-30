package com.matas;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;

/**
 * @author matas
 * @date 2018/7/29 16:28
 * @email mataszhang@163.com
 */
public class TestJdbc {

    @Test
    public void supportTransaction() throws Exception {
        Class.forName(Driver.class.getName());
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/wbt", "root", "123456");
        DatabaseMetaData metaData = connection.getMetaData();
        boolean b = metaData.supportsTransactions();
        System.out.println(b);
        metaData.supportsTransactionIsolationLevel(Connection.TRANSACTION_READ_COMMITTED);
        int defaultTransactionIsolation = metaData.getDefaultTransactionIsolation();
        System.out.println(defaultTransactionIsolation);
        boolean b1 = metaData.supportsSavepoints();
        System.out.println(b1);
    }
}
