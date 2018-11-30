package cn.itcast.client.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.client.dao.UserClientDao;
import cn.itcast.client.domain.UserClient;
import cn.itcast.client.service.UserClientService;
import cn.itcast.utils.ImageUtil;

public class UserAction extends BaseAction implements ModelDriven<UserClient>{
	private String tel;
	
	public void setTel(String tel) {
		this.tel = tel;
	}

	private UserClient model=new UserClient();
	@Autowired
	private UserClientService userClienttService;
	@Autowired
	private RedisTemplate<String,String> rt;

	private String piccode;	

	public void setPiccode(String piccode) {
		this.piccode = piccode;
	}
	
	private String phoneVercode;
	@Override
	public UserClient getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	public void setPhoneVercode(String phoneVercode) {
		this.phoneVercode = phoneVercode;
	}
	
	private String vercode;
	public void setVercode(String vercode) {
		this.vercode = vercode;
	}



	@Autowired
	@Qualifier(value="jmsQueueTemplate")
	private JmsTemplate jq;

	@Action(value="userAction_genActiveCode")
	public String getActiveCode() throws IOException{
		String piccode1=ImageUtil.getRundomStr();
		session.put("piccode",piccode1);
		System.out.println(piccode1);
		ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
		ImageUtil.getImage(piccode1, out);		
		return null;
	}
	
	@Action(value="userAction_sendVerCode")
	public String sendVerCode() throws IOException{
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setCharacterEncoding("utf-8");
		PrintWriter wr = res.getWriter();
		String piccode1=(String) session.get("piccode");
		System.out.println(piccode+"\t"+piccode1);
		System.out.println(piccode.equals(piccode1)+"\t"+tel);
		if(piccode.equals(piccode1)){
			MessageCreator m=new MessageCreator() {			
				@Override
				public Message createMessage(Session session) throws JMSException {
					MapMessage m1= session.createMapMessage();
					m1.setString("telephone", tel);
					return m1;
				}
			};
			jq.send("ilcbs_client",m);
		}else{
			wr.write("验证码错误");
		}
		return null;
	}

	@Action(value="userAction_register")
	public String register() throws IOException{
		System.out.println("ua here 1");
		//判断图片验证码是否正确		
		//判断手机验证码是否正确
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setCharacterEncoding("utf-8");
		PrintWriter wr = res.getWriter();	
		String te2=model.getTelephone();
		String tel1 = rt.opsForValue().get(te2);
		System.out.println(tel1+"\t@"+phoneVercode+"\t@"+te2);
		if(tel1.equals(phoneVercode)){
			rt.delete(tel1);
			session.remove("piccode");
//			System.out.println("为什么删了后还有"+rt.opsForValue().get(telephone));
			userClienttService.saveOrUpdate(model);
			System.out.println("model="+model);
			wr.write("2");
		}else{
			wr.write("手机验证码错误");
			System.out.println("手机验证码错误");
		}
		//情况缓存
		return null;
	}

}
