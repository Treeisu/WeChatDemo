package com.imooc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imooc.service.QueryService;

/**
 * 对话页面的初始化控制
 * @author c_huangjingjing
 *
 */
@SuppressWarnings("serial")
public class InitTalkServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out=resp.getWriter();
		String messagecontent=req.getParameter("content");
		if(messagecontent != null){
			QueryService queryService=new QueryService();
			out.write(queryService.queryByCommand(messagecontent));
			out.flush();
			out.close();
		}else{
			req.getRequestDispatcher("/WEB-INF/jsp/front/talk.jsp").forward(req, resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
