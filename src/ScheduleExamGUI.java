import java.awt.Color;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;


public class ScheduleExamGUI extends JFrame implements ActionListener, MouseListener {
	
	private JPanel panel;
	private JLabel courseLabel;
	private JButton searchButton;
	private JComboBox<String> hoursBox;
	private JButton confirmButton;
	private JLabel remainingStudents;
	private JDateChooser dateChooser;
	private ExamScheduler ES;
	private String examHours[];
	private JTextField remStudField;
	private String[] listValues; 
	private int roomsListSize;
	private ArrayList<Room> roomsList;
	private DefaultListModel<String> listModel; 
	private Course selectedCourse;
	private HashMap<String, Room> roomsMap;
	private HashMap<String, Integer> hoursMap;
	private String roomName;
	private JScrollPane scrollPane;
	private JList<String> suggestedRoomsList;
	private String remStudStr;
	private int remStud;
	private int[] indices;
	private String selectedRoomStr;
	private Room selectedRoomObj;
	private Date selectedDate;
	
	
	public ScheduleExamGUI(Course selectedCourse) {
		
		ES = ExamScheduler.getInstance();
		this.hoursMap = ES.getHoursMap();
		this.examHours = ES.getExamHours();
		
		this.selectedCourse=selectedCourse;
		roomsList = ES.getRoomList();
	
		roomsListSize = roomsList.size();
		
		listValues = new String[roomsListSize];
		
		int i=0;
		for(Room r : roomsList) {
			roomName = r.getRoomName();
			listValues[i] = roomName;
			i++;
		}
	
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
		
		this.searchButton = new JButton("Search");
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
		hoursBox.setFont(new Font("Arial", Font.PLAIN, 15));
		panel.add(hoursBox);
	
		
		listModel = new DefaultListModel<String>();
		for(String s : listValues) {
			listModel.addElement(s);
		}
		
		remainingStudents = new JLabel("Remaining students: ");
		sl_panel.putConstraint(SpringLayout.WEST, remainingStudents, 86, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, remainingStudents, -10, SpringLayout.SOUTH, panel);
		remainingStudents.setForeground(Color.BLACK);
		remainingStudents.setFont(new Font("Arial", Font.PLAIN, 13));
		panel.add(remainingStudents);
		
		
		JLabel hoursLabel = new JLabel("Available Hours:");
		sl_panel.putConstraint(SpringLayout.EAST, remainingStudents, 0, SpringLayout.EAST, hoursLabel);
		sl_panel.putConstraint(SpringLayout.WEST, hoursLabel, 91, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.WEST, hoursBox, 49, SpringLayout.EAST, hoursLabel);
		sl_panel.putConstraint(SpringLayout.NORTH, hoursLabel, 1, SpringLayout.NORTH, hoursBox);
		hoursLabel.setFont(new Font("Arial", Font.PLAIN, 17));
		panel.add(hoursLabel);
		
		confirmButton = new JButton("Confirm");
		confirmButton.setFont(new Font("Arial", Font.PLAIN, 20));
		springLayout.putConstraint(SpringLayout.NORTH, confirmButton, 14, SpringLayout.SOUTH, panel);
		confirmButton.addActionListener(this);
		
		JLabel suggestedRoomsLabel = new JLabel("Suggested Rooms:");
		sl_panel.putConstraint(SpringLayout.WEST, suggestedRoomsLabel, 91, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, suggestedRoomsLabel, 6, SpringLayout.SOUTH, hoursLabel);
		suggestedRoomsLabel.setFont(new Font("Arial", Font.PLAIN, 17));
		panel.add(suggestedRoomsLabel);
		
		remStudField = new JTextField(Integer.toString(selectedCourse.getNumberOfStudents()));
		sl_panel.putConstraint(SpringLayout.NORTH, remStudField, 189, SpringLayout.SOUTH, suggestedRoomsLabel);
		sl_panel.putConstraint(SpringLayout.WEST, remStudField, 6, SpringLayout.EAST, remainingStudents);
		sl_panel.putConstraint(SpringLayout.SOUTH, remStudField, -10, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, remStudField, -235, SpringLayout.EAST, panel);
		remStudField.setForeground(new Color(0, 0, 0));
		remStudField.setEditable(false);
		remStudField.setFont(new Font("Arial", Font.PLAIN, 12));
		remStudField.setBackground(new Color(225, 225, 225));
		panel.add(remStudField);
		remStudField.setColumns(10);
		
		scrollPane = new JScrollPane();
		sl_panel.putConstraint(SpringLayout.NORTH, scrollPane, 16, SpringLayout.SOUTH, suggestedRoomsLabel);
		sl_panel.putConstraint(SpringLayout.WEST, scrollPane, -4, SpringLayout.WEST, remainingStudents);
		sl_panel.putConstraint(SpringLayout.SOUTH, scrollPane, 166, SpringLayout.SOUTH, suggestedRoomsLabel);
		sl_panel.putConstraint(SpringLayout.EAST, scrollPane, -65, SpringLayout.EAST, panel);
		panel.add(scrollPane);
		
		suggestedRoomsList = new JList<String>(listModel);
		suggestedRoomsList.setModel(listModel);
		suggestedRoomsList.setSelectionModel(new MultiSelectionModel());
		suggestedRoomsList.addMouseListener(this);
		
		scrollPane.setViewportView(suggestedRoomsList);
		springLayout.putConstraint(SpringLayout.WEST, confirmButton, 184, SpringLayout.WEST, this.getContentPane());
		this.getContentPane().add(confirmButton);
		
		roomsMap = this.createRoomsMap();
		
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
		
		try {
			selectedDate = dateChooser.getDate();
		}catch(Exception e1){
			JOptionPane.showMessageDialog(this,"Please select a date","Error",JOptionPane.ERROR_MESSAGE);
		
		}
		
		String convertedDate = ES.ConvertDate(selectedDate);//dd-MM-yy
		
		Course[] coursesArray= new Course[6];
		coursesArray = ES.findDate(convertedDate);
		
		if(e.getSource() == searchButton) {
			
			int i;
			
			for(i=0; i<coursesArray.length; i++) {
				if(!(coursesArray[i] instanceof Course)){	
					this.hoursBox.addItem(examHours[i]);
				}
			}

		}else if(e.getSource() == confirmButton){

		
			String tempHour = (String) hoursBox.getSelectedItem();
			int index = hoursMap.get(tempHour);	
			
			ArrayList<ExamDate> edList = ES.getDates();
			int edListSize = edList.size();
			ExamDate DateObj = edList.get(edListSize-1);
			
			DateObj.addCourse(selectedCourse, index);
			
			if((Integer.parseInt(remStudField.getText())>0)){
				JOptionPane.showMessageDialog(this,"Please select more rooms, there are " + remStudField.getText() +" students remaining","Error",JOptionPane.ERROR_MESSAGE);
			}else {
				
				indices = suggestedRoomsList.getSelectedIndices();
				
				for(int i : indices) {
					
					selectedRoomStr =  listModel.getElementAt(i);
					
					try {
						selectedRoomObj =  roomsMap.get(selectedRoomStr);
					}catch(Exception e1) {
						e1.printStackTrace();
					}
					
					selectedCourse.addSelectedRoom(selectedRoomObj);
				}
				}
				
				
			
			int option = JOptionPane.showConfirmDialog(this, "Do you want to add another course?", "Confirmation", JOptionPane.YES_NO_OPTION);
			
	        if (option == JOptionPane.YES_OPTION) {
	            
	        	SelectCourseGUI.getInstance().setVisible(true);
	        	this.dispose();
	            
	        } else {
	        	
	            LoginGUI.getInstance().setVisible(true);
	            this.dispose();
	        }
	    }
	}
	

		public void mousePressed(MouseEvent e) {
			indices = suggestedRoomsList.getSelectedIndices();
			
			remStud = selectedCourse.getNumberOfStudents();
			
			for(int i : indices) {
				selectedRoomStr =  listModel.getElementAt(i);
				
				selectedRoomObj=null;
				
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
				
				remStud = ES.calcRemainingStudents(remStud, roomCapacity);
			
			}
				remStudStr = String.valueOf(remStud);
				remStudField.setText(remStudStr);
		}
		
		public void mouseClicked(MouseEvent e) {
		}
		public void mouseReleased(MouseEvent e) {	
		}
		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent e) {
		}
			
		private HashMap<String, Room> createRoomsMap(){
			HashMap<String, Room> map = new HashMap<>();
	        for (Room r : roomsList) {
	            map.put(r.getRoomName(), r);
	        }
	        return map;
		}
		
		static class MultiSelectionModel extends DefaultListSelectionModel {
		        @Override
		     public void setSelectionInterval(int index0, int index1) {
		         if (isSelectedIndex(index0)) {
		             super.removeSelectionInterval(index0, index1);
		         } else {
		             super.addSelectionInterval(index0, index1);
		         }
		     }
		 }
}
		

