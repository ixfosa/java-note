## JDBC 概述和架构

Java数据库连接，（Java Database Connectivity，简称JDBC）是Java语言中用来规范客户端程序如何来访问数据库的应用程序接口，提供了诸如查询和更新数据库中数据的方法。JDBC也是Sun Microsystems的商标。JDBC是面向关系型数据库的。

> 使用java代码（程序）`发送sql语句`的技术，就是jdbc技术！！！！

JDBC 驱动程序共分四种类型：

+ JDBC-ODBC桥

  这种类型的驱动把所有JDBC的调用传递给ODBC，再让后者调用数据库本地驱动代码（也就是数据库厂商提供的数据库操作二进制代码库，例如Oracle中的oci.dll）。

+ 本地API驱动

  这种类型的驱动通过客户端加载数据库厂商提供的本地代码库（C/C++等）来访问数据库，而在驱动程序中则包含了Java代码。

+ 网络协议驱动

  这种类型的驱动给客户端提供了一个网络API，客户端上的JDBC驱动程序使用[套接字（Socket）来调用服务器上的中间件程序，后者在将其请求转化为所需的具体API调用。

+ 本地协议驱动

  这种类型的驱动使用Socket，直接在客户端和数据库间通信。



## JDBC 编程步骤

建立连接的五大步骤：

+ 加载（注册）数据库
  + jar: mysql-connector-java-5.1.7-bin.jar
+ 建立链接（使用jdbc发送sql前提）
  + 
    数据库的`P`地址
     + `端口`
     + 数据库`用户名`
     + `密码`
+ 执行SQL语句
  + `Statement`
  + `PreparedStatement`
  + `CallableStatement`
+ 处理结果集
  + `ResultSet`
+ 关闭数据库

```java
/**
 * jdbc连接数据库
 *
 */
public class Demo {
    
	// 连接数据库的URL
	private String url = "jdbc:mysql://localhost:3306/ixfosa";
	                    // jdbc协议:数据库子协议:主机:端口/连接的数据库   

	private String user = "root";      // 用户名
	private String password = "root";  // 密码
	
	/**
	 * 第一种方法
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception{
		// 1.创建驱动程序类对象
		Driver driver = new com.mysql.jdbc.Driver();     // 新版本
		// Driver driver = new org.gjt.mm.mysql.Driver(); //旧版本
		
		// 设置用户名和密码
		Properties props = new Properties();
		props.setProperty("user", user);
		props.setProperty("password", password);
		
		// 2.连接数据库，返回连接对象
		Connection conn = driver.connect(url, props);
		
		System.out.println(conn);
	}
	
	/**
	 * 使用驱动管理器类连接数据库(注册了两次，没必要)
	 * @throws Exception
	 */
	@Test
	public void test2() throws Exception{
        
		Driver driver = new com.mysql.jdbc.Driver();
		// Driver driver2 = new com.oracle.jdbc.Driver();
        
		// 1.注册驱动程序(可以注册多个驱动程序)
		DriverManager.registerDriver(driver);
		//DriverManager.registerDriver(driver2);
		
		// 2.连接到具体的数据库
		Connection conn = DriverManager.getConnection(url, user, password);
		System.out.println(conn);
		
	}
	
	/**
	 * （推荐使用这种方式连接数据库）
	 * 推荐使用加载驱动程序类  来注册驱动程序 
	 * @throws Exception
	 */
	@Test
	public void test3() throws Exception{
        
		// Driver driver = new com.mysql.jdbc.Driver();
		
		// 通过得到字节码对象的方式加载静态代码块，从而注册驱动程序
		Class.forName("com.mysql.jdbc.Driver");
		
		// Driver driver2 = new com.oracle.jdbc.Driver();
		// 1.注册驱动程序(可以注册多个驱动程序)
		// DriverManager.registerDriver(driver);
		// DriverManager.registerDriver(driver2);
		
		// 2.连接到具体的数据库
		Connection conn = DriverManager.getConnection(url, user, password);
		System.out.println(conn);
	}
}
```



## JDBC接口核心的API

JDBC API主要位于JDK中的`java.sql`包中（之后扩展的内容位于`javax.sql`包中）+

+ `DriverManager`：负责加载各种不同驱动程序（Driver），并根据不同的请求，向调用者返回相应的数据库连接（Connection）。
+ `Driver`：驱动程序，会将自身加载到DriverManager中去，并处理相应的请求并返回相应的数据库连接（Connection）。
+ `Connection`：数据库连接，负责与进行数据库间通讯，SQL执行以及事务处理都是在某个特定Connection环境中进行的。可以产生用以执行SQL的Statement。
+ `Statement`：用以执行SQL查询和更新（针对静态SQL语句和单次执行）。
+ `PreparedStatement`：用以执行包含动态参数的SQL查询和更新（在服务器端编译，允许重复执行以提高效率）。
+ `CallableStatement`：用以调用数据库中的存储过程。
+ `SQLException`：代表在数据库连接的建立和关闭和SQL语句的执行过程中发生了例外情况（即错误）。



```java
java.sql.*   和  javax.sql.*

|- Driver接口： 表示java驱动程序接口。所有的具体的数据库厂商要来实现此接口。
    |- connect(url, properties):  连接数据库的方法。
    	url:       连接数据库的URL 
        URL语法:    jdbc协议:数据库子协议: // 主机:端口/数据库
		user:      数据库的用户名
  	    password:  数据库用户密码
            
            
|- DriverManager类： 驱动管理器类，用于管理所有注册的驱动程序
    |- registerDriver(driver): 注册驱动类对象
    |- Connection getConnection(url,user,password):  获取连接对象
            

|- Connection接口： 表示java程序和数据库的连接对象。
	|- Statement createStatement():                      创建Statement对象
	|- PreparedStatement prepareStatement(String sql):   创建PreparedStatement对象
	|- CallableStatement prepareCall(String sql):        创建CallableStatement对象

            
|- Statement接口： 用于执行静态的sql语句
		|- int executeUpdate(String sql):       执行静态的更新sql语句（DDL，DML）
		|- ResultSet executeQuery(String sql):  执行的静态的查询sql语句（DQL）
	|- PreparedStatement接口：用于执行预编译sql语句
			|- int executeUpdate():       执行预编译的更新sql语句（DDL，DML）
			|-ResultSet executeQuery():   执行预编译的查询sql语句（DQL）
    |- CallableStatement接口：用于执行存储过程的sql语句（call xxx）
            |- ResultSet executeQuery():  调用存储过程的方法
            
|- ResultSet接口：用于封装查询出来的数据
	|- boolean next() ： 将光标移动到下一行
	|- getXX() : 获取列的值
```



## JDBC 工具类 JdbcUtil

```properties
url=jdbc:mysql://localhost:3306/ixfosa
user=root
password=root
driverClass=com.mysql.jdbc.Driver
```

```java
/**
 * 	jdbc工具类
 */
public class JdbcUtil {
    
	private static String url = null;
	private static String user = null;
	private static String password = null;
	private static String driverClass = null;
	
	/**
	 * 	静态代码块中（只加载一次）
	 */
	static{
		try {
			// 读取db.properties文件
			Properties props = new Properties();
			/**
			 *  . 代表java命令运行的目录
			 *  在java项目下，. java命令的运行目录从项目的根目录开始
			 *  在web项目下，  . java命令的而运行目录从tomcat/bin目录开始
			 *  所以不能使用点.
			 */
			// FileInputStream in = new FileInputStream("./src/db.properties");
			
			/**
			 * 使用类路径的读取方式
			 *  / : 斜杠表示classpath的根目录
			 *     在java项目下，classpath的根目录从bin目录开始
			 *     在web项目下，classpath的根目录从WEB-INF/classes目录开始
			 */
			InputStream in = JdbcUtil.class.getResourceAsStream("/db.properties");
			
			// 加载文件
			props.load(in);
            
			// 读取信息
			url = props.getProperty("url");
			user = props.getProperty("user");
			password = props.getProperty("password");
			driverClass = props.getProperty("driverClass");
			
			
			// 注册驱动程序
			Class.forName(driverClass);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("驱程程序注册出错");
		}
	}

	/**
	 * 	抽取获取连接对象的方法
	 */
	public static Connection getConnection(){
		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * 	释放资源的方法
	 */
	public static void close(Connection conn, Statement stmt){
		if(stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
	
	public static void close(Connection conn, Statement stmt, ResultSet rs){
		if(rs != null)
			try {
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new RuntimeException(e1);
			}
		if(stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
}
```



## Statement执行sql语句

### 执行DDL语句

```java
/**
 * 	使用Statement对象执行静态sql语句
 */
public class Demo {

	private String url = "jdbc:mysql://localhost:3306/jdbc";
	private String user = "root";
	private String password = "root";
	/**
	 * 执行DDL语句(创建表)
	 */
	@Test
	public void test1(){
		Statement stmt = null;
		Connection conn = null;
		try {
			// 1.驱动注册程序
			Class.forName("com.mysql.jdbc.Driver");
			
			// 2.获取连接对象
			conn = DriverManager.getConnection(url, user, password);
			
			// 3.创建Statement
			stmt = conn.createStatement();
			
			// 4.准备sql
			String sql = "CREATE TABLE student(id INT PRIMARY KEY AUTO_INCREMENT,NAME VARCHAR(20),gender VARCHAR(2))";
			
			// 5.发送sql语句，执行sql语句,得到返回结果
			int count = stmt.executeUpdate(sql);
			
			// 6.输出
			System.out.println("影响了"+count+"行！");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally{
			// 7.关闭连接(顺序:后打开的先关闭)
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			if(conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
		}		
	}
}
```



### 执行DML语句

```java
/**
 * 	使用Statement执行DML语句
 */
public class Demo {
    
	private String url = "jdbc:mysql://localhost:3306/ixfosa";
	private String user = "root";
	private String password = "root";

	/**
	 * 	增加
	 */
	@Test
	public void testInsert(){
		Connection conn = null;
		Statement stmt = null;
		try {
			// 通过工具类获取连接对象
			conn = JdbcUtil.getConnection();
			
			// 3.创建Statement对象
			stmt = conn.createStatement();
			
			// 4.sql语句
			String sql = "INSERT INTO student(NAME,gender) VALUES('李四','女')";
			
			// 5.执行sql
			int count = stmt.executeUpdate(sql);
			
			System.out.println("影响了"+count+"行");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally{
			// 关闭资源
			JdbcUtil.close(conn, stmt);
		}
	}
	
	/**
	 * 	修改
	 */
	@Test
	public void testUpdate(){
		Connection conn = null;
		Statement stmt = null;
		// 模拟用户输入
		String name = "陈六";
		int id = 3;
		try {
			/*
			// 1.注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			
			// 2.获取连接对象
			conn = DriverManager.getConnection(url, user, password);
			*/
            
			//通过工具类获取连接对象
			conn = JdbcUtil.getConnection();
			
			//3.创建Statement对象
			stmt = conn.createStatement();
			
			//4.sql语句
			String sql = "UPDATE student SET NAME='"+name+"' WHERE id="+id+"";
			
			System.out.println(sql);
			
			// 5.执行sql
			int count = stmt.executeUpdate(sql);
			
			System.out.println("影响了"+count+"行");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally{
			JdbcUtil.close(conn, stmt);
		}
	}
	
	/**
	 * 	删除
	 */
	@Test
	public void testDelete(){
		Connection conn = null;
		Statement stmt = null;
        
		// 模拟用户输入
		int id = 3;
        
		try {
			// 通过工具类获取连接对象
			conn = JdbcUtil.getConnection();
			
			//3.创建Statement对象
			stmt = conn.createStatement();
			
			//4.sql语句
			String sql = "DELETE FROM student WHERE id=" + id + "";
			
			System.out.println(sql);
			
			// 5.执行sql
			int count = stmt.executeUpdate(sql);
			
			System.out.println("影响了" + count+"行");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally{
			JdbcUtil.close(conn, stmt);
		}
	}
}
```



### 执行DQL语句

```java
/**
 * 	使用Statement执行DQL语句（查询操作）
 */
public class Demo {

	@Test
	public void test1(){
        
		Connection conn = null;
		Statement stmt = null;
        
		try{
			// 获取连接
			conn = JdbcUtil.getConnection();
			// 创建Statement
			stmt = conn.createStatement();
			// 准备sql
			String sql = "SELECT * FROM student";
			// 执行sql
			ResultSet rs = stmt.executeQuery(sql);
			
			//移动光标
			/*
			boolean flag = rs.next();
			flag = rs.next();
			flag = rs.next();
			
			if(flag){
				// 取出列值
				// 索引
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String gender = rs.getString(3);
				System.out.println(id + "," + name + "," + gender);
				
				// 列名称
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				System.out.println(id+","+name+","+gender);
			}
			*/
			
			//遍历结果
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				System.out.println(id + "," + name + "," + gender);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, stmt);
		}
	}
}
```



## PreparedStatement执行sql语句

**PreparedStatement vs Statment**

+ 语法不同：PreparedStatement可以使用预编译的sql，而Statment只能使用静态的sql
+ 效率不同： PreparedStatement可以使用sql缓存区，效率比Statment高
+ 安全性不同： PreparedStatement可以有效防止sql注入，而Statment不能防止sql注入。

> 推荐使用`PreparedStatement`

```java
/**
 * PreparedStatement執行sql語句
 *
 */
public class Demo {

	/**
	 * 增加
	 */
	@Test
	public void testInsert() {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// 1.获取连接
			conn = JdbcUtil.getConnection();
			
			// 2.准备预编译的sql
            // ? 表示一个参数的占位符
			String sql = "INSERT INTO student(NAME,gender) VALUES(?, ?)"; 
			
			// 3.执行预编译sql语句(检查语法)
			stmt = conn.prepareStatement(sql);
			
			// 4.设置参数值
			/**
			 * 参数一： 参数位置  从1开始
			 */
			stmt.setString(1, "李四");
			stmt.setString(2, "男");
			
			// 5.发送参数，执行sql
			int count = stmt.executeUpdate();
			
			System.out.println("影响了" + count + "行");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, stmt);
		}
	}
	
	/**
	 * 修改
	 */
	@Test
	public void testUpdate() {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// 1.获取连接
			conn = JdbcUtil.getConnection();
			
			// 2.准备预编译的sql
			String sql = "UPDATE student SET NAME=? WHERE id=?"; //?表示一个参数的占位符
			
			// 3.执行预编译sql语句(检查语法)
			stmt = conn.prepareStatement(sql);
			
			// 4.设置参数值
			stmt.setString(1, "王五");
			stmt.setInt(2, 9);
			
			// 5.发送参数，执行sql
			int count = stmt.executeUpdate();
			
			System.out.println("影响了" + count + "行");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, stmt);
		}
	}
	
	/**
	 * 删除
	 */
	@Test
	public void testDelete() {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// 1.获取连接
			conn = JdbcUtil.getConnection();
			
			// 2.准备预编译的sql
			String sql = "DELETE FROM student WHERE id=?"; // ?表示一个参数的占位符
			
			// 3.执行预编译sql语句(检查语法)
			stmt = conn.prepareStatement(sql);
			
			//4.设置参数值
			stmt.setInt(1, 9);
			
			// 5.发送参数，执行sql
			int count = stmt.executeUpdate();
			
			System.out.println("影响了" + count + "行");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, stmt);
		}
	}
	
	/**
	 * 查询
	 */
	@Test
	public void testQuery() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// 1.获取连接
			conn = JdbcUtil.getConnection();
			
			// 2.准备预编译的sql
			String sql = "SELECT * FROM student"; 
			
			// 3.预编译
			stmt = conn.prepareStatement(sql);
			
			// 4.执行sql
			rs = stmt.executeQuery();
			
			// 5.遍历rs
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				System.out.println(id + "," + name + "," + gender);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			// 6.关闭资源
			JdbcUtil.close(conn,stmt,rs);
		}
	}
}
```



## CallableStatement执行存储过程

```java
/**
 * 使用 CablleStatement 调用存储过程
 */
public class Demo {

	/**
	 * 调用带有输入参数的存储过程
	 * CALL pro_findById(4);
	 */
	@Test
	public void test1(){
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		try {
			// 获取连接
			conn = JdbcUtil.getConnection();
			
			// 准备sql
			String sql = "CALL pro_findById(?)"; //可以执行预编译的sql
			
			// 预编译
			stmt = conn.prepareCall(sql);
			
			// 设置输入参数
			stmt.setInt(1, 6);
			
			// 发送参数
            // 注意： 所有调用存储过程的sql语句都是使用 executeQuery 方法执行！
			rs = stmt.executeQuery(); 
			
			// 遍历结果
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				System.out.println(id+","+name+","+gender);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, stmt ,rs);
		}
	}
	
	/**
	 * 执行带有输出参数的存储过程
	 * CALL pro_findById2(5,@NAME);
	 */
	@Test
	public void test2(){
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		try {
			// 获取连接
			conn = JdbcUtil.getConnection();
			// 准备sql
			String sql = "CALL pro_findById2(?,?)"; //第一个？是输入参数，第二个？是输出参数
			
			// 预编译
			stmt = conn.prepareCall(sql);
			
			// 设置输入参数
			stmt.setInt(1, 6);
            
			// 设置输出参数(注册输出参数)
			/**
			 * 参数一： 参数位置
			 * 参数二： 存储过程中的输出参数的jdbc类型    VARCHAR(20)
			 */
			stmt.registerOutParameter(2, java.sql.Types.VARCHAR);
			
			// 发送参数，执行
			stmt.executeQuery(); //结果不是返回到结果集中，而是返回到输出参数中
			
			// 得到输出参数的值
			/**
			 * 索引值： 预编译sql中的输出参数的位置
			 */
			String result = stmt.getString(2); // getXX方法专门用于获取存储过程中的输出参数
			
			System.out.println(result);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, stmt ,rs);
		}
	}
}
```



## 预编译sql处理(防止sql注入)

```sql
-- 创建数据库
CREATE DATABASE jdbc_demo DEFAULT CHARACTER SET utf8;

-- 创建表
USE jdbc_demo;
CREATE TABLE admin(
    id INT PRIMARY KEY AUTO_INCREMENT,
    userName VARCHAR(20),
    pwd VARCHAR(20)
)
```

```java
public class App {
	
	// 全局参数
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	

	// 1. 没有使用防止sql注入的案例
	@Test
	public void testLogin() {
		
		// 1.0 模拟登陆的用户名，密码
		String userName = "tom";
		//String pwd = "8881";
		String pwd = " ' or 1=1 -- ";
		
		// SQL语句
		String sql = "select * from admin where userName='"+ userName+"'  and pwd='"+pwd+"' ";
		System.out.println(sql);
		try {
			// 1.1 加载驱动，创建连接
			con = JdbcUtil.getConnection();
			// 1.2 创建stmt对象
			stmt = con.createStatement();
			// 1.3 执行查询
			rs = stmt.executeQuery(sql);
			// 业务判断
			if (rs.next()) {
				System.out.println("登陆成功, 编号：" + rs.getInt("id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 1.4 关闭
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	// 2. 使用PreparedStatement, 防止sql注入
	@Test
	public void testLogin2() {
		
		// 1.0 模拟登陆的用户名，密码
		String userName = "tom";
		//String pwd = "8881";
		String pwd = " ' or 1=1 -- ";
		
		// SQL语句
		String sql = "select * from admin where userName=?  and pwd=? ";
		try {
			// 1.1 加载驱动，创建连接
			con = JdbcUtil.getConnection();
			// 1.2 创建pstmt对象
			pstmt = con.prepareStatement(sql);   // 对sql语句预编译
			// 设置占位符值
			pstmt.setString(1, userName);
			pstmt.setString(2, pwd);
			
			// 1.3 执行
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("登陆成功，" + rs.getInt("id"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 1.4 关闭
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
```



## 批处理

- MySQL 不支持批量处理，但在新的JDBC驱动中，可以通过在 URL 连接中设置参数来优化：`rewriteBatchedStatements=true`
- 步骤：
  - 将给定的 SQL 命令添加到此 Statement 对象的当前命令列表中，或者设置 SQL 语句中指定位置的参数值后添加到此 PreparedStatement 对象的当前命令列表中
  - 执行批处理
  - 清除缓存



批处理相关方法

```java
|-- Statement
	批处理相关方法
	void addBatch(String sql)     添加批处理
	void clearBatch()             清空批处理
	int[] executeBatch()          执行批处理
```



实现：

+ Admin.java         实体类封装数据

  ```java
  // 实体类封装数据
  public class Admin {
  
  	private String userName;
  	private String pwd;
  	public String getUserName() {
  		return userName;
  	}
  	public void setUserName(String userName) {
  		this.userName = userName;
  	}
  	public String getPwd() {
  		return pwd;
  	}
  	public void setPwd(String pwd) {
  		this.pwd = pwd;
  	}
  }
  ```

  

+ AdminDao.java      封装所有的与数据库的操作

  ```java
  // 封装所有的与数据库的操作
  public class AdminDao {
  	
  	// 全局参数
  	private Connection con;
  	private PreparedStatement pstmt;
  	private ResultSet rs;
  
  	// 批量保存管理员
  	public void save(List<Admin> list) {
  		// SQL
  		String sql = "INSERT INTO admin(userName, pwd) values(?, ?)";
  		
  		try {
  			// 获取连接
  			con = JdbcUtil.getConnection();
  			// 创建stmt 
  			pstmt = con.prepareStatement(sql);  // 【预编译SQL语句】
  			
  			for (int i = 0; i < list.size(); i++) {
  				Admin admin = list.get(i);
  				// 设置参数
  				pstmt.setString(1, admin.getUserName());
  				pstmt.setString(2, admin.getPwd());
  				
  				// 添加批处理
  				pstmt.addBatch();						// 【不需要传入SQL】
  				
  				// 测试：每5条执行一次批处理
  				if (i % 5 == 0) {
  					// 批量执行 
  					pstmt.executeBatch();
  					// 清空批处理
  					pstmt.clearBatch();
  				}
  			}
  			
  			// 批量执行 
  			pstmt.executeBatch();
  			// 清空批处理
  			pstmt.clearBatch();
  			
  		} catch (Exception e) {
  			e.printStackTrace();
  		} finally {
  			JdbcUtil.closeAll(con, pstmt, rs);
  		}
  	}
  }
  
  ```

  

+ App.java      测试

  ```java
  public class App {
  
  	// 测试批处理操作
  	@Test
  	public void testBatch() throws Exception {
  		
  		// 模拟数据
  		List<Admin> list = new ArrayList<Admin>();
  		for (int i = 1; i < 21; i++) {
  			Admin admin = new Admin();
  			admin.setUserName("Jack" + i);
  			admin.setPwd("888" + i);
  			list.add(admin);
  		}
  		
  		// 保存
  		AdminDao dao = new AdminDao();
  		dao.save(list);
  	}
  }
  ```

  

## 获取自增长值，插入数据

### 设计数据库

员工表 （外键表） 【员工表有一个外键字段，引用了部门表的主键】

部门表（主键表）

```java
部门
CREATE TABLE dept(
   deptId INT PRIMARY KEY AUTO_INCREMENT,
   deptName VARCHAR(20)
);

-- 员工
CREATE TABLE employee(
   empId INT PRIMARY KEY AUTO_INCREMENT,
   empName VARCHAR(20),
   dept_id  INT   --  外键字段   
);

-- 给员工表添加外键约束
ALTER TABLE employee ADD CONSTRAINT FK_employee_dept_deptId
	FOREIGN KEY(dept_id) REFERENCES dept(deptId) ;
```

编码总体思路

保存员工及其对应的部门！

​	步骤：

1. 

2. 



### 设计javabean

```java
public class Dept {
    
	private int id;
	private String deptName;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}
```

```java
public class Employee {

	private int empId;
	private String empName;
	// 关联的部门
	private Dept dept;
	
	
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
}
```



### 设计dao

```java
public class EmpDao {
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

	// 保存员工，同时保存关联的部门
	public void save(Employee emp){
		
		// 保存部门
		String sql_dept = "insert into dept(deptName) values(?)";
		// 保存员工
		String sql_emp = "INSERT INTO employee (empName,dept_id) VALUES (?,?)";
		// 部门id
		int deptId = 0;
		
		try {
			// 连接
			con = JdbcUtil.getConnection();
			
			/*****保存部门，获取自增长*******/
            // 先保存部门, 再得到部门主键，再保存员
			// 【一、需要指定返回自增长标记】
			pstmt = con.prepareStatement(sql_dept, Statement.RETURN_GENERATED_KEYS);
			// 设置参数
			pstmt.setString(1, emp.getDept().getDeptName());
			// 执行
			pstmt.executeUpdate();
			
			// 【二、获取上面保存的部门子增长的主键】
			rs =  pstmt.getGeneratedKeys();
			// 得到返回的自增长字段
			if (rs.next()) {
				deptId = rs.getInt(1);
			}
			
			/*****保存员工*********/
			pstmt = con.prepareStatement(sql_emp);
			// 设置参数
			pstmt.setString(1, emp.getEmpName());
			pstmt.setInt(2, deptId);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(con, pstmt, rs);
		}
	}
}
```



### 测试代码

```java
public class App {

	// 保存员工
	@Test
	public void testSave() throws Exception {
		// 模拟数据
		Dept d = new Dept();
		d.setDeptName("应用开发部");
		Employee emp = new Employee();
		emp.setEmpName("ixfosa");
		emp.setDept(d);   // 关联
		
		// 调用dao保存
		EmpDao empDao = new EmpDao();
		empDao.save(emp);
		
	}
}
```



## 事务处理

事务使指一组`最小逻辑操作单元`，里面有多个操作组成。 组成事务的每一部分必须要`同时提交成功`，如果有一个操作失败，整个操作就`回滚`。

### 事务ACID特性

+ `原子性`，是一个最小逻辑操作单元 !
  + 原子性是指事务是一个不可分割的工作单位，事务中的操作要么都发生，要么都不发生。 
+ `一致性`，事务过程中，数据处于一致状态。
  + 事务必须使数据库从一个一致性状态变换到另外一个一致性状态。
+ `持久性`， 事务一旦提交成功，对数据的更改会反映到数据库中。
  + 持久性是指一个事务一旦被提交，它对数据库中数据的改变就是永久性的，接下来即使数据库发生故障也不应该对其有任何影响
+ `隔离性`， 事务与事务之间是隔离的
  + 事务的隔离性是多个用户并发访问数据库时，数据库为每一个用户开启的事务，不能被其他事务的操作数据所干扰，多个并发事务之间要相互隔离。



### 事务处理相关方法

- **隐式提交事务**：正常退出或者运行 DDL、DCL 语句
- **隐式自动回滚**：Connection 遇到一个未处理的 SQLException 异常，系统非正常退出（但如果程序捕获了该异常，则需要在异常处理块中显式地回滚事务）

操作事务的模板:

```java
try {
    connection对象.setAutoCommit(false); // 取消事务自动提交
    DML 操作1
    DML 操作2
    ...
    Connection对象.commit(); // 提交事务
} catch(Exception e) {
    // 处理异常
    Connection对象.rollback(); // 回滚事务
} finally {
    释放资源
}
```

```java
|-- Connection
	void setAutoCommit(boolean autoCommit) ;  设置事务是否自动提交
								              如果设置为false，表示手动提交事务。
	void commit() ();						  手动提交事务
	void rollback() ;						  回滚（出现异常时候，所有已经执行成功的代码需要回												退到事务开始前的状态。）
	Savepoint setSavepoint(String name) 
```



### 案例：转账

需求： 张三给李四转账
设计： 账户表

```sql
-- 账户表
CREATE TABLE account(
   id INT PRIMARY KEY AUTO_INCREMENT,
   accountName VARCHAR(20),
   money DOUBLE
);

-- 转账
UPDATE account SET money=money-1000 WHERE accountName='张三';
UPDATE account SET money=money+1000 WHERE accountName='李四';
```

```java
public class AccountDao {

	// 全局参数
	private Connection con;
	private PreparedStatement pstmt;

	// 1. 转账，没有使用事务
	public void trans1() {

		String sql_zs = 
            "UPDATE account SET money=money-1000 WHERE accountName='张三';";
        
		String sql_ls = 
            "UPDATE account SET money=money+1000 WHERE accountName='李四';";

		try {
			con = JdbcUtil.getConnection(); // 默认开启的隐士事务
			con.setAutoCommit(true);

			/*** 第一次执行SQL ***/
			pstmt = con.prepareStatement(sql_zs);
			pstmt.executeUpdate();

			/*** 第二次执行SQL ***/
			pstmt = con.prepareStatement(sql_ls);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(con, pstmt, null);
		}

	}

	// 2. 转账，使用事务
	public void trans2() {

		String sql_zs = 
            "UPDATE account SET money=money-1000 WHERE accountName='张三';";
		String sql_ls = 
            "UPDATE1 account SET money=money+1000 WHERE accountName='李四';";

		try {
			con = JdbcUtil.getConnection(); // 默认开启的隐士事务
			// 一、设置事务为手动提交
			con.setAutoCommit(false);

			/*** 第一次执行SQL ***/
			pstmt = con.prepareStatement(sql_zs);
			pstmt.executeUpdate();

			/*** 第二次执行SQL ***/
			pstmt = con.prepareStatement(sql_ls);
			pstmt.executeUpdate();

		} catch (Exception e) {
			try {
				// 二、 出现异常，需要回滚事务
				con.rollback();
			} catch (SQLException e1) {
			}
			e.printStackTrace();
		} finally {
			try {
				// 三、所有的操作执行成功, 提交事务
				con.commit();
				JdbcUtil.closeAll(con, pstmt, null);
			} catch (SQLException e) {
                e.printStackTrace();
			}
		}

	}

	// 3. 转账，使用事务， 回滚到指定的代码段
	public void trans3() {
		// 定义个标记
		Savepoint sp = null;
		
		// 第一次转账
		String sql_zs1 = 
            "UPDATE account SET money=money-1000 WHERE accountName='张三';";
		String sql_ls1 = 
            "UPDATE account SET money=money+1000 WHERE accountName='李四';";
		
		// 第二次转账
		String sql_zs2 = 
            "UPDATE account SET money=money-500 WHERE accountName='张三';";
		String sql_ls2 = 
            "UPDATE1 account SET money=money+500 WHERE accountName='李四';";

		try {
			con = JdbcUtil.getConnection(); // 默认开启的隐士事务
			con.setAutoCommit(false);       // 设置事务手动提交

			/*** 第一次转账 ***/
			pstmt = con.prepareStatement(sql_zs1);
			pstmt.executeUpdate();
			pstmt = con.prepareStatement(sql_ls1);
			pstmt.executeUpdate();
			
			// 回滚到这个位置？
			sp = con.setSavepoint(); 
			
			
			/*** 第二次转账 ***/
			pstmt = con.prepareStatement(sql_zs2);
			pstmt.executeUpdate();
			pstmt = con.prepareStatement(sql_ls2);
			pstmt.executeUpdate();
			

		} catch (Exception e) {
			try {
				// 回滚 (回滚到指定的代码段)
				con.rollback(sp);
			} catch (SQLException e1) { 
			}
			e.printStackTrace();
		} finally {
			try {
				// 提交
				con.commit();
			} catch (SQLException e) {
                e.printStackTrace();
			}
			JdbcUtil.closeAll(con, pstmt, null);
		}

	}
}
```



## 大文本, 二进制类型处理

Oracle中大文本数据类型，

+ Clob    长文本类型   （MySQL中不支持，使用的是text）
+ Blob    二进制类型



MySQL数据库，

+ Text    长文本类型
+ Blob    二进制类型



### 设计表

```sql
-- 测试大数据类型
CREATE TABLE test(
     id INT PRIMARY KEY AUTO_INCREMENT,
     content LONGTEXT,
     img LONGBLOB
);
```

### 长文本类型

```java
public class App_text {
	
	// 全局参数
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	

	@Test
	// 1. 保存大文本数据类型   ( 写longtext)
	public void testSaveText() {
		String sql = "insert into test(content) values(?)";
		try {
			// 连接
			con = JdbcUtil.getConnection();
			// pstmt 对象
			pstmt = con.prepareStatement(sql);
			// 设置参数
			// 先获取文件路径
            //src/cn/ixfosa/longtext/tips.txt
			String path = App_text.class.getResource("tips.txt").getPath();
			FileReader reader = new FileReader(new File(path));
			pstmt.setCharacterStream(1, reader);
			
			// 执行sql
			pstmt.executeUpdate();
			
			// 关闭
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(con, pstmt, null);
		}
	}
	
	@Test
	// 2. 读取大文本数据类型   ( 读longtext)
	public void testGetAsText() {
		String sql = "select * from  test;";
		try {
			// 连接
			con = JdbcUtil.getConnection();
			// pstmt 对象
			pstmt = con.prepareStatement(sql);
			// 读取
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 获取长文本数据, 方式1:
				//Reader r = rs.getCharacterStream("content");
				
				// 获取长文本数据, 方式2:
				System.out.print(rs.getString("content"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(con, pstmt, null);
		}
	}
}
```



### 二进制类型

```java
public class App_blob {
	
	// 全局参数
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	

	@Test
	// 1. 二进制数据类型   ( 写longblob)
	public void testSaveText() {
		String sql = "insert into test(img) values(?)";
		try {
			// 连接
			con = JdbcUtil.getConnection();
			// pstmt 对象
			pstmt = con.prepareStatement(sql);
			// 获取图片流   src/cn/ixfosa/longtext/7.jpg
			InputStream in = App_text.class.getResourceAsStream("7.jpg");
			pstmt.setBinaryStream(1, in);
			
			// 执行保存图片
			pstmt.execute();
			
			// 关闭
			in.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(con, pstmt, null);
		}
	}
	
    
	@Test
	// 2. 读取大文本数据类型   ( 读longblob)
	public void testGetAsText() {
		String sql = "select img from  test where id = 2;";
		try {
			// 连接
			con = JdbcUtil.getConnection();
			// pstmt 对象
			pstmt = con.prepareStatement(sql);
			// 读取
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 获取图片流
				InputStream in = rs.getBinaryStream("img");
				// 图片输出流
				FileOutputStream out = new FileOutputStream(new File("c://1.jpg"));
				int len = -1;
				byte b[] = new byte[1024];
				while ((len = in.read(b)) != -1) {
					out.write(b, 0, len);
				}
				// 关闭
				out.close();
				in.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(con, pstmt, null);
		}
	}
}
```



## 获取数据库元数据

+ 在jdbc中获取数据库的定义，例如：数据库、表、列的定义信息。就用到元数据。
+ 在jdbc中可以使用：
  + 数据库元数据
  + 参数元数据
  + 结果集元数

```java
public class App {

	// 1. 数据库元数据
	@Test
	public void testDB() throws Exception {
		// 获取连接
		Connection conn = JdbcUtil.getConnection();
		// 获取数据库元数据  // alt + shift + L  快速获取方法返回值
		DatabaseMetaData metaData = conn.getMetaData();
		
		System.out.println(metaData.getUserName());
		System.out.println(metaData.getURL());
		System.out.println(metaData.getDatabaseProductName());
	}
	
	//2. 参数元数据
	@Test
	public void testParams() throws Exception {
		// 获取连接
		Connection conn = JdbcUtil.getConnection();
		// SQL
		String sql = "select * from dept where deptid=? and deptName=?";
		// Object[] values = {"tom","888"};
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// 参数元数据
		ParameterMetaData p_metaDate = pstmt.getParameterMetaData();
		// 获取参数的个数
		int count = p_metaDate.getParameterCount();
		
		
		// 测试
		System.out.println(count);
	}
	
	// 3. 结果集元数据
	@Test
	public void testRs() throws Exception {
		String sql = "select * from dept ";
		
		// 获取连接
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		// 得到结果集元数据(目标：通过结果集元数据，得到列的名称)
		ResultSetMetaData rs_metaData = rs.getMetaData();
		
		// 迭代每一行结果
		while (rs.next()) {
            
			// 1. 获取列的个数
			int count = rs_metaData.getColumnCount();
            
			// 2. 遍历，获取每一列的列的名称
			for (int i=0; i<count; i++) {
                
				// 得到列的名称
				String columnName = rs_metaData.getColumnName(i + 1);
                
				// 获取每一行的每一列的值
				Object columnValue = rs.getObject(columnName);
                
				// 测试
				System.out.print(columnName + "=" + columnValue + ",");
			}
			System.out.println();
		}
	}
}
```



## 数据库连接池

### 连接池引入

程序中连接如何管理？

1. 连接资源宝贵；需要对连接管理
2. 连接：
   + 操作数据库，创建连接
   + 操作结束，  关闭！
3. 分析：
   	涉及频繁的连接的打开、关闭，影响程序的运行效率！
4. 连接管理：
   	预先创建一组连接，有的时候每次取出一个； 用完后，放回；
5. 学习连接池：
   + 自定义一个连接池
   + 优秀的连接池组件 `DBCP`, `C3P0`等



### 自定义连接池

```java
/**
 * 自定义连接池, 管理连接
 * 代码实现：
	1.  MyPool.java  连接池类，   
	2.  指定全局参数：  初始化数目、最大连接数、当前连接、   连接池集合
	3.  构造函数：循环创建3个连接
	4.  写一个创建连接的方法
	5.  获取连接
	------>  判断： 池中有连接， 直接拿
	------>        池中没有连接，
	------>  	   判断，是否达到最大连接数； 达到，抛出异常；没有达到最大连接数，创建新的连接
	6. 释放连接
	 ------->  连接放回集合中
 *
 */
public class MyPool {

	private int init_count = 3;		// 初始化连接数目
	private int max_count = 6;		// 最大连接数
	private int current_count = 0;  // 记录当前使用连接数
    
	// 连接池 （存放所有的初始化连接）
	private LinkedList<Connection> pool = new LinkedList<Connection>();
	
	
	// 1.构造函数中，初始化连接放入连接池
	public MyPool() {
		// 初始化连接
		for (int i=0; i<init_count; i++){
			// 记录当前连接数目
			current_count++;
			// 创建原始的连接对象
			Connection con = createConnection();
			// 把连接加入连接池
			pool.addLast(con);
		}
	}
	
	// 2.创建一个新的连接的方法
	private Connection createConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 原始的目标对象
			final Connection con = 
                DriverManager.getConnection("jdbc:mysql:///test", "root", "root");
			
			/**********对con对象代理**************/
			// 对con创建其代理对象
			Connection proxy = (Connection) Proxy.newProxyInstance(
				
					con.getClass().getClassLoader(),     // 类加载器
					// con.getClass().getInterfaces(),   // 当目标对象是一个具体的类的时候 
					new Class[]{Connection.class},       // 目标对象实现的接口
					
					new InvocationHandler() {// 当调用con对象方法的时候， 自动触发事务处理器
						@Override
						public Object invoke(
                                        Object proxy, 
                                        Method method, 
                                        Object[] args) throws Throwable {
							// 方法返回值
							Object result = null;
							// 当前执行的方法的方法名
							String methodName = method.getName();
							
							// 判断当执行了close方法的时候，把连接放入连接池
							if ("close".equals(methodName)) {
								System.out.println("begin:当前执行close方法开始！");
								// 连接放入连接池 (判断..)
								pool.addLast(con);
								System.out.println("end: 当前连接已经放入连接池了！");
							} else {
								// 调用目标对象方法
								result = method.invoke(con, args);
							}
							return result;
						}
					}
			);
			return proxy;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	// 3.获取连接
	public Connection getConnection(){
		
		// 3.1 判断连接池中是否有连接, 如果有连接，就直接从连接池取出
		if (pool.size() > 0){
			return pool.removeFirst();
		}
		
		// 3.2 连接池中没有连接： 判断，如果没有达到最大连接数，创建；
		if (current_count < max_count) {
			// 记录当前使用的连接数
			current_count++;
			// 创建连接
			return createConnection();
		}
		
		// 3.3 如果当前已经达到最大连接数，抛出异常
		throw new RuntimeException("当前连接已经达到最大连接数目！");
	}
	
	//4. 释放连接
	public void realeaseConnection(Connection con) {
		// 4.1 判断： 池的数目如果小于初始化连接，就放入池中
		if (pool.size() < init_count){
			pool.addLast(con);
		} else {
			try {
				// 4.2 关闭 
				current_count--;
				con.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	public static void main(String[] args) throws SQLException {
		MyPool pool = new MyPool();
		System.out.println("当前连接: " + pool.current_count);  // 3
		
		// 使用连接
		pool.getConnection();
		pool.getConnection();
		Connection con4 = pool.getConnection();
		Connection con3 = pool.getConnection();
		Connection con2 = pool.getConnection();
		Connection con1 = pool.getConnection();
		
		// 释放连接, 连接放回连接池
		// pool.realeaseConnection(con1);
		/*
		 * 希望：当关闭连接的时候，要把连接放入连接池！【当调用Connection接口的close方法时候，希望		   * 触发pool.addLast(con);操作】把连接放入连接池
		 * 解决1：实现Connection接口，重写close方法
		 * 解决2：动态代理
		 */
		con1.close();
		
		// 再获取
		pool.getConnection();
		
		System.out.println("连接池：" + pool.pool.size());      // 0
		System.out.println("当前连接: " + pool.current_count);  // 3
	}
}
```







### DBCP连接池

+ DBCP 是 Apache 软件基金组织下的开源连接池实现
+ Tomcat 的连接池是采用该连接池来实现的。该数据库连接池既可以与应用服务器整合使用，也可由应用程序独立使用。

+ 相关 jar 文件：
  + Commons-dbcp.jar：连接池的实现
  + Commons-pool.jar：连接池实现的依赖库
+ 核心类：`BasicDataSource`



src/cn/ixfosa/dbcp/db.properties

```java
url=jdbc:mysql:///jdbc_demo
driverClassName=com.mysql.jdbc.Driver
username=root
password=root
initialSize=3
maxActive=6
maxIdle=3000
```

```java
package cn.ixfosa.dbcp;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

public class App_DBCP {

	// 1. 硬编码方式实现连接池
	@Test
	public void testDbcp() throws Exception {
		// DBCP连接池核心类
		BasicDataSource dataSouce = new BasicDataSource();
		// 连接池参数配置：初始化连接数、最大连接数 / 连接字符串、驱动、用户、密码
		dataSouce.setUrl("jdbc:mysql:///jdbc_demo");			// 数据库连接字符串
		dataSouce.setDriverClassName("com.mysql.jdbc.Driver");  // 数据库驱动
		dataSouce.setUsername("root");							// 数据库连接用户
		dataSouce.setPassword("root"); 							// 数据库连接密码
		dataSouce.setInitialSize(3);  // 初始化连接
		dataSouce.setMaxActive(6);	  // 最大连接
		dataSouce.setMaxIdle(3000);   // 最大空闲时间
		
		// 获取连接
		Connection con = dataSouce.getConnection();
		con.prepareStatement("delete from admin where id=3").executeUpdate();
		// 关闭
		con.close();
	}
	
    
	@Test
	// 2. 【推荐】配置方式实现连接池  ,  便于维护
	public void testProp() throws Exception {
		// 加载prop配置文件
		Properties prop = new Properties();
		// 获取文件流   src/cn/ixfosa/dbcp/db.properties
		InputStream inStream = App_DBCP.class.getResourceAsStream("db.properties");
		// 加载属性配置文件
		prop.load(inStream);
		// 根据prop配置，直接创建数据源对象
		DataSource dataSouce = BasicDataSourceFactory.createDataSource(prop);
		
		// 获取连接
		Connection con = dataSouce.getConnection();
		con.prepareStatement("delete from admin where id=4").executeUpdate();
		// 关闭
		con.close();
	}
}
```



### C3P0连接池

+ C3P0连接池，核心类：		`CombopooledDataSource`
+ 相关 jar 文件：c3p0-0.9.1.2.jar
+ 使用连接池，创建连接
  + 硬编码方式
  + 配置方式(xml)



```xml
<!-- src/c3p0-config.xml -->
<c3p0-config>
	<default-config>
		<property name="jdbcUrl">jdbc:mysql://localhost:3306/jdbc_demo</property>
		<property name="driverClass">com.mysql.jdbc.Driver</property>
		<property name="user">root</property>
		<property name="password">root</property>
		<property name="initialPoolSize">3</property>
		<property name="maxPoolSize">6</property>
		<property name="maxIdleTime">1000</property>
	</default-config>

	<named-config name="oracle_config">
		<property name="jdbcUrl">jdbc:mysql://localhost:3306/jdbc_demo</property>
		<property name="driverClass">com.mysql.jdbc.Driver</property>
		<property name="user">root</property>
		<property name="password">root</property>
		<property name="initialPoolSize">3</property>
		<property name="maxPoolSize">6</property>
		<property name="maxIdleTime">1000</property>
	</named-config>
</c3p0-config>
```

```java
package cn.ixfosa.c3p0;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class App {

	@Test
	// 1.硬编码方式，使用C3P0连接池管理连接
	public void testCode() throws Exception {
		// 创建连接池核心工具类
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		// 设置连接参数：url、驱动、用户密码、初始连接数、最大连接数
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/jdbc_demo");
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setUser("root");
		dataSource.setPassword("root");
		dataSource.setInitialPoolSize(3);
		dataSource.setMaxPoolSize(6);
		dataSource.setMaxIdleTime(1000);
		
		// ---> 从连接池对象中，获取连接对象
		Connection con = dataSource.getConnection();
		// 执行更新
		con.prepareStatement("delete from admin where id=7").executeUpdate();
		// 关闭
		con.close();
	}
	
	@Test
	// 2.XML配置方式，使用C3P0连接池管理连接
	public void testXML() throws Exception {
		// 创建c3p0连接池核心工具类
		// 自动加载src下c3p0的配置文件【c3p0-config.xml】
		ComboPooledDataSource dataSource = new ComboPooledDataSource();// 使用默认的配置
		PreparedStatement pstmt = null;
		
		// 获取连接
		Connection con = dataSource.getConnection();
		for (int i = 1; i < 11; i++){
			String sql = "insert into employee(empName, dept_id) values(?, ?)";
			// 执行更新
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "Rose" + i);
			pstmt.setInt(2, 1);
			pstmt.executeUpdate();
		}
		pstmt.close();
		// 关闭
		con.close();
	}
}
```



### Druid 连接池

- 需要的 jar 包：druid.jar、commons-logging.jar
- Druid 的分页支持：PagerUtil

常用属性配置

```properties
driverClassName=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/jdbcdemo?useSSL=false&rewriteBatchedStatements=true&serverTimezone=Asia/Shanghai
username=root
password=

# 最大连接数
maxActive=10
```

核心对象

```java
// 通过连接池工厂创建连接池对象
// 配置文件中的 key 名要对应 DruidDataSourceFactory 类中字符串常量
DataSource ds = DruidDataSourceFactory.createDataSource(Properties properties);
```

