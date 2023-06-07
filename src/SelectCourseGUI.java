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

public class SelectCourseGUI extends JFrame implements ActionListener {
	
	private JButton btnConfirm;
	private JComboBox<String> courseBox;
	private ArrayList<Course> courses;
	
	
	public SelectCourseGUI(Secretary S) {
		
		courses.addAll(S.getCourseList());
	
		
		this.setAlwaysOnTop(true);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ELENI\\Documents\\Exams-Scheduler-App-\\logo.png"));
		this.setTitle("Select Course");
		this.setSize(400,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		this.getContentPane().setLayout(springLayout);
		
		JLabel selectLabel = new JLabel("Select Course");
		springLayout.putConstraint(SpringLayout.NORTH, selectLabel, 37, SpringLayout.NORTH, this.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, selectLabel, 126, SpringLayout.WEST, this.getContentPane());
		selectLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		this.getContentPane().add(selectLabel);
		
		courseBox = new JComboBox<String>();
		courseBox.setFont(new Font("Arial", Font.PLAIN, 15));
		springLayout.putConstraint(SpringLayout.NORTH, courseBox, 29, SpringLayout.SOUTH, selectLabel);
		springLayout.putConstraint(SpringLayout.EAST, courseBox, 0, SpringLayout.EAST, selectLabel);
		this.getContentPane().add(courseBox);
		
		for (Course c : courses) {
            courseBox.addItem(c.getCourseName());
        }
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setFont(new Font("Arial", Font.PLAIN, 15));
		springLayout.putConstraint(SpringLayout.NORTH, btnConfirm, 31, SpringLayout.SOUTH, courseBox);
		springLayout.putConstraint(SpringLayout.WEST, btnConfirm, 149, SpringLayout.WEST, this.getContentPane());
		this.getContentPane().add(btnConfirm);
		
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//When confirm button gets pressed, the selected course from the JComboBox gets selected
		
		String selectedCourseName = (String) courseBox.getSelectedItem();
		//HashcourseMap = createCourseMap();
		HashMap<String, Course> map = this.createCourseMap();
		try {
			Course selectedCourse = map.get(selectedCourseName);
		}catch(Exception e1) {
			e1.printStackTrace();
			//System.exit(1);	
		}
		
		//Use the course object in next GUI...
		
		
	}
	
	 private HashMap<String, Course> createCourseMap() {
	        HashMap<String, Course> map = new HashMap<>();
	        for (Course c : courses) {
	            map.put(c.getCourseName(), c);
	        }
	        return map;
	    }

}
