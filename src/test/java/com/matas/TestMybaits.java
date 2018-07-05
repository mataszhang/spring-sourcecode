package com.matas;

import com.matas.jdbc.User;
import com.matas.mybaits.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author matas
 * @date 2018/7/5 14:29
 * @email mataszhang@163.com
 */
public class TestMybaits {
    private static class MybatisUtils {
        private final static SqlSessionFactory sqlSessionFactory;

        static {
            String location = "mybatis/mybatis-config.xml";
            InputStream inputStream = null;
            try {
                inputStream = new PathMatchingResourcePatternResolver().getResource(location).getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        }

        public static SqlSessionFactory getSqlSessionFactory() {
            return sqlSessionFactory;
        }

    }


    @Test
    public void inserUser() {
        SqlSession sqlSession = MybatisUtils.getSqlSessionFactory().openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setName("rose");
        user.setAge(20);
        user.setSex("female");
        mapper.insertUser(user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void getUser() {
        SqlSession sqlSession = MybatisUtils.getSqlSessionFactory().openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUser(1);
        System.out.println(user);
        sqlSession.close();
    }

    @Test
    public void queryUserList() {
        SqlSession sqlSession = MybatisUtils.getSqlSessionFactory().openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        System.out.println(mapper.queryUserList());
        sqlSession.close();
    }
}
