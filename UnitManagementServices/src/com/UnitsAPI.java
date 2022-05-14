package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import com.Unit;



@WebServlet("/UnitsAPI")
public class UnitsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Unit unitObj = new Unit();
	
    public UnitsAPI() {
        super();
    }
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
	{
	 Map<String, String> map = new HashMap<String, String>();
	try
	 {
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	 String queryString = scanner.hasNext() ?
	 scanner.useDelimiter("\\A").next() : "";
	 scanner.close();
	 String[] params = queryString.split("&");
	 for (String param : params)
	 { 
		 String[] p = param.split("=");
		 map.put(p[0], p[1]);
		 }
		 }
		catch (Exception e)
		 {
		 }
		return map;
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String output = unitObj.insertUnit(request.getParameter("cus_id"),
				 request.getParameter("cus_name"),
				request.getParameter("cus_phone"),
				request.getParameter("new_read"),
				request.getParameter("last_read"),
				request.getParameter("used_unit"));
				response.getWriter().write(output); 

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
			{
			 Map paras = getParasMap(request);
			 String output = unitObj.updateUnits(paras.get("hidUnitIDSave").toString(),
			 paras.get("cus_id").toString(),
			paras.get("cus_name").toString(),
			paras.get("cus_phone").toString(),
			paras.get("new_read").toString(),
			paras.get("last_read").toString(),
			paras.get("used_unit").toString());
			response.getWriter().write(output);
			} 

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
			{
			 Map paras = getParasMap(request);
			 String output = unitObj.deleteUnit(paras.get("idUnit").toString());
			response.getWriter().write(output);
			}

}