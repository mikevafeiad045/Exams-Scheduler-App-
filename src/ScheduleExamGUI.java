import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class ScheduleExamGUI extends JFrame implements ActionListener {
	
	private JPanel panel;
	private JLabel courseLabel;
	private JButton searchButton;
	private JComboBox<String> hoursBox;
	private JList<String> suggestedRoomsList;
	private JButton confirmButton;
	
	
	
	public ScheduleExamGUI() {
		

		this.setAlwaysOnTop(true);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ELENI\\Documents\\Exams-Scheduler-App-\\logo.png"));
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
		
		JLabel courseLabel = new JLabel("Course/NumOfStudents");
		sl_panel.putConstraint(SpringLayout.NORTH, courseLabel, 10, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, courseLabel, -128, SpringLayout.EAST, panel);
		courseLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		panel.add(courseLabel);
		
		JLabel selectLabel = new JLabel("Select Date:");
		selectLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		panel.add(selectLabel);
		
		JDateChooser dateChooser = new JDateChooser();
		sl_panel.putConstraint(SpringLayout.NORTH, selectLabel, 0, SpringLayout.NORTH, dateChooser);
		sl_panel.putConstraint(SpringLayout.EAST, selectLabel, -18, SpringLayout.WEST, dateChooser);
		sl_panel.putConstraint(SpringLayout.NORTH, dateChooser, 26, SpringLayout.SOUTH, courseLabel);
		sl_panel.putConstraint(SpringLayout.WEST, dateChooser, 197, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, dateChooser, -321, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, dateChooser, -110, SpringLayout.EAST, panel);
		panel.add(dateChooser);
		
		JButton searchButton = new JButton("Search");
		sl_panel.putConstraint(SpringLayout.NORTH, searchButton, 25, SpringLayout.SOUTH, dateChooser);
		sl_panel.putConstraint(SpringLayout.WEST, searchButton, 197, SpringLayout.WEST, panel);
		searchButton.setFont(new Font("Arial", Font.PLAIN, 15));
		panel.add(searchButton);
		
		JComboBox hoursBox = new JComboBox();
		sl_panel.putConstraint(SpringLayout.WEST, hoursBox, 244, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, hoursBox, 0, SpringLayout.EAST, courseLabel);
		hoursBox.setModel(new DefaultComboBoxModel(new String[] {"9:00-11:00", "11:00-13:00", "13:00-15:00", "15:00-17:00", "17:00-19:00", "19:00-21:00"}));
		hoursBox.setFont(new Font("Arial", Font.PLAIN, 15));
		panel.add(hoursBox);
		
		JList suggestedRoomsList = new JList();
		sl_panel.putConstraint(SpringLayout.NORTH, suggestedRoomsList, 50, SpringLayout.SOUTH, hoursBox);
		sl_panel.putConstraint(SpringLayout.WEST, suggestedRoomsList, 78, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, suggestedRoomsList, -10, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, suggestedRoomsList, 0, SpringLayout.EAST, dateChooser);
		suggestedRoomsList.setModel(new AbstractListModel() {
			String[] values = new String[] {"1", "2", "3", "4", "5"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		suggestedRoomsList.setFont(new Font("Arial", Font.PLAIN, 13));
		suggestedRoomsList.setBackground(new Color(192, 192, 192));
		panel.add(suggestedRoomsList);
		
		JLabel hoursLabel = new JLabel("Available Hours:");
		sl_panel.putConstraint(SpringLayout.NORTH, hoursLabel, 84, SpringLayout.SOUTH, selectLabel);
		sl_panel.putConstraint(SpringLayout.EAST, hoursLabel, -50, SpringLayout.WEST, hoursBox);
		sl_panel.putConstraint(SpringLayout.NORTH, hoursBox, -3, SpringLayout.NORTH, hoursLabel);
		hoursLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		panel.add(hoursLabel);
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 20));
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 14, SpringLayout.SOUTH, panel);
		
		JLabel suggestedRoomsLabel = new JLabel("Suggested Rooms:");
		sl_panel.putConstraint(SpringLayout.NORTH, suggestedRoomsLabel, 11, SpringLayout.SOUTH, hoursBox);
		sl_panel.putConstraint(SpringLayout.WEST, suggestedRoomsLabel, 157, SpringLayout.WEST, panel);
		suggestedRoomsLabel.setFont(new Font("Arial", Font.PLAIN, 17));
		panel.add(suggestedRoomsLabel);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 184, SpringLayout.WEST, this.getContentPane());
		this.getContentPane().add(btnNewButton);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
