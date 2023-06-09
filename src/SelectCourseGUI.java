import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import java.awt.Color;

public class SelectCourseGUI extends JFrame implements ActionListener {
	
	private JButton btnConfirm;
	private JComboBox<String> courseBox;
	private ArrayList<Course> courses;
	private ExamScheduler ES;
	
	
	public SelectCourseGUI(ExamScheduler ES) {
		
		this.ES=ES;
		courses.addAll(ES.getCourseList());
	
		SpringLayout springLayout = new SpringLayout();
		this.getContentPane().setLayout(springLayout);
		
		JLabel selectLabel = new JLabel("Select Course");
		springLayout.putConstraint(SpringLayout.WEST, selectLabel, 116, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, selectLabel, -108, SpringLayout.EAST, getContentPane());
		selectLabel.setFont(new Font("Arial", Font.PLAIN, 24));
		this.getContentPane().add(selectLabel);
		
		courseBox = new JComboBox<String>();
		courseBox.setBackground(new Color(229, 229, 229));
		springLayout.putConstraint(SpringLayout.SOUTH, selectLabel, -37, SpringLayout.NORTH, courseBox);
		springLayout.putConstraint(SpringLayout.EAST, courseBox, -119, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, courseBox, -246, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, courseBox, 0, SpringLayout.WEST, selectLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, courseBox, -210, SpringLayout.SOUTH, getContentPane());
		courseBox.setFont(new Font("Arial", Font.PLAIN, 15));
		this.getContentPane().add(courseBox);
		
		for (Course c : courses) {
            courseBox.addItem(c.getCourseName());
        }
		
		btnConfirm = new JButton("Confirm");
		springLayout.putConstraint(SpringLayout.NORTH, btnConfirm, 26, SpringLayout.SOUTH, courseBox);
		springLayout.putConstraint(SpringLayout.WEST, btnConfirm, 147, SpringLayout.WEST, getContentPane());
		btnConfirm.setBackground(new Color(141, 255, 113));
		btnConfirm.setFont(new Font("Arial", Font.PLAIN, 15));
		this.getContentPane().add(btnConfirm);
		
		addWindowListener(new ProgramTerminated(ES));
		
		this.setAlwaysOnTop(true);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png"));
		this.setTitle("Select Course");
		this.setSize(400,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//When confirm button gets pressed, the selected course from the JComboBox gets selected
		
		String selectedCourseName = (String) courseBox.getSelectedItem();
		//HashcourseMap = createCourseMap();
		HashMap<String, Course> map = this.createCourseMap();
		Course selectedCourse=null;
		try {
			selectedCourse = map.get(selectedCourseName);
			
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		
		
		if(selectedCourse == null) System.exit(1);
		//Use the course object in next GUI...
		new ScheduleExamGUI(ES,selectedCourse);
		this.dispose();
		
		
	}
	
	 private HashMap<String, Course> createCourseMap() {
	        HashMap<String, Course> map = new HashMap<>();
	        for (Course c : courses) {
	            map.put(c.getCourseName(), c);
	        }
	        return map;
	    }

}
