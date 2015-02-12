package dbconsole;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {

	String driver;
	String server, dbname, url, user, password;
	java.sql.Connection con;
	java.sql.Statement stmt;
	ResultSet rs;

	public MySQL() {
		this.driver = "org.gjt.mm.mysql.Driver";
		this.server = "j11000.sangi01.net";
		this.dbname = "50213046";
		this.url = "jdbc:mysql://" + server + "/" + dbname + "?useUnicode=true&characterEncoding=UTF-8";
		this.user = "50213046";
		this.password = "50213046";
		
		try{
			this.con = DriverManager.getConnection(url, user, password);
			this.stmt = con.createStatement();
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public void close(){
		try{
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public ResultSet selectAll(){
		// 勝ち点，得失点差，得点の優先順で値の大きい順に並べる
		String sql = "SELECT * FROM Jleague ORDER BY WinPoint DESC, ScoreDiff DESC, Score DESC";
		rs = null;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}    
}
