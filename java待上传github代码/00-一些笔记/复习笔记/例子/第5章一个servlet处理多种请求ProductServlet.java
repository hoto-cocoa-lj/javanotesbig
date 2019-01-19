package com.itheima.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.itheima.bean.Product;
import com.itheima.service.ProductService;
import com.mysql.fabric.xmlrpc.base.Data;

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决乱码
		request.setCharacterEncoding("utf-8");
		//获取method
		String method = request.getParameter("method");
		//根据method判断执行哪个方法
		if("findAll".equals(method)){
			findAll(request,response);
		}else if("addUI".equals(method)){
			addUI(request,response);
		}else if("add".equals(method)){
			add(request,response);
		}else if("edit".equals(method)){
			edit(request,response);
		}else if("update".equals(method)){
			update(request,response);
		}else if("delete".equals(method)){
			deletePro(request,response);
		}
	}
	/*
	 * 根据商品id删除商品信息
	 */
	private void deletePro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//获取pid
			String pid = request.getParameter("pid");
			//调用service和dao完成删除商品操作
			ProductService ps = new ProductService();
			ps.deletePro(pid);
			
			//请求转发到查询所有的链接上
			request.getRequestDispatcher("/product?method=findAll").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "删除商品失败");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}
	/**
	 * 根据id更新商品
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//获取map
			Map<String, String[]> map = request.getParameterMap();
			//创建bean
			Product pro = new Product();
			//把map中的数据拷贝到bean中
			BeanUtils.populate(pro, map);
			//调用service完成数据更新
			ProductService ps = new ProductService();
			ps.updatePro(pro);
			//请求转发到查询所有商品的链接上
			request.getRequestDispatcher("/product?method=findAll").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "更新商品失败");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		} 
	}
	/**
	 * 根据id查询数据
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//获取id
			String id = request.getParameter("id");
			//调用方法查询pro
			ProductService ps = new ProductService();
			Product pro=ps.getProByPid(id);
			
			//把pro放入request中
			request.setAttribute("pro", pro);
			//请求转发到edit.jsp
			request.getRequestDispatcher("/edit.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "查询单条记录商品失败");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		} 
	}
	/**
	 * 添加商品
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			//获取前台录入的数据  map
			Map<String, String[]> map = request.getParameterMap();
			//创建bean
			Product pro = new Product();
			//把map中的数据拷贝到bean中
			BeanUtils.populate(pro, map);
			//把pid和pdate放入bean中
			pro.setPid(UUIDCls.getUUid());
			pro.setPdate(new Date().toLocaleString());
			//调用service完成保存操作
			ProductService ps = new ProductService();
			ps.saveProduct(pro);
			//请求转发到查询链接
			request.getRequestDispatcher("/product?method=findAll").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "添加商品失败");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		} 
	}
	/**
	 * 防止具体的资源暴露在地址栏后面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/product.jsp").forward(request, response);
	}
	/**
	 * 查询所有商品
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//创建ProductService
			ProductService ps = new ProductService();
			//调用方法
			List<Product> list = ps.findAll();
			//把list集合放入request域对象中
			request.setAttribute("list", list);
			//请求转发到list.jsp
			request.getRequestDispatcher("/list.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "查询商品出现错误");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}