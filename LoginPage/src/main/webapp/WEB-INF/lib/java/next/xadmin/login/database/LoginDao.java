package next.xadmin.login.database;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.jdi.connect.spi.Connection;

import next.xadmin.login.bean.LoginBean;

public class LoginDao {
	
	private String dbUrl = "jdbc:mysql://localhost:8083/employeedatabase";
	private String dbUname = "root";
	private String dbPassword = "Snigdha2396!";
	private String dbDriver = "com.mysql.cj.jdbc.Driver";
	
	public void loadDriver(String dbDriver)
	{
		try
		{
			Class.forName(dbDriver);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public Connection getConnection()
	{
	Connection con = null;
	try {
	con = (Connection) DriverManager.getConnection(dbUrl, dbUname, dbPassword);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return con;
	}
	
	public boolean validate(LoginBean loginBean) {
		loadDriver(dbDriver);
		Connection con = getConnection();
		boolean status = false;
		String sql = "(select * from `employeedatabase`.`employee` where username = ? and password = ?)";
		
		PreparedStatement ps;
		try {
			ps =((java.sql.Connection) con).prepareStatement(sql);
			ps.setString(1,  loginBean.getUsername());
			ps.setString(1, loginBean.getPassword());
			
			ResultSet rs = ps.executeQuery();
			status = rs.next();
			} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return status;
	}

}
