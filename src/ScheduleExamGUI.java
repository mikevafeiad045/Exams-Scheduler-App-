import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import com.toedter.calendar.JDateChooser;
import javax.swing.UIManager;
import javax.swing.JTextField;



public class ScheduleExamGUI extends JFrame implements ActionListener {
	
	private JPanel panel;
	private JLabel courseLabel;
	private JButton searchButton;
	private JComboBox<String> hoursBox;
	private JList<String> suggestedRoomsList;
	private JButton confirmButton;
	private JLabel remainingStudents;
	private JDateChooser dateChooser;
	private ExamScheduler ES;
	private String def[] =new String[] {"9:00-11:00", "11:00-13:00", "13:00-15:00", "15:00-17:00", "17:00-19:00", "19:00-21:00"};
	private JTextField remStudField;
	
	
	public ScheduleExamGUI(ExamScheduler ES, Course selectedCourse) {
		
		ArrayList<Room> rooms = ES.getRoomList();
		ArrayList<String> roomCodes = null;
		
		for(Room r : rooms) {
			String roomName = r.getRoomName();
			roomCodes.add(roomName);
		
		}
		
		this.setAlwaysOnTop(true);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png"));
		this.setTitle("Schedule The Exam");
		this.setSize(500,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		this.getContentPane().setLayout(springLayout);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 0, SpringLayout.NORTH, this.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST, this.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 400, SpringLayout.NORTH, this.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 0, SpringLayout.EAST, this.getContentPane());
		this.getContentPane().add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JLabel courseLabel = new JLabel("Schedule selected course");
		sl_panel.putConstraint(SpringLayout.NORTH, courseLabel, 10, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, courseLabel, 102, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, courseLabel, -97, SpringLayout.EAST, panel);
		courseLabel.setFont(new Font("Arial", Font.PLAIN, 24));
		panel.add(courseLabel);
		
		JLabel selectLabel = new JLabel("Select Date:");
		selectLabel.setFont(new Font("Arial", Font.PLAIN, 17));
		panel.add(selectLabel);
		
		JDateChooser dateChooser = new JDateChooser();
		sl_panel.putConstraint(SpringLayout.NORTH, dateChooser, 35, SpringLayout.SOUTH, courseLabel);
		sl_panel.putConstraint(SpringLayout.EAST, selectLabel, -21, SpringLayout.WEST, dateChooser);
		sl_panel.putConstraint(SpringLayout.NORTH, selectLabel, 0, SpringLayout.NORTH, dateChooser);
		sl_panel.putConstraint(SpringLayout.WEST, dateChooser, 137, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, dateChooser, -220, SpringLayout.EAST, panel);
		panel.add(dateChooser);
		//dateChooser.addActionListener
		
		JButton searchButton = new JButton("Search");
		sl_panel.putConstraint(SpringLayout.NORTH, searchButton, 62, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, searchButton, 34, SpringLayout.EAST, dateChooser);
		sl_panel.putConstraint(SpringLayout.EAST, searchButton, -85, SpringLayout.EAST, panel);
		searchButton.setFont(new Font("Arial", Font.PLAIN, 15));
		panel.add(searchButton);
		
		JComboBox hoursBox = new JComboBox();
		sl_panel.putConstraint(SpringLayout.SOUTH, dateChooser, -34, SpringLayout.NORTH, hoursBox);
		sl_panel.putConstraint(SpringLayout.SOUTH, searchButton, -25, SpringLayout.NORTH, hoursBox);
		sl_panel.putConstraint(SpringLayout.NORTH, hoursBox, 131, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, hoursBox, -85, SpringLayout.EAST, panel);
		hoursBox.setModel(new DefaultComboBoxModel(def));
		hoursBox.setFont(new Font("Arial", Font.PLAIN, 15));
		panel.add(hoursBox);
		
		JList<String> suggestedRoomsList = new JList<String>();
		sl_panel.putConstraint(SpringLayout.SOUTH, suggestedRoomsList, -61, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, suggestedRoomsList, -97, SpringLayout.EAST, panel);
		suggestedRoomsList.setModel(new AbstractListModel<String>() {
			//--------/!\
			String[] values = new String[] {"1", "2", "3", "4", "5"};
			public int getSize() {
				return values.length;
				
			}
			public String getElementAt(int index) {
				return values[index];
				
			}
			/* !!!!!!!!!!!!!!!!
			public int getSize() {
				//return values.length;
				return roomCodes.size();
			}
			public String getElementAt(int index) {
				//return values[index];
				return roomCodes.get(index);
			}*/
		});
		suggestedRoomsList.setFont(new Font("Arial", Font.PLAIN, 13));
		suggestedRoomsList.setBackground(UIManager.getColor("InternalFrame.resizeIconHighlight"));
		panel.add(suggestedRoomsList);
		
		//-----------------------------------------------------------/!\
		JLabel remainingStudents = new JLabel("Remaining students: ");
		sl_panel.putConstraint(SpringLayout.NORTH, remainingStudents, 14, SpringLayout.SOUTH, suggestedRoomsList);
		sl_panel.putConstraint(SpringLayout.WEST, remainingStudents, 86, SpringLayout.WEST, panel);
		remainingStudents.setForeground(Color.BLACK);
		remainingStudents.setFont(new Font("Arial", Font.PLAIN, 13));
		panel.add(remainingStudents);
		//---------------------------------------------------------------------------
		
		JLabel hoursLabel = new JLabel("Available Hours:");
		sl_panel.putConstraint(SpringLayout.EAST, remainingStudents, 0, SpringLayout.EAST, hoursLabel);
		sl_panel.putConstraint(SpringLayout.WEST, hoursLabel, 91, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.WEST, suggestedRoomsList, 0, SpringLayout.WEST, hoursLabel);
		sl_panel.putConstraint(SpringLayout.WEST, hoursBox, 49, SpringLayout.EAST, hoursLabel);
		sl_panel.putConstraint(SpringLayout.NORTH, hoursLabel, 1, SpringLayout.NORTH, hoursBox);
		hoursLabel.setFont(new Font("Arial", Font.PLAIN, 17));
		panel.add(hoursLabel);
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 20));
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 14, SpringLayout.SOUTH, panel);
		
		JLabel suggestedRoomsLabel = new JLabel("Suggested Rooms:");
		sl_panel.putConstraint(SpringLayout.NORTH, suggestedRoomsList, 9, SpringLayout.SOUTH, suggestedRoomsLabel);
		sl_panel.putConstraint(SpringLayout.WEST, suggestedRoomsLabel, 91, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, suggestedRoomsLabel, 6, SpringLayout.SOUTH, hoursLabel);
		suggestedRoomsLabel.setFont(new Font("Arial", Font.PLAIN, 17));
		panel.add(suggestedRoomsLabel);
		
		remStudField = new JTextField();
		sl_panel.putConstraint(SpringLayout.SOUTH, remStudField, 20, SpringLayout.NORTH, remainingStudents);
		sl_panel.putConstraint(SpringLayout.EAST, remStudField, -235, SpringLayout.EAST, panel);
		remStudField.setForeground(new Color(255, 255, 255));
		remStudField.setEditable(false);
		remStudField.setFont(new Font("Arial", Font.PLAIN, 12));
		remStudField.setBackground(new Color(225, 255, 226));
		sl_panel.putConstraint(SpringLayout.NORTH, remStudField, -1, SpringLayout.NORTH, remainingStudents);
		sl_panel.putConstraint(SpringLayout.WEST, remStudField, 6, SpringLayout.EAST, remainingStudents);
		panel.add(remStudField);
		remStudField.setColumns(10);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 184, SpringLayout.WEST, this.getContentPane());
		this.getContentPane().add(btnNewButton);
		
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		String selectedDate = dateChooser.getDateFormatString();
		String convertedDate = ES.ConvertDate(selectedDate);
		Course[] coursesArray= new Course[6];
		coursesArray = ES.findDate(convertedDate);
		int i=-1;
		
		if(e.getSource() == searchButton) {
			
			
			for(i=0; i<=coursesArray.length; i++) {
				if(!(coursesArray[i] instanceof Course)) {
					switch(i) {
						case 0:
							hoursBox.addItem("9:00-11:00");
							break;
						case 1:
							hoursBox.addItem("11:00-13:00");
							break;
						case 2:
							hoursBox.addItem("13:00-15:00");
							break;
						case 3:
							hoursBox.addItem("15:00-17:00");
							break;
						case 4:
							hoursBox.addItem("17:00-19:00");
							break;
						case 5:
							hoursBox.addItem("19:00-21:00");
							break;
					}
					
				
					
				
			}
		}
		}else{
			//add selected course object to selected date array depending on the exam zone selected
			
			
			
		
			
		
	}
	/*	 private HashMap<int, String> createHoursMap() {
		        HashMap<String, Course> map = new HashMap<>();
		        for (Course c : courses) {
		            map.put(c.getCourseName(), c);
		        }
		        return map;
		    }*/

}
}
