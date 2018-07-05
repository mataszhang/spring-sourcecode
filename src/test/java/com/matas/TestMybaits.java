package com.matas;

import com.matas.jdbc.User;
import com.matas.mybaits.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.PropertyResourceConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

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

    @Test
    public void queryUserList2() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-mybatis.xml");
        UserMapper mapper = ctx.getBean(UserMapper.class);
        System.out.println(mapper.queryUserList());
    }

    /**
     * 测试processPropertyPlaceHolders属性的作用
     * <p>提前解析basePackage配置的PropertyPlaceHolder。</p>
     * <p>
     * 因为{@link PropertyResourceConfigurer }实现了{@link BeanFactoryPostProcessor }接口
     * 意味着在BeanFactory加载完所有的BeanDefinition后才会调用。而{@link MapperScannerConfigurer}在扫描Mapper的时候，需要替换配置的属性。
     * 所以需要通过processPropertyPlaceHolders来控制是否提前解析PropertyPlaceHolder。通过模拟spirng的环境，来调用PropertyResourceConfigurer解析配置
     * </p>
     *
     * @param
     * @return
     * @author matas
     * @date 2018/7/5 16:40
     * @see MapperScannerConfigurer#processPropertyPlaceHolders()
     */
    @Test
    public void testBasePackage() {
        //applicationContext instanceof GenericApplicationContext
        ApplicationContext ctx = new GenericXmlApplicationContext("test-mybatis.xml");
        UserMapper mapper = ctx.getBean(UserMapper.class);
        System.out.println(mapper.queryUserList());
    }

    @Test
    public void testScan() throws Exception {
        String basePackage = "com.matas.aop";
        String convertPath = ClassUtils.convertClassNameToResourcePath(basePackage); // 包名装路径
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                convertPath + "/" + "**/*.class";
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);//路径下所有的*.class
        System.out.println(resources.length);


        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(true);
        provider.addIncludeFilter((new TypeFilter() {
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                return true;
            }
        })); //所有的类都匹配
        Set<BeanDefinition> candidateComponents = provider.findCandidateComponents(basePackage);
        System.out.println(candidateComponents.size());
    }
}
