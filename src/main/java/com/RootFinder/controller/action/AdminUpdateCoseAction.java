package com.RootFinder.controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.RootFinder.dao.CoseDAO;
import com.RootFinder.dao.UserDAO;
import com.RootFinder.dto.CoseCommentDTO;
import com.RootFinder.dto.CoseDTO;
import com.RootFinder.dto.CoseListDTO;
import com.RootFinder.dto.UserDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import util.DateParse;

public class AdminUpdateCoseAction implements Action{
   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  response.setContentType("text/html;charset=UTF-8");
      
      MultipartRequest multi = null;
      
      PrintWriter out = response.getWriter();
      // 다운로드 경로 설정
      String savePath = "image";
      response.setContentType("text/html; charset=UTF-8");
      // 최대 업로드 사이즈 설정
      int maxSize = 5 * 1024 * 1024;
      // 인코딩 타입
      String encType = "UTF-8";
      HttpSession session = request.getSession();
      
      ServletContext context = session.getServletContext();
      String uploadFilePath = context.getRealPath(savePath);
      System.out.println("서버상의 실제 디렉토리");
      System.out.println(uploadFilePath);
      
      try {
         multi = new MultipartRequest(request// 요청
               , uploadFilePath, // 서버상의 실제 디렉토리
               maxSize, // 최대 업로드 파일 사이즈
               encType, // 인코딩 타입
               new DefaultFileRenamePolicy()
               
               
         // 동일이름 존재하면 이름변경
         );

         // 업로드 파일 이름 확인
         Enumeration files = multi.getFileNames();
         
         while (files.hasMoreElements()) {
            String file = (String) files.nextElement();
            String file_name = multi.getFilesystemName(file);
            String ori_file_name = multi.getOriginalFileName(file);
            
            
            System.out.println("원본 파일명"+file_name);
         }
         
         System.out.println(multi.getParameter("last"));
         
         
      } catch (Exception e) {
            System.out.println("코스등록 이미지 오류 : "+e.getMessage());
      } finally {

      }
      
      // 값을 세션에 저장
      
      String fileName="";
      
      ArrayList<CoseDTO> list = new ArrayList<CoseDTO>();
      CoseDAO rdao =CoseDAO.getInstance();
      int last = Integer.parseInt(multi.getParameter("last"));
      String code = multi.getParameter("code");
      String content = multi.getParameter("update_content");
      for (int i = 1; i < last; i++) {
         CoseDTO rdto = new CoseDTO();
         rdto.setCode(code);
         
         fileName =  multi.getFilesystemName("update_img_" + i);
         String current_cose_img = multi.getParameter("current_cose_img_"+i);
         if(fileName == null)
			{
				System.out.println("관리자_프로필 이미지 파일이 업로드 되지 않았습니다.");
				fileName = current_cose_img;
			}else {
				System.out.println(fileName+"관리자_프로필 이미지 파일이 업로드 되었습니다.");

			}
         rdto.setImg("\\" + fileName);
         rdto.setPlaceName(multi.getParameter("placename_" + i));
         rdto.setAddr( multi.getParameter("update_addr_" + i));
         rdto.setStarttime(multi.getParameter("starttime_"+i));
         rdto.setPrice(multi.getParameter("update_price_"+i));
         System.out.println(rdto);
         String date = DateParse.dateToStr(multi.getParameter("date_"+i));
         System.out.println(date);
         Date insday = (Date) DateParse.strToDate(date);
         System.out.println(insday);
         rdto.setCose_date(insday);
         list.add(rdto);
      }
      System.out.println(list);
      rdao.update_content(code,content);
      
      for(int i = 0; i <last-1;i++) {
         CoseDTO rDto = (CoseDTO) list.get(i);
         System.out.println(rDto);
         int result = rdao.rootUpdate(rDto);
         System.out.println(result);
      }
      
    //DB변경 후 변경된 값을 재호출 하기
		
		UserDAO udao = UserDAO.getInstance();
		
		
		ArrayList<UserDTO> allUserInf = udao.allUserInf("hmjcp");
		session.setAttribute("allUserInf", allUserInf);
		
		ArrayList<CoseListDTO> allCoseList = rdao.allCoseList();
		session.setAttribute("allCoseList", allCoseList);
		
		
		ArrayList<CoseCommentDTO> allCommentList = rdao.allCommentList();
		session.setAttribute("allCommentList", allCommentList);
		String url="admin_mainpage.jsp";
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
   }

}