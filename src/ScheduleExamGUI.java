import java.awt.Color;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.ArrayList;

import java.util.HashMap;


import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import com.toedter.calendar.JDateChooser;
import javax.swing.UIManager;
//import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.JTextField;



public class ScheduleExamGUI extends JFrame implements ActionListener, MouseListener {
	
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
	private String[] listValues; 
	private int roomsListSize;
	private ArrayList<Room> rooms;
	private DefaultListModel<String> listModel; 
	//private ArrayList<String> tempRooms;
	private Course selectedCourse;
	private HashMap<String, Room> roomsMap;
	private HashMap<String, Integer> hoursMap;
	private String roomName;
	
	
	
	public ScheduleExamGUI(Course selectedCourse) {
		//---------------------/!\ Auto mporei na ginei methodos
		
		ES = ExamScheduler.getInstance();
		
		this.selectedCourse=selectedCourse;
		rooms = ES.getRoomList();
	
		roomsListSize = rooms.size();
		
		listValues = new String[roomsListSize];
		
		int i=0;
		for(Room r : rooms) {
			roomName = r.getRoomName();
			listValues[i] = roomName;
			i++;
		}
		//----------------------/!\
		
		hoursMap = this.createHoursMap();
	
		SpringLayout springLayout = new SpringLayout();
		this.getContentPane().setLayout(springLayout);
		
		panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 0, SpringLayout.NORTH, this.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST, this.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 400, SpringLayout.NORTH, this.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 0, SpringLayout.EAST, this.getContentPane());
		this.getContentPane().add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		courseLabel = new JLabel("Schedule selected course");
		sl_panel.putConstraint(SpringLayout.NORTH, courseLabel, 10, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, courseLabel, 102, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, courseLabel, -97, SpringLayout.EAST, panel);
		courseLabel.setFont(new Font("Arial", Font.PLAIN, 24));
		panel.add(courseLabel);
		
		JLabel selectLabel = new JLabel("Select Date:");
		selectLabel.setFont(new Font("Arial", Font.PLAIN, 17));
		panel.add(selectLabel);
		
		dateChooser = new JDateChooser();
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
		
		searchButton.addActionListener(this);
		
		hoursBox = new JComboBox<String>();
		sl_panel.putConstraint(SpringLayout.SOUTH, dateChooser, -34, SpringLayout.NORTH, hoursBox);
		sl_panel.putConstraint(SpringLayout.SOUTH, searchButton, -25, SpringLayout.NORTH, hoursBox);
		sl_panel.putConstraint(SpringLayout.NORTH, hoursBox, 131, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, hoursBox, -85, SpringLayout.EAST, panel);
		hoursBox.setModel(new DefaultComboBoxModel<String>(def));
	//	hoursBox.setRenderer(new DisabledItemsComboBoxRenderer());
		hoursBox.setFont(new Font("Arial", Font.PLAIN, 15));
		panel.add(hoursBox);
		
		suggestedRoomsList = new JList<String>();
		sl_panel.putConstraint(SpringLayout.SOUTH, suggestedRoomsList, -61, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, suggestedRoomsList, -97, SpringLayout.EAST, panel);
		
		
		listModel = new DefaultListModel<String>();
		//{
			//--------/!\
			//String[] listValues = new String[] {"1", "2", "3", "4", "5"};
			//public int getSize() {
				//return listValues.length;
				
			//}
			//public String getElementAt(int index) {
			//	return listValues[index];
				
			//}
		//};
		
		

		suggestedRoomsList.setFont(new Font("Arial", Font.PLAIN, 13));
		suggestedRoomsList.setBackground(UIManager.getColor("InternalFrame.resizeIconHighlight"));
		suggestedRoomsList.setVisible(true);
		panel.add(suggestedRoomsList);
		suggestedRoomsList.setModel(listModel);
		
		for(String s : listValues) {
			listModel.addElement(s);
		}
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
		
		confirmButton = new JButton("Confirm");
		confirmButton.setFont(new Font("Arial", Font.PLAIN, 20));
		springLayout.putConstraint(SpringLayout.NORTH, confirmButton, 14, SpringLayout.SOUTH, panel);
		confirmButton.addActionListener(this);
		
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
		springLayout.putConstraint(SpringLayout.WEST, confirmButton, 184, SpringLayout.WEST, this.getContentPane());
		this.getContentPane().add(confirmButton);
		
		suggestedRoomsList.addMouseListener(this);
		
		addWindowListener(new ProgramTerminated());
		
		this.setAlwaysOnTop(true);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png"));
		this.setTitle("Schedule The Exam");
		this.setSize(500,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		String selectedDate = dateChooser.getDateFormatString();
		String convertedDate = ES.ConvertDate(selectedDate);
		Course[] coursesArray= new Course[6];
		coursesArray = ES.findDate(convertedDate);
		
		
		if(e.getSource() == searchButton) {
			int i;
			for(i=0; i<coursesArray.length; i++) {
				hoursBox.remove(i);
			}
			
			
			for(i=0; i<coursesArray.length; i++) {
				if(coursesArray[i] == null) {
					switch(i) {
						case 0:
							hoursBox.addItem(def[0]);
							break;
						case 1:
							hoursBox.addItem(def[1]);
							break;
						case 2:
							hoursBox.addItem(def[2]);
							break;
						case 3:
							hoursBox.addItem(def[3]);
							break;
						case 4:
							hoursBox.addItem(def[4]);
							break;
						case 5:
							hoursBox.addItem(def[5]);
							break;
					}
					
						
					
				
			}
		}
		}else{
			//add selected course object to selected date array depending on the exam zone selected
			
			//int index = hoursBox.getSelectedIndex();
			
			
			String convertedDate1 = ES.ConvertDate(selectedDate);
			ExamDate date1 = new ExamDate(convertedDate1);
			
			
			hoursMap = this.createHoursMap();
			String tempHour = (String) hoursBox.getSelectedItem();
			int index = hoursMap.get(tempHour);	
			
			date1.addCourse(selectedCourse, index);
			
			int option = JOptionPane.showConfirmDialog(null, "Do you want to add another course?", "Confirmation", JOptionPane.YES_NO_OPTION);

	        if (option == JOptionPane.YES_OPTION) {
	            
	        	SelectCourseGUI.getInstance().setVisible(true);;
	        	
	           // System.out.println("Adding a course...");
	            
	        } else {
	        	
	           // System.out.println("Exiting...");
	            System.exit(0);
	        }
			
			LoginGUI.getInstance();
		
			
		}
			
		
		}
		
		public void mouseClicked(MouseEvent e) {
			//boolean isSelected = myList.isSelectedIndex(0);
			
			int[] indices = suggestedRoomsList.getSelectedIndices();
			
			//tempRooms = new ArrayList<String>();
			
			for(int i : indices) {
				String selectedRoomStr =  listModel.getElementAt(i);
				
				roomsMap = this.createRoomsMap();
				Room selectedRoomObj=null;
				
				try {
					selectedRoomObj =  roomsMap.get(selectedRoomStr);
					
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				
				int tempRoomType = selectedRoomObj.getType();
				int roomCapacity = -1;
				
				if(tempRoomType == 0) {
					//1h periptwsh: Auditorium
					roomCapacity = ES.getCapacityAud();
				
				}else {
					//2h periptwsh: Amphitheatre
					roomCapacity = ES.getCapacityAmph();
				
				}
				
				int remStud = ES.calcRemainingStudents(selectedCourse.getNumberOfStudents(), roomCapacity);
				String remStudStr = String.valueOf(remStud);
				
				remainingStudents.setText(remStudStr);
			
			}
		
		
		
		}

	
		public void mousePressed(MouseEvent e) {
		}
		public void mouseReleased(MouseEvent e) {	
		}
		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent e) {
		}
		
		
		private HashMap<String, Integer> createHoursMap() {
			
		        HashMap<String, Integer> map = new HashMap<>();
		        int increment=0;
		        for (String s : def) {
		            map.put(s, increment);
		            increment++;
		        }
		        return map;
		    }
			
		
		private HashMap<String, Room> createRoomsMap(){
			HashMap<String, Room> map = new HashMap<>();
	        for (Room r : rooms) {
	            map.put(r.getRoomName(), r);
	        }
	        return map;
		}
		
		/*
		static class DisabledItemsComboBoxRenderer extends BasicComboBoxRenderer {
	        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	            Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

	            // Check if the item is disabled
	            if (!list.isEnabled() || !list.isEnabledAt(index)) {
	                // Set the item to be disabled (greyed out)
	                component.setEnabled(false);
	            }

	            return component;
	        }
	    }*/
		
}
