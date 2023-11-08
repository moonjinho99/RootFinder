package com.RootFinder.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LikeCoseAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		String like = request.getParameter("like");
		String code = request.getParameter("code");
		String id = request.getParameter("id");
		System.out.println("좋아요 코드 : "+code);
		System.out.println("좋아요 아이디 : "+id);
		
		if(like.equals("yes"))
		{
			System.out.println("관심목록 추가");
		}
		else {
			System.out.println("관심목록 해제");
		}
		
//		RequestDispatcher rd = request.getRequestDispatcher("RootFinderServlet?command=root_summary_form&code="+code+"&id="+id);
//		rd.forward(request, response);
		
		//response.sendRedirect("RootFinderServlet?command=root_summary_form&code="+code+"&id="+id);
	}

}
