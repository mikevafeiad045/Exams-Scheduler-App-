import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;



public class CalendarGUI extends JFrame{
	
	private DefaultListModel<String> model;
	private ArrayList<ExamDate> exams =new ArrayList<>();
	private JList<String> list;
	private ExamScheduler ES;
	private HashMap<String, Integer> hoursMap;
	private JPanel panel;
	
	public CalendarGUI() {
		ES = ExamScheduler.getInstance();
		
		this.exams = ES.getDates();
		this.hoursMap = ES.getHoursMap();
		
		//panel.add(new JScrollPane());
		
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		
		list = new JList<String>();
		//list.setBounds(0, 0, 484, 461);
		list.setFont(list.getFont().deriveFont(18.0f));
		list.setFixedCellHeight(44);
		list.setVisible(true);
		
		//String[] test = new String[]{"1","2","3"}; 
	
 		model = new DefaultListModel<>();
 		/*for(String t : test) {
 			model.addElement(t);
 		}*/
		list.setModel(model);

		JScrollPane scrollPane = new JScrollPane(list);
		//scrollPane.setViewportView(list);
		
		panel.add(scrollPane, BorderLayout.CENTER);
		
		getContentPane().add(panel, BorderLayout.CENTER);
		
		revalidate();
		
		for(ExamDate d: exams) {
			String examDayElem = d.day; 
			Course[] coursesZone= d.getZone();
			String courseNameElem;
			ArrayList<String> profs = new ArrayList<>();
			ArrayList<Room> rooms = new ArrayList<>();
			String combinedElements;
			String hourElem;
			int i;
			for(i=0;i<6;i++) {
				if(coursesZone[i]!=null) {
					courseNameElem = coursesZone[i].getCourseName();
					profs = coursesZone[i].getProfessorsList();
					rooms = coursesZone[i].getSelectedRoomsList();
					
					hourElem = this.findHoursFromIndex(i);
					
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
					combinedElements = examDayElem + "   " +hourElem + "   " +courseNameElem + "   " +sb.toString() + "   " +sb2.toString();
					model.addElement(combinedElements);
					}
			}
			
		}
		
		ImageIcon logo = new ImageIcon("logo.png");
		this.setIconImage(logo.getImage());
		
		this.setSize(700,500);
		this.setTitle("Calendar");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	
	}
	
	private String findHoursFromIndex(int index) {
		int targetValue = index;
        String key = null;

        for (HashMap.Entry<String, Integer> entry : hoursMap.entrySet()) {
            if (entry.getValue() == targetValue) {
                key = entry.getKey();
                break;
            }
        }

        return key;
	}
	
}




