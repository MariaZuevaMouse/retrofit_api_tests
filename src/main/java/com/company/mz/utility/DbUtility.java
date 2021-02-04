package com.company.mz.utility;

import com.company.mz.orm.db.dao.CategoriesMapper;
import com.company.mz.orm.db.dao.ProductsMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class DbUtility {
    private static String resource = "mybatis-config.xml";
    public static ProductsMapper getProductsMapper() throws IOException {
        SqlSession sqlSession = getSqlSession();
        return sqlSession.getMapper(ProductsMapper.class);
    }

    public static CategoriesMapper getCategoriesMapper() throws IOException {
        SqlSession sqlSession = getSqlSession();
        return sqlSession.getMapper(CategoriesMapper.class);
    }

    private static SqlSession getSqlSession() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        return sqlSessionFactory.openSession();
    }
}
