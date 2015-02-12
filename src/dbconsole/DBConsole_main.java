package dbconsole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConsole_main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBconsoleController controller = new DBconsoleController();
		DBconsoleView frame = new DBconsoleView(controller);
		frame.setBounds(5,5,800,500);
		frame.setVisible(true);
		
	}

}
