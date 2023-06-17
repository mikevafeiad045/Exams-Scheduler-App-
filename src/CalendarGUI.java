import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * GUI class for the resulting exam schedule window showing info about all scheduled exams.
 * 
 * @version 17 Jun 2023
 * @author Dimitra Papadopoulou, Michalis Vafeiadis
 */
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

		panel = new JPanel();
		panel.setLayout(new BorderLayout());


		list = new JList<String>();
		list.setFont(list.getFont().deriveFont(18.0f));
		list.setFixedCellHeight(44);
		list.setVisible(true);


		model = new DefaultListModel<>();
		list.setModel(model);
		list.setCellRenderer(new BorderedListCellRenderer());

		JScrollPane scrollPane = new JScrollPane(list);

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
				
				if(coursesZone[i]!=null) {//if an exam is scheduled at that time
					courseNameElem = coursesZone[i].getCourseName();
					profs = coursesZone[i].getProfessorsList();
					rooms = coursesZone[i].getSelectedRoomsList();
					hourElem = this.findHoursFromIndex(i);
					
					//Appending all professor names in a single string
					StringBuilder sb = new StringBuilder();
					for(int j=0; j<profs.size();j++) {
						sb.append(profs.get(j));
						if(j<profs.size()-1) {
							sb.append(", ");
						}
					}
					
					//Appending all room codenames in a single string
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
	
	/**
	 * Reverse HashMap search for the string depending on an index (e.g. value index 0 in the availability array will return key 
	 * "9:00-11:00")
	 * 
	 * @param index
	 * @return key
	 */
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

	/**
	 * Subclass for implementing a Jlist border around each cell for better view of the final schedule
	 * 
	 * @return component
	 */
	private class BorderedListCellRenderer extends DefaultListCellRenderer {

		private static final int BORDER_PADDING = 1;

		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createLineBorder(Color.BLACK),
					BorderFactory.createEmptyBorder(BORDER_PADDING, 0, BORDER_PADDING, 0)
					));
			return component;
		}
	}

}




