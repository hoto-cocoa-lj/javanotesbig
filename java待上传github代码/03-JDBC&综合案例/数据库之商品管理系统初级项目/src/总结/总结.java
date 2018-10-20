/**
 * 一.SQL语句
 * 
 * 1.分类:
 * 		DDL:数据库定义语言,主要对数据库,表,列,进行增删改查
 * 			a.创建数据库
 * 			create database 数据名   [charset 字符集名];
 * 			***b.创建表
 * 			create table 表名(
 * 				字段名 数据类型(长度) [约束],
 * 				字段名 数据类型(长度) [约束]
 * 			);
 * 			SQL中的数据类型:
 * 			整数:int
 * 			小数:double
 * 			字符串:varchar(长度),建议 用2的整数倍
 * 			日期:date  格式: 'YYYY-MM-DD'
 * 			SQL中的约束:
 * 			a.主键约束:primary key, 唯一且非空
 * 			b.自动增长列约束:auto_increment,必须是数值类型,而且一般我们会给主键加上自增长约束
 * 			c.唯一约束:Unique, 多个记录的该列的值不能相同
 * 			d.非空约束: Not Null,不能为null
 * 			e.默认约束: default 默认值,为某一个字段设置默认值
 * 			f.外键约束: foreign key  多表查询
 * 		DCL:数据库控制语言
 * 			了解(Oracle时说讲述)
 * 		**DML:数据库操作语言 : 对数据库中表中的数据进行增删改
 * 			增:
 * 			insert into 表名  (字段1,字段2...) values (值1,值2,值3);
 * 			注意事项:
 * 			1.字段和值要一一对应
 * 			2.如果是全字段,表名后面可以不写,但是values必须写上全部字段的值
 * 			3.值的写法: 除了数值类型的值,其他值必须用''或者""括起来
 * 			删:
 * 			delete from 表名 [where条件];
 * 			trancate table 表名:
 * 			以上两种删除表中数据的区别:
 * 			delete from 表名:只会删除记录,不会重置自动增长值,下次插入数据时,接着增加自动增长值
 * 			trancate table 表名: 摧毁表,再重建,即会删除所有记录,也会重置自动增长值(重置为1)
 * 			改:
 * 			update 表名 set 字段=值,字段=值 [where 条件]
 * 			
 * 		**DQL:数据库查询语言: 对数据库中表中的数据进行花式查询
 * 		单表查询:
 * 			a.条件查询:
 * 				select * from 表名  where 条件:
 * 				条件: 
 * 					大小: > < >= <= = != <>
 * 				区间:
 * 					between .. and .. 注意:只能判断数值和日期
 * 					比如: between '1990-05-30' and '2000-10-10'
 * 					age in (10,20)===> age=10 or age = 20;
 * 				为空:
 * 					is null;
 * 					is not null
 * 				模糊查询:
 * 					like '表达式',  符号_表示任意一个字符  符号%表示任意个任意字符
 * 			b.排序查询
 * 				select * from 表名 order by 字段  ASC(默认,升序)|DESC(降序);
 * 			c.聚合查询:
 * 				select count(*)|max(数值字段)|min(数值字段)|sum(数值字段)|avg(数值字段)	from 表名
 * 				注意事项:聚合函数查询出来的只有一个值,会忽略null值
 * 			d.分组查询:
 * 				select 分组字段,聚合函数 from 表名 group by 某个字段;
 * 				在分组查询中,要查询的字段必须是分组字段,也可以是聚合函数
 * 			e.分页查询
 * 				select * from 表名 limit 第几条记录,要查询第三条记录
 * 				比如: 我要查询第m页,每页有n条记录
 * 				第一页: limit (1-1)*n,n;
 * 				第二页: limit (2-1)*n,n;
 * 				第m页:  limit  (m-1)*n;n
 * 			f.去重复查询
 * 				select distinct 字段 from 表名;
 * 				查询出所有该字段,并且去掉重复值
 * 			多表:
 * 				1.为什么使用多表? 回顾第二天视频
 * 				2.表与表之间的关系:
 * 					一对多:商品分类 和 商品信息, 学生和考试成绩,省和市
 * 							必须两张表,一张主表,一张从表,
 * 							原则:从表必须有一个外键,这个外键 引用 主表的主键
 * 							如何给从表添加外键约束
 * 							Alter table 从表 add constraint 主表_从表_fk
 * 							foreign key (从表外键名) references 主表 (主键名);
 * 					多对多: 学生和课程, 演员和角色 , 老师和学生
 * 						   必须有三张表,两张正常表,一张中间表
 * 						 原则: 中间表,至少有两个字段,分别是外键,引用两张的主键
 * 						Alter table 中间表  add constraint _fk
 * 							foreign key (第一个外键名) references 第一张表 (主键名);
 * 						Alter table 中间表  add constraint _fk
 * 							foreign key (第二个外键名) references 第二张表 (主键名);
 * 					一对一: QQ号码,和QQ详细信息
 * 							可以用一张表示
 * 				多表查询语句:
 * 				1.交叉查询: 本身有错误的 ,实际上是一个叫做笛卡尔积的东西
 * 					select * from 表1,表2;
 * 				2.内连接:在交叉连接的基础上 添加条件(一般是主表.主键=从表.外键)
 * 					隐式内连接: 不写inner join 后面的条件用where判断
 * 						select * from 表A,表B where 表A.主键=表B.外键
 * 					显示内连接: 写上inner join 后面的条件用on判断
 * 						select * from 表A inner join 表B on 表A.主键=表B.外键
 * 
 * 				3.外链接:关键字 outer join
 * 					左外连接: left outer join,以左表作为,如果右表中没有和左表匹配的那条记录
 * 						那么也会将这条记录查询出来,没有值的地方填充null;
 * 					右外连接: right outer join
 * 						以右表作为,如果左表中没有和右表匹配的那条记录
 * 						那么也会将这条记录查询出来,没有值的地方填充null;
 * 				4.子查询:
 * 					一条select语句的结果,作为另外一条select语句的一部分
 * 					比如: 商品分类表和 商品详情表为例
 * 					查询 商品名字为 "霸王" 的商品的分类名
 * 					select cname from 商品分类表 where 分类id = 
 * 							(select 商品分类id from 商品详情表 where 商品名="霸王");
 * 					
 * 
 * 二.JDBC:
 * 		1.JDBC原生API
 * 			步骤:
 * 			1.注册驱动:
 * 			Class.forName("com.mysql.jdbc.Driver");
 * 			2.获取连接: 
 * 			Connection conn = 
 * 			DriverManager.getConnection("jdbc:mysql://ip:3306/数据库名","用户名","密码");
 * 			3.获取sql执行对象
 * 				Statement st = conn.createStatement();
 * 			4.执行sql语句,并且获取结果集(只有查询有结果集,其他都是int返回值)
 * 				int rows = st.excuteUpdate(sql);
 * 				ResultSet rs = st.excuteQuery(sql);
 * 			5.处理结果集
 * 				结果集中的两个方法
 * 				next();//判断有没有下一条记录
 * 				getXxx(int colid),getXxx(String colname);
 * 				其中Xxx可以是int,String,Double,Object
 * 			6.释放资源
 * 				conn.close(),st.close(),rs.close();
 * 		2.JDBCUtils工具类
 * 			//四个要素
 * 			private static String driverName = "com.mysql.jdbc.Driver";
 * 			private static String url = "jdbc:mysql://localhost:3306/day04";
 * 			private static String username = "root";
 * 			private static String password = "123";
 * 			//static
 * 			static{
 * 				Class.forName(driverName);
 * 			}
 * 			//获取连接:
 * 			public static Connection getConnection(){
 * 				DriverManager.getConnecton(url,username,password);
 * 			}
 * 			//关闭资源
 * 			public static void closeAll(Connection conn,Statement st,ResultSet rs){..}
 *		
 *		3.连接池: 是一个集合,预先获取一些连接对象,保存到集合中以便下次使用
 *			JDBC中规定:所有的连接池对象,必须实现 DataSource接口
 *			DBCP连接池:
 *				public class BasicDataSource implements DataSource;
 *				==================================================
 *				DBCPUtils工具类: 不使用配置文件
 *				//四个要素
 *				private static String driverName = "com.mysql.jdbc.Driver";
 *				private static String url = "jdbc:mysql://localhost:3306/day04";
 *				private static String username = "root";
 *				private static String password = "123";
 *				//连接池
 *				private static BasicDataSource ds = new BasicDataSource();
 *				static{
 *					//设置四大要素
 *					ds.setDriverClassName(driverName);
 *					ds.setUrl(url);
 *					ds.setUsername(username);
 *					ds.setPassword(password);
 *				}
 *				public static Connection getConnection(){
 *					ds.getConnection();	
 *				}
 *				//关闭资源
 * 				public static void closeAll(Connection conn,Statement st,ResultSet rs){..}
 *				==================================================
 *				DBCPUtils工具类: 使用配置文件(推荐使用Properties配置文件)
 *				//配置文件中写四个要素
 *				driverName=com.mysql.jdbc.Driver
 *				url=jdbc:mysql://localhost:3306/day04
 *				username=root
 *				password=123
 *				//连接池
 *				private static DataSource ds;
 *				static{
 *					Properties ps =  new Properties();
 *					ps.load(new FIleInputStream("dbcpconfig.properties"))
 *					//用到一个生产BasicDataSource的工厂类
 *					ds = BasicDataSourceFactory.createDataSource(ps);
 *					//设置四大要素
 *					//ds.setDriverClassName(ps.get("driverName"));
 *					//ds.setUrl(ps.get("url"));
 *					//ds.setUsername(ps.get("username"));
 *					//ds.setPassword(ps.get("password"));
 *				}
 *				public static Connection getConnection(){
 *					ds.getConnection();	
 *				}
 *				//关闭资源
 * 				public static void closeAll(Connection conn,Statement st,ResultSet rs){..}
 *				=============================================			
 *				C3P0连接池:不使用配置文件
 *				//四个要素
 *				private static String driverName = "com.mysql.jdbc.Driver";
 *				private static String url = "jdbc:mysql://localhost:3306/day04";
 *				private static String username = "root";
 *				private static String password = "123";
 *				//连接池对象
 *				ComboPooledDataSource ds = new ComboPooledDataSource();
 *				//静态代码块
 *				static{
 *					ds.setDriverClass(driverName);
 *					ds.setJdbcurl(url);
 *					ds.setUser(username);
 *					ds.setPassword(password);
 *				}
 *				public static Connection getConnection(){
 *					ds.getConnection();	
 *				}
 *				//关闭资源
 * 				public static void closeAll(Connection conn,Statement st,ResultSet rs){..}
 *				=============================================			
 *				C3P0连接池:使用配置文件
 *				//四个要素写到XMl文件中
 *				 <default-config><!-- 默认配置 -->
 *					<property name="driverClass">com.mysql.jdbc.Driver</property>
 *					<property name="jdbcUrl">jdbc:mysql://localhost:3306/day04</property>
 *					<property name="user">root</property>
 *					<property name="password">123</property>
 *					<property name="initialPoolSize">10</property>
 *				</default-config>
 *				//连接池对象
 *				//在创建ComboPooledDataSource对象时,底层会去自动读取并解析XML
 *				//但是这个XML必须Src根目录下,文件名字必须叫做c3p0-config.xml
 *				ComboPooledDataSource ds = new ComboPooledDataSource();
 *				
 *				public static Connection getConnection(){
 *					ds.getConnection();	
 *				}
 *				//关闭资源
 * 				public static void closeAll(Connection conn,Statement st,ResultSet rs){..}
 *			第三方框架: DBUtils工具类
 *			DBUtils: 主要是关系资源
 *				public static void closeQuietly(Connection conn,Statement st,ResultSet rs);
 *				
 *			QueryRunner:SQL语句执行对象
 *				不支持事务
 *				构造:QueryRunner(DataSource ds)
 *				1.int  update(String sql,Object... params)
 *				2. query(String sql,ResultSetHandler接口 rsh, Object...params)
 *				支持事务
 *				构造:QueryRunner()
 *				1.int  update(Connection conn,String sql,Object... params)
 *				2. query(Connection conn,String sql,ResultSetHandler接口 rsh, Object...params)
 *			ResultSetHandler接口 的实现类
 *				Object[]  ArrayHandler:
 *					把结果集中的第一条记录,封装到一个数组中,数组中的每个元素都是字段的值
 *				List<Object[]>  ArrayListHandler:
 *					把结果集中的每一条记录,分别封装到一个数组中,数组中的每个元素都是字段的值
 *					把所有数组再封装到List集合中,并返回这个集合
 *				JavaBean BeanHandler:
 *					把结果集中的第一条记录,封装到一个JavaBean对象中,并返回这个对象
 *				List<JavaBean> BeanListHandler:
 *					把结果集中的每一条记录,分别封装到一个JavaBean对象中,
 *					把这些对象保存到集合中,并返回这个集合
 *				Map<String,Object> MapHandler:
 *				List<Map<String,Object>> MapListHandler
 *				List<Object> ColumnHandler:
 *				Object Scalarhandler:
 *		事务:
 *			开始事务: 
 *				try{
 *					conn.setAutoCommit(false);
 *					insert(..)
 *					insert(..)
 *					insert(..)
 *					conn.commit();
 *				}catch(Exception ex){
 *					conn.rollback();
 *				}
 *			ThreadLocal:底层是一个Map<Thread,Object>
 *				set(Object 值);==> map.set(当前线程对象,值)
 *				get();===>map.get(当前线程对象)
 *				remove();====>map.remove(当前线程对象)
 *				
 */