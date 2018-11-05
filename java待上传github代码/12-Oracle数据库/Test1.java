package cc.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.driver.OracleCallableStatement;
import oracle.jdbc.oracore.OracleType;
import oracle.sql.NUMBER;

public class Test1 {
	private Connection c;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@192.168.88.131:1521:orcl";
	private String user = "scott";
	private String password = "tiger";	
	private CallableStatement call;
	private ResultSet rs;
	
	@Before		//测试前运行的注解
	public void init() throws Exception{
			Class.forName(driver);
			c = DriverManager.getConnection(url,user,password);
			System.out.println("get connection");
	}
	
	@After 		//测试后运行的注解
	public void after() throws Exception{
		if(rs!=null){
			rs.close();
		}
		if(call!=null){
			call.close();
		}
		if(c!=null){
			c.close();
		}
		System.out.println("close all");
	}
	
	@Test
	public void test1() throws Exception{
		//forname方法原来是这么用的。
//		Test1 t=(Test1) Class.forName("cc.test.Test1").newInstance();
//		t.test1();
		call = c.prepareCall("select * from emp");
		ResultSet rs=call.executeQuery();		
//		Statement st=c.createStatement();
//		ResultSet rs=st.executeQuery("select * from emp");	
		while(rs.next()){
			System.out.println(rs.getString(1)+"\t"+rs.getString("ename"));
		}
	}
	
	@Test		//调用存储过程,不能加;否则报错
	public void test2() throws SQLException{
		call=c.prepareCall("call p(?)");
		call.setObject(1, 7499);
		call.execute();
	}
	
	@Test		//调用存储过程,不能加;否则报错
	public void test3() throws SQLException{
		call=c.prepareCall("call pel(?,?)");
		call.setObject(1, 10);
		call.registerOutParameter(2,OracleTypes.CURSOR);
		call.execute();
		rs = ((OracleCallableStatement)call).getCursor(2);
		while(rs.next()){
			System.out.println(rs.getString(1)+"\t"+rs.getString("ename"));
		}
	}
	
	@Test		//调用存储过程,不能加;否则报错
	public void test4() throws SQLException{
		call=c.prepareCall("call p2(?,?)");
		call.setObject(1,7499);
		call.registerOutParameter(2,OracleTypes.NUMBER);
		call.execute();
		 NUMBER n = ((OracleCallableStatement)call).getNUMBER(2);
		System.out.println(n.intValue()+"\t"+n.doubleValue());
	}
	@Test		//调用存储过程,不能加;否则报错
	public void test5() throws SQLException{
		call=c.prepareCall("{?=call f(?)}");
		call.setObject(2,10);
		call.registerOutParameter(1,OracleTypes.VARCHAR);
		call.execute();
		 String n = call.getString(1);
		System.out.println(n);
	}

}
