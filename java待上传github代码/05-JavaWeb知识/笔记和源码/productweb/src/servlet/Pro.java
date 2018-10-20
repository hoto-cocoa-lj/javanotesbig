package servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import bean.Pagebean;
import bean.product;
import service.Proservice;
import utils.ManagerConnection;

public class Pro extends HttpServlet {
	List<product> all = new ArrayList<product>();

	public void doGet(HttpServletRequest r, HttpServletResponse s) throws ServletException, IOException {
		String me = r.getParameter("me");
		if ("getall".equalsIgnoreCase(me)) {
			search(r, s);
		} else if ("add".equalsIgnoreCase(me)) {
			add(r, s);
		} else if ("delete".equalsIgnoreCase(me)) {
			delete(r, s);
		} else if ("search".equalsIgnoreCase(me)) {
			search(r, s);
		}

	}

	private void search(HttpServletRequest r, HttpServletResponse s) throws ServletException, IOException {
		String searchword = r.getParameter("search");
		String spage = r.getParameter("cpage");
		Pagebean pb = null;
		int page = 1;
		if (spage != null && spage.trim().length() != 0) {
			page = Integer.parseInt(spage);
		}
		if (searchword == null || "".equals(searchword.trim())) {
			try {
				searchword = "";
				pb = new Proservice().getall(page);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				searchword = searchword.trim();
				String sw = "%" + searchword + "%";
				pb = new Proservice().getall(page, sw, sw);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		r.getSession().setAttribute("searchword", searchword);
		r.setAttribute("pb", pb);
		System.out.println("pb=" + pb);
		r.getRequestDispatcher("/index.jsp").forward(r, s);
	}

	private void delete(HttpServletRequest r, HttpServletResponse s) throws ServletException, IOException {
		String[] deletes = r.getParameterValues("checkbox");
		int deleteres = 0;
		try {
			ManagerConnection.start();
			for (String i : deletes) {
				deleteres += new Proservice().delete(i);
			}
			if (deleteres != deletes.length) {
				ManagerConnection.rollback();
			}
			ManagerConnection.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ManagerConnection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			if (deleteres == deletes.length) {
				r.getSession().setAttribute("deleteres", "成功删除" + deleteres + "条数据");
			} else {
				r.getSession().setAttribute("deleteres", "删除失败");
			}

			s.sendRedirect("/productweb/pro?me=getall");

		}

	}

	private void add(HttpServletRequest r, HttpServletResponse s) throws ServletException, IOException {

		Map<String, String[]> map = r.getParameterMap();
		product pro = new product();

		try {
			BeanUtils.populate(pro, map);
			System.out.println("新增商品名字是：" + pro.getName());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String i = "失败";
		try {
			i = new Proservice().insert(pro);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			r.setAttribute("addres", i);
			r.getRequestDispatcher("/index.jsp").forward(r, s);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	// private void getall(HttpServletRequest r, HttpServletResponse s) throws
	// ServletException, IOException {
	// try {
	// String spage = r.getParameter("cpage");
	// int page = 1;
	// if (spage != null && spage.trim().length() != 0) {
	// page = Integer.parseInt(spage);
	// }
	// Pagebean pb = new Proservice().getall(page);
	// all = pb.getList();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } finally {
	// r.setAttribute("all", all);
	// r.getRequestDispatcher("/index.jsp").forward(r, s);
	// }
	// search(r, s);
	// }

}
