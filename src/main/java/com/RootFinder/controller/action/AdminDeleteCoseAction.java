package com.RootFinder.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.RootFinder.dao.CoseDAO;
import com.RootFinder.dao.UserDAO;
import com.RootFinder.dto.CoseCommentDTO;
import com.RootFinder.dto.CoseListDTO;
import com.RootFinder.dto.UserDTO;

public class AdminDeleteCoseAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String delete_code = request.getParameter("delete_code");
		System.out.println("코드 : "+delete_code);
		CoseDAO cdao = CoseDAO.getInstance();
		int res = cdao.AdminDeleteCose(delete_code);
		
		if(res == 1)
		{
			System.out.println("관리자_코스 삭제 성공");
		}else {
			System.out.println("관리자_코스 삭제 실패");
		}
		
		
		//DB변경 후 변경된 값을 재호출 하기
		
			UserDAO udao = UserDAO.getInstance();
			HttpSession session = request.getSession();
			
			ArrayList<UserDTO> allUserInf = udao.allUserInf("hmjcp");
			session.setAttribute("allUserInf", allUserInf);
			
			ArrayList<CoseListDTO> allCoseList = cdao.allCoseList();
			session.setAttribute("allCoseList", allCoseList);
			
			
			ArrayList<CoseCommentDTO> allCommentList = cdao.allCommentList();
			session.setAttribute("allCommentList", allCommentList);
			String url="admin_mainpage.jsp";
			
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
	}

}
