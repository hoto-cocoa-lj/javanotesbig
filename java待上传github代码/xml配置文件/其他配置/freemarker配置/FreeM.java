package com.pinyougou.service;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeM {

	@Test
	public void tt1() throws Exception {
		// 1.创建配置类
		Configuration configuration = new Configuration(Configuration.getVersion());
		// 2.设置模板所在的目录
		String c = "E:/eclipseworkspace/pyg-parent/pyg-manager-web/src/main/resources";
		configuration.setDirectoryForTemplateLoading(new File(c));
		// 3.设置字符集
		configuration.setDefaultEncoding("utf-8");
		// 4.加载模板
		Template template = configuration.getTemplate("1.ftl");
		// 5.创建数据模型
		Map map = new HashMap();
		map.put("name", "张三 ");
		map.put("message", "欢迎来到神奇的品优购世界！");
		map.put("today", new Date());
		// 6.创建Writer对象
		Writer out = new FileWriter(new File("1.html"));
		// 7.输出
		template.process(map, out);
		// 8.关闭Writer对象
		out.close();
	}
}
