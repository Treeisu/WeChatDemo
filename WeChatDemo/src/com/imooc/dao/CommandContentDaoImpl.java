package com.imooc.dao;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;



import com.imooc.db.DBAccess;
import com.imooc.model.CommandContent;

/**
 * 和command_content 表相关的数据库操作
 */
public class CommandContentDaoImpl {
	DBAccess dbAccess = new DBAccess();
	SqlSession sqlSession = null;
	/**
	 * 单条新增
	 */
	public void insertOne(CommandContent commandContent){
		try {
			sqlSession = dbAccess.getSqlSession();
			// 通过sqlSession执行SQL语句
			CommandContentDao commandContentDao =sqlSession.getMapper(CommandContentDao.class);
			commandContentDao.insertOne(commandContent);
			sqlSession.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
	
	}
	
	/**
	 * 批量新增
	 */
	public void insertBatch(List<CommandContent> list) {
		
		try {
			sqlSession = dbAccess.getSqlSession();
			// 通过sqlSession执行SQL语句
			CommandContentDao commandContent = sqlSession.getMapper(CommandContentDao.class);
			commandContent.insertBatch(list);
			sqlSession.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
	}
}