package com.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.service.CatoService;
import com.serviceimpl.CatoServiceImpl;
import com.utils.UploadUtils;

public class CatoServlet extends BaseServlet {
	public String getcato(HttpServletRequest r, HttpServletResponse s) throws IOException {
		CatoService cs = new CatoServiceImpl();
		try {
			String js = cs.fromysql();
			// String js = cs.fromredis();
			// System.out.println("catoservlet js=" + js);
			s.setHeader("content-type", "text/text;charset=utf-8");
			s.getWriter().print(js);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	// https://blog.csdn.net/u014785687/article/details/73863964
	public String sc(HttpServletRequest r, HttpServletResponse s)
			throws IOException, FileUploadException, IllegalAccessException, InvocationTargetException {
		DiskFileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload up = new ServletFileUpload(fac);
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> map1 = new HashMap<String, String>();
		BeanUtils.populate(map1, r.getParameterMap());
		List<FileItem> list = up.parseRequest(r);
		for (FileItem i : list) {
			if (i.isFormField()) {
				map.put(i.getFieldName(), i.getString("utf-8"));
			} else {
				String oldFileName = i.getName();
				String newFileName = UploadUtils.getUUIDName(oldFileName);
				InputStream is = i.getInputStream();
				String realpath = r.getServletContext().getRealPath("/products/3");
				String dir = UploadUtils.getDir(newFileName);
				String path = realpath + dir;
				File newDir = new File(path);
				if (!newDir.exists()) {
					newDir.mkdirs();
				}
				File finalFile = new File(newDir, newFileName);
				if (!finalFile.exists()) {
					finalFile.createNewFile();
				}
				OutputStream os = new FileOutputStream(finalFile);
				IOUtils.copy(is, os);
				IOUtils.closeQuietly(is);
				IOUtils.closeQuietly(os);
				System.out.println("map1=" + map1);
				System.out.println("map=" + map);
				System.out.println("products/3" + dir + "/" + newFileName);

			}
		}

		// System.out.println("username=" + username + "\tup=" + up);
		return null;

	}
}
