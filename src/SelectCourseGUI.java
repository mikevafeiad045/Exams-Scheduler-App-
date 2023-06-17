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
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;


import java.awt.Color;

/**
 * A professor's gui for selecting a course to schedule for examination
 * 
 * @version 17 Jun 2023
 * @author Michalis Vafeiadis, Eleni Loula
 */
public class SelectCourseGUI extends JFrame implements ActionListener {

	private static SelectCourseGUI instance;

	private static JButton btnConfirm;
	private static JComboBox<String> courseBox;
	private static ArrayList<Course> courses;
	private static ExamScheduler ES;


	private SelectCourseGUI() {

		ES = ExamScheduler.getInstance();

		courses = ES.getCourseList();

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
		btnConfirm.addActionListener(this);
		this.getContentPane().add(btnConfirm);

		addWindowListener(new ProgramTerminated());//data saved to files before termination

		this.setAlwaysOnTop(true);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/logo.png")));
		this.setTitle("Select Course");
		this.setSize(400,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	/**
	 * Returns the singleton instance of SelectCourseGUI.
	 *
	 * @return the singleton instance
	 */
	public static SelectCourseGUI getInstance() {
		if (instance == null) {
			instance = new SelectCourseGUI();
		}
		return instance;
	}

	/**
	 * Button listener for the confirm button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//When confirm button gets pressed, the selected course from the JComboBox gets selected

		String selectedCourseName = (String) courseBox.getSelectedItem();
		if(selectedCourseName != null) {

			HashMap<String, Course> map = this.createCourseMap();
			Course selectedCourse=null;
			try {
				selectedCourse = map.get(selectedCourseName);

			}catch(Exception e1) {
				e1.printStackTrace();
			}

			//Use the course object in next GUI...
			new ScheduleExamGUI(selectedCourse);
			this.dispose();
		} else {//no course selected
			JOptionPane.showMessageDialog(this,"Please select a course before proceeding","No course selected",JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Hash map creation method. <Course name, course object>
	 * 
	 * @return map
	 */
	private HashMap<String, Course> createCourseMap() {
		HashMap<String, Course> map = new HashMap<>();
		for (Course c : courses) {
			map.put(c.getCourseName(), c);
		}
		return map;
	}

}
