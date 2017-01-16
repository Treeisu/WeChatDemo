package com.imooc.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;


import com.imooc.UtilEntity.Page;


/**
 * 拦截器  实现分页
 * @author c_huangjingjing
 *
 *type：mybatis封装好的处理的接口StatementHandler
 *method：mybatis封装好的接口下的具体的处理方法prepare
 *argss：这个代表的是传递的参数
 */
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})
public class pageInterceptor implements Interceptor{
	//第三，再执行拦截的具体处理方法
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		//获得来mybatis封装好的接口,注解里面拦下的类；进行实际的分页处理
		StatementHandler statementHandler=(StatementHandler) invocation.getTarget();
		//将这个对象交给metaobject进行封装一下
		MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY);
		//通过metaobject获得到和SQL语句相关配置的对象mappedStatement
		MappedStatement mappedStatement= (MappedStatement) metaObject.getValue("delegate.mappedStatement");
		
		//获得xml中查询的方法，这样才能把分页的参数传给它
		String SqlID=mappedStatement.getId();
		
		//如果这个方法是ByPage结尾的，说明这条语句需要通过拦截器进行分页
		if(SqlID.matches(".+byPage$")){
			
			BoundSql boundSql=statementHandler.getBoundSql();
			
			String Sql=boundSql.getSql();//得到xml中需要分页的sql语句	
			//执行查询总条数的语句 ,得到数据库中的总条数
			String countSql = "select count(*) from (" + Sql + ")a";
			Connection connection = (Connection)invocation.getArgs()[0];
			PreparedStatement countStatement = connection.prepareStatement(countSql);
			ParameterHandler parameterHandler = (ParameterHandler)metaObject.getValue("delegate.parameterHandler");
			parameterHandler.setParameters(countStatement);
			ResultSet rs = countStatement.executeQuery();
			//执行count的查询语句
			
			//获得分页对象，设置完page所有的参数
			Map<?,?> map = (Map<?,?>)boundSql.getParameterObject();//获得传给dao层方法的参数，才能获得分页参数
			Page page=(Page) map.get("page");
			if(rs.next()){
				page.setTotalNumber(rs.getInt(1));
				page.count();
			}
			//进行limit分页参数拼接
			String pageSql = Sql + " limit " + page.getDbIndex() + "," + page.getDbNumber();
			//把拼接好的语句返回给最原始的对象MetaObject
			metaObject.setValue("delegate.boundSql.sql", pageSql);
		}
		return invocation.proceed();
	}
	//第二，首先执行plugin方法；传入的参数是拦截到dao层需要分页的类 queryMessageListbyPage
	@Override
	public Object plugin(Object target) {
		//返回的是queryMessageListbyPage需要分页的类，还有当前的拦截器pageInterceptor的一个实例
		return Plugin.wrap(target, this);
	}
	//第一
	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		
	}

}
