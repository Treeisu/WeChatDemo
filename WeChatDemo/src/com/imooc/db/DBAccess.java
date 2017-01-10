package com.imooc.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 获得sqlSession
 */
public class DBAccess {
	public SqlSession getSqlSession() throws IOException {
		//获得cfg.xml配置文件   /WeChatDemo/src/com/imooc/configXML/mybatis.cfg.xml
		Reader reader = Resources.getResourceAsReader("com/imooc/configXML/mybatis.cfg.xml");
		//构建sqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		//打开一个sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}
}
