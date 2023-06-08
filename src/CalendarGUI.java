import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class CalendarGUI extends JFrame{

	private DefaultListModel<ExamDate> model;
	private ArrayList<ExamDate> exams =new ArrayList<>();
	
	public CalendarGUI(ExamScheduler ES) {
		
		this.exams = ES.getDates();
		
		
		//panel.add(new JScrollPane());
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		JList<ExamDate> list = new JList<ExamDate>();
		list.setBounds(0, 0, 484, 461);
		getContentPane().add(list);
		list.setFont(list.getFont().deriveFont(18.0f));
		list.setFixedCellHeight(44);
		
		/*DefaultListModel model = new DefaultListModel();
		model.addElement("sifalera");
		model.addElement("sam");
		list.setModel(model);*/

	
		this.model = new DefaultListModel<>();
		
		list.setModel(model);
		for(ExamDate d: exams) {
			String e1 = d.day;
			
			
			
			model.addElement(d);
		}
		
		JScrollPane scrollPane = new JScrollPane(list);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		revalidate();
		list.setVisible(true);
		
		
		ImageIcon logo= new ImageIcon("logo.png");
		this.setIconImage(logo.getImage());
		
		this.setSize(500,500);
		this.setTitle("Calendar");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		this.setVisible(true);
	
	}
	
}




