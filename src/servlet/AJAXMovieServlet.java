package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import service.MovieService;
import service.impl.MovieServiceImpl;
import utils.Command;


public class AJAXMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MovieService ms = new MovieServiceImpl();
	private Gson gson = new Gson();
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String cmd = Command.getCmd(request);
    	response.setCharacterEncoding("utf-8");
    	response.setContentType("text/html;charset=utf-8");
    		if("list".equals(cmd)) {
    			PrintWriter pw = response.getWriter();
    			pw.println(gson.toJson(ms.selectMovieList()));
    			
    			} else {
    				try {
        				int miNum=Integer.parseInt(cmd);
        				PrintWriter pw = response.getWriter();
        				pw.println(gson.toJson(ms.selectMovieByMiNum(miNum)));
        			} catch(Exception e) {
    					throw new ServletException("올바른 상세조회 값이 아닙니다.");
    				}
    			}
    		  		
    	}
        	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = Command.getCmd(request);
    		if("insert".equals(cmd)) {
    			HttpSession hs = request.getSession();
    			if(hs.getAttribute("user")==null) {
    				Command.goResultPage(request, response, "로그인 해주세요", "/");
    				return;
    			}
    			Map<String,String> movie = Command.getSingleMap(request);
    			String msg = "영화등록이 실패하였습니다.";
    			String url = "/movie/list";
    			if(ms.insertMovie(movie)==1) {
        			msg = "영화등록이 성공하였습니다.";
        			url = "/movie/list";
    			}
    			Command.goResultPage(request, response, msg, url);

    			} else if("delete".equals(cmd)) {
    				HttpSession hs = request.getSession();
        			if(hs.getAttribute("user")==null) {
        				Command.goResultPage(request, response, "로그인 해주세요", "/");
        				return;
        			}
        			int miNum = Integer.parseInt(request.getParameter("mi_num"));
        			String msg = "삭제에 실패하였습니다.";
    				String url = "/movie/" + miNum;
        			if(ms.deleteMovie(miNum)==1) {
        				msg = "삭제에 성공하였습니다.";
        				url = "/movie/list";
        			}
        			Command.goResultPage(request, response, msg, url);
        			
    			}
    	
    	}
	}


