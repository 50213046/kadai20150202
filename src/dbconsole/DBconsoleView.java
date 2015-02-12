package dbconsole;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


public class DBconsoleView extends Frame implements ActionListener,WindowListener{
	
	int maxTeam = 50, dbteam;
	String TeamName[] = new String[maxTeam];
	int WinPoint[] = new int[maxTeam];
	int ScoreDiff[] = new int[maxTeam];
	int Score[] = new int [maxTeam];
	
	private Button button1 = new Button("D/B Connect");
	private Button button2 = new Button("DrawGraph");

	public DBconsoleView(DBconsoleController controller) {
		// TODO Auto-generated constructor stub
		addWindowListener(this);
		setLayout(new FlowLayout(FlowLayout.CENTER));

		add(button1);
		button1.addActionListener(this);
		add(button2);
		button2.addActionListener(this);
		button2.setVisible(false);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.exit(0);
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		ResultSet rs;
		
		// TODO Auto-generated method stub
		if(e.getSource() == button1){
			dbteam = 0;
			MySQL mysql = new MySQL();
			rs = mysql.selectAll();
			try {
				while(rs.next()){
				    TeamName[dbteam] = rs.getString("TEAM");
					WinPoint[dbteam] = rs.getInt("WINPOINT");
					ScoreDiff[dbteam] = rs.getInt("SCOREDIFF");
					Score[dbteam]    = rs.getInt("SCORE");
					dbteam++;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			mysql.close();
			button2.setVisible(true);
            this.setVisible(true);
	
		}
		else if(e.getSource() == button2){
			setTitle("J-League");
			DefaultCategoryDataset data = new DefaultCategoryDataset();
			JFreeChart chart = ChartFactory.createLineChart("J-League 2014", "Team", "Point", data, PlotOrientation.VERTICAL, true, true, true);
		     CategoryPlot plot = chart.getCategoryPlot();
             CategoryAxis axis = plot.getDomainAxis();
             axis.setLowerMargin(0.03);
             axis.setUpperMargin(0.03);
             axis.setCategoryLabelPositions(CategoryLabelPositions
             .createUpRotationLabelPositions(Math.PI / 6.0));

			for(int i = 0; i < dbteam; i++){

				data.addValue(WinPoint[i], "WinPoint", TeamName[i]);
				data.addValue(ScoreDiff[i], "ScoreDiff", TeamName[i]);
				data.addValue(Score[i], "Score", TeamName[i]);
			}

			ChartPanel cpanel = new ChartPanel(chart);
			add(cpanel, BorderLayout.CENTER);
			this.setVisible(true);
		}

		
	}
	
	
	
	

}
