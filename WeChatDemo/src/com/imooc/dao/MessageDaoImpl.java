package com.imooc.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.imooc.db.DBAccess;
import com.imooc.model.Message;


public class MessageDaoImpl{
	DBAccess dbAccess = new DBAccess();
	SqlSession sqlSession=null;

	public List<Message> queryMessageList(String command,String description) {
		
		List<Message> messagelist=new ArrayList<Message>();
		try {
			sqlSession=dbAccess.getSqlSession();//通过公共类dbAccess去获得一次会话sqlsession
			/**
			 * 由于mybatis的sql配置只接收一个参数，
			 * 为了解决查询时传入两个条件的情况，就对这两个条件进行封装；
			 * 这样就可以传入一个对象进行查询
			 */
			MessageDao messageDao=sqlSession.getMapper(MessageDao.class);
			Message message=new Message();
			message.setCommand(command);
			message.setDescription(description);
			messagelist=messageDao.queryMessageList(message);//hbm.xml中已经配置好的方法，sqlsession执行查询操作
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			/*if(sqlSession!=null){
				sqlSession.close();
			}*/
			sqlSession.close();
		}
		return messagelist;
	}
	
	/**
	 * 单条删除
	 */
	public void deleteOne(int id) {
		try {
			sqlSession = dbAccess.getSqlSession();
			// 通过sqlSession执行SQL语句
			MessageDao messageDao=sqlSession.getMapper(MessageDao.class);
			messageDao.deleteOne(id);
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
	 * 单条删除
	 */
	public void deleteBatch(List<Integer> list) {
		try {
			sqlSession = dbAccess.getSqlSession();
			// 通过sqlSession执行SQL语句
			MessageDao messageDao=sqlSession.getMapper(MessageDao.class);
			messageDao.deleteBatch(list);
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
