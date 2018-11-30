package cn.itcast.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.httpclient.HttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;

import cn.itcast.dao.DeptDao;
import cn.itcast.dao.ModuleDao;
import cn.itcast.dao.RoleDao;
import cn.itcast.dao.UserDao;
import cn.itcast.domain.Dept;
import cn.itcast.domain.Module;
import cn.itcast.domain.Role;
import cn.itcast.domain.User;
import cn.itcast.service.DeptService;
import cn.itcast.service.ModuleService;
import cn.itcast.service.RoleService;
import cn.itcast.service.UserService;
import cn.itcast.utils.HttpClientUtil;
import cn.itcast.utils.MailUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JpaTest {
	@Autowired
	private DeptDao dd;
	@Autowired
	private DeptService  ds;
	@Autowired
	private UserDao ud;
	@Autowired
	private UserService  us;
	@Autowired
	private RoleDao rd;
	@Autowired
	private RoleService  rs;
	@Autowired
	private ModuleDao md;
	@Autowired
	private ModuleService ms;
	
@Test
public void t1(){		//dao层通过id查找
	Dept d=dd.findOne("100zxc");
	System.out.println(d);
}
@Test
public void t2(){		//ds层通过id查找
	Dept d=ds.get("10zxc0");
	System.out.println(d);
}
@Test
public void t3(){		//ds层新增
	Dept d=new Dept();
	d.setDeptName("测试部门");
	d.setState(1);
	ds.saveOrUpdate(d);
	
}
@Test
public void t4_0(){		//ds层不查找直接update
	Dept d=new Dept();
	d.setId("4028918167028967016702896d050000");
	d.setState(0);
	ds.saveOrUpdate(d);
	
}
@Test
public void t4_1(){		//ds层查找后再修改并update
	Dept d=ds.get("4028918167028967016702896d050000");
	d.setDeptName("fuckyou!!");
	ds.saveOrUpdate(d);
	
}
@Test
public void t5(){		//ds层查找后再删除
	ds.deleteById("4028918167050000");
	
}
@Test
public void t6(){		//dd层使用jpql进行条件查找
	System.out.println(dd.findDeptByName("部"));
	
}
@Test
public void t7(){		//dd层使用sql进行条件查找
	System.out.println(dd.findDeptByName1("%部%").size());
	
}
@Test
public void user1(){		
	List<User> u = ud.findAll();
	System.out.println(u.size());
}
@Test
public void user2(){		
	List<User> u = us.findUserByUserNameLike("%o%");
	System.out.println(u.size());
}

@Test
public void role1(){		
	List<Role> u = rd.findAll();
	System.out.println(u.size());
}
@Test
public void role2(){		
	List<Role> u = rs.findRoleByNameLike("%C%");
	System.out.println(u.size());
}
@Test
public void module1(){		
	List<Module> u = ms.find(null);
	System.out.println(u.size());
}
@Test
public void json1(){		
	List a=new ArrayList();
	Gson g=new Gson();
	System.out.println(g.toJson(a));
}
@Test
public void mail1() throws MessagingException{		
	Properties p = new Properties();
	p.put("mail.smtp.host","smtp.163.com");
	p.setProperty("mail.stmp.auth","true");
	Session se = Session.getInstance(p);
	se.setDebug(true);
	
	MimeMessage m = new MimeMessage(se);
	InternetAddress from = new InternetAddress("17136413603@163.com");
	m.setFrom(from);
	InternetAddress to = new InternetAddress("yukitosairi5@163.com");
	m.setRecipient(RecipientType.TO,to);
	m.setRecipient(RecipientType.CC,from);
	m.setSubject("tomorrow");
	m.setText("tomorrow hehe");
	m.saveChanges();
	
	Transport tr = se.getTransport("smtp");
	tr.connect("smtp.163.com","17136413603@163.com","qq17136413603");
	tr.sendMessage(m,m.getAllRecipients());
	tr.close();
}
@Test
public void mail2() throws Exception{		
	MailUtil.sendMsg("yukitosairi5@163.com", "tom", "tomo");
}
@Test
public void mail3() throws Exception{		
	ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("applicationContext-mail.xml");
	JavaMailSender jm = (JavaMailSender) c.getBean("mailSender");
	SimpleMailMessage sm = (SimpleMailMessage) c.getBean("mailMessage");
	sm.setTo("17136413603@163.com");
	sm.setCc("yukitosairi5@163.com");
	
	sm.setSubject("同样的问题");
	sm.setText("用的时候出现了同样的问题");
	jm.send(sm);
}
@Test
public void mail4() throws Exception{		
	ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("applicationContext-mail.xml");
	JavaMailSender jm = (JavaMailSender) c.getBean("mailSender");

	MimeMessage me=jm.createMimeMessage();
	MimeMessageHelper sm=new MimeMessageHelper(me,true);
	
	sm.setFrom("17136413603@163.com");
//	sm.setTo("17136413603@163.com");
	sm.setTo("17136413603@163.com");
	
	sm.setSubject("同样的问题");
	sm.setText("<html><head></head><body><h1>hello!!spring image html mail</h1><a href=http://www.baidu.com>baidu</a><img src=cid:image /></body></html>");
	//要给cid为image的这个编号，上传一个图片到邮件服务器
	sm.addInline("image", new FileSystemResource(new File("E:\\1.jpg")));
	
	
	//6.带附件   第一个参数：代表这个附件在服务器上的文件名     第二个参数：代表文件资源在本地的位置
	sm.addAttachment("1.txt", new FileSystemResource(new File("E:\\1.txt")));
	
	//7.发送
	jm.send(me);
}
@Test
public void pc1() throws ClientProtocolException, IOException {
	CloseableHttpClient c = HttpClients.createDefault();
	HttpGet get = new HttpGet("http://localhost:8080/ilcbs_parent/loginAction_login?username=cgx&password=123456");	
	CloseableHttpResponse res = c.execute(get);
	HttpEntity e=res.getEntity();
	System.out.println(res.getStatusLine());
	if(e!=null){
		System.out.println(EntityUtils.toString(e));
	}
	res.close();
}
@Test
public void pc2() throws ClientProtocolException, IOException {
	CloseableHttpClient c = HttpClients.createDefault();
	HttpPost get = new HttpPost("http://localhost:8080/ilcbs_parent/loginAction_login");
	ArrayList<BasicNameValuePair> al=new ArrayList<BasicNameValuePair>();
	al.add(new BasicNameValuePair("username","cgx"));
	al.add(new BasicNameValuePair("password","123456"));
	
	UrlEncodedFormEntity ee = new UrlEncodedFormEntity(al);
	get.setEntity(ee);
	
	CloseableHttpResponse res = c.execute(get);
	HttpEntity e=res.getEntity();
	System.out.println(res.getStatusLine());
	if(e!=null){
		System.out.println(EntityUtils.toString(e));
	}
	res.close();
}
@Test
public void pc3(){
	String lu="http://localhost:8080/ilcbs_parent/loginAction_login";
	Map map=new HashMap();
	map.put("username","cgx");
	map.put("password","123456");
	HttpClientUtil.doPost(lu,map);
	String mu="http://localhost:8080/ilcbs_parent/sysadmin/roleAction_genzTreeNodes?id=4028a1c34ec2e5c8014ec2ebf8430001";
	String s1 = HttpClientUtil.doGet(mu);
	System.out.println(s1);
}
@Test
public void te1(){
	String a="123x ";
	System.out.println(Arrays.toString(a.split("x")));
}
}
