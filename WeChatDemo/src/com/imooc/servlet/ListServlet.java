package com.imooc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.imooc.db.DBAccess;
import com.imooc.model.Message;
import com.imooc.service.QueryService;

/**
 *消息回复后台页面servlet
 */
@SuppressWarnings("serial")
public class ListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String command=req.getParameter("command");
		String description=req.getParameter("description");
		req.setAttribute("command", command);
		req.setAttribute("description", description);
		QueryService queryService=new QueryService();
		List<Message> list=queryService.queryMessageList(command, description);
		req.setAttribute("messageList", list);
		req.getRequestDispatcher("/WEB-INF/jsp/background/list.jsp").forward(req, resp);
	}
	 /**vffefferehe
	  *    
	  */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}