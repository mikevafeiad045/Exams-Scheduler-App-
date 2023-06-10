import java.awt.BorderLayout;
import java.util.ArrayList;


import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;



public class CalendarGUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultListModel<String> model;
	private ArrayList<ExamDate> exams =new ArrayList<>();
	private JList<String> list;
	private ExamScheduler ES;
	
	public CalendarGUI(/*ExamScheduler ES*/) {
		ES = ExamScheduler.getInstance();
		
		this.exams = ES.getDates();
		
		
		//panel.add(new JScrollPane());
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		list = new JList<String>();
		list.setBounds(0, 0, 484, 461);
		getContentPane().add(list);
		list.setFont(list.getFont().deriveFont(18.0f));
		list.setFixedCellHeight(44);
		
		JScrollPane scrollPane = new JScrollPane(list);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		revalidate();
		list.setVisible(true);
		
		this.model = new DefaultListModel<>();
		
		list.setModel(model);
		for(ExamDate d: exams) {
			String examDayElem = d.day; 
			Course[] coursesZone= d.getZone();
			String courseNameElem;
			ArrayList<String> profs = new ArrayList<>();
			ArrayList<Room> rooms = new ArrayList<>();
			String combinedElements;
			int i;
			for(i=0;i<6;i++) {
				if(coursesZone[i]!=null) {
					courseNameElem = coursesZone[i].getCourseName();
					profs = coursesZone[i].getProfessorsList();
					rooms = coursesZone[i].getSelectedRoomsList();
					StringBuilder sb = new StringBuilder();
					for(int j=0; j<profs.size();j++) {
						sb.append(profs.get(j));
						if(j<profs.size()-1) {
							sb.append(", ");
							}
						}
					StringBuilder sb2 = new StringBuilder();
					for(int j=0; j<rooms.size();j++) {
						sb2.append(rooms.get(j).getRoomName());
						if(j<rooms.size()-1) {
							sb2.append(", ");
							
							}
						}
					combinedElements = examDayElem + courseNameElem + sb.toString() + sb2.toString();
					model.addElement(combinedElements);
					}
			}
			
		}
		
		addWindowListener(new ProgramTerminated());
		
		ImageIcon logo = new ImageIcon("logo.png");
		this.setIconImage(logo.getImage());
		
		this.setSize(500,500);
		this.setTitle("Calendar");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		this.setVisible(true);
	
	}
	
}




