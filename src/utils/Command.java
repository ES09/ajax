package utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Command {
	
	private static final String RESULT_PATH = "/views/msg/result";
	
	public static String getCmd(HttpServletRequest req) throws ServletException {
		String uri = req.getRequestURI();
		String cmd = req.getParameter("cmd");
		if(cmd!=null) {
			return cmd;
		}
		int idx = uri.lastIndexOf("/");
		if(idx==0 || idx==-1) {
			throw new ServletException("올바르지 않은 요청입니다.");
		}				
		return uri.substring(idx+1);
	}
	
	public static void goResultPage(HttpServletRequest request, HttpServletResponse response, String msg, String url) 
									throws ServletException, IOException {
		request.setAttribute("msg",	msg);
		request.setAttribute("url", url);
		RequestDispatcher rd = request.getRequestDispatcher(RESULT_PATH);
		rd.forward(request, response);
	}
	
	public static Map<String,String> getSingleMap(HttpServletRequest request){
		// value가 많아도 0번째 값 리턴 - key value를 1대1로 매핑하는 것을 만든 것
		Map<String,String> pMap = new HashMap<>();
		Map<String,String[]> map = request.getParameterMap();
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			String value = map.get(key)[0];
			pMap.put(key, value);			
		}
		return pMap;
	}

}
