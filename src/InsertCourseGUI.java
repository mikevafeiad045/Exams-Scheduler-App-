import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class InsertCourseGUI extends JFrame implements ActionListener{
	
	private JTextField coursetextField;
	private JTextField proftextField;
	private JTextField numtextField;
	private JButton insertButton;
	private JButton confirmButton;
	private Secretary secr;
	private ExamScheduler ES;
	
	public InsertCourseGUI(Secretary secr, ExamScheduler ES) {
		//"C:\\Users\\ELENI\\Documents\\Exams-Scheduler-App-\\
		
		SpringLayout springLayout = new SpringLayout();
		this.getContentPane().setLayout(springLayout);
		
		JLabel insertLabel = new JLabel("Insert Courses");
		springLayout.putConstraint(SpringLayout.WEST, insertLabel, 73, SpringLayout.WEST, this.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, insertLabel, -527, SpringLayout.SOUTH, this.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, insertLabel, -77, SpringLayout.EAST, this.getContentPane());
		insertLabel.setHorizontalAlignment(SwingConstants.CENTER);
		insertLabel.setFont(new Font("Arial", Font.BOLD, 25));
		this.getContentPane().add(insertLabel);
		
		JPanel InsertPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.EAST, InsertPanel, 521, SpringLayout.WEST, getContentPane());
		InsertPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		springLayout.putConstraint(SpringLayout.NORTH, InsertPanel, 42, SpringLayout.NORTH, this.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, InsertPanel, -343, SpringLayout.SOUTH, this.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, InsertPanel, 73, SpringLayout.WEST, this.getContentPane());
		this.getContentPane().add(InsertPanel);
		SpringLayout sl_InsertPanel = new SpringLayout();
		InsertPanel.setLayout(sl_InsertPanel);
		
		JLabel coursenameLabel = new JLabel("Course Name");
		sl_InsertPanel.putConstraint(SpringLayout.WEST, coursenameLabel, 10, SpringLayout.WEST, InsertPanel);
		coursenameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		InsertPanel.add(coursenameLabel);
		
		coursetextField = new JTextField();
		sl_InsertPanel.putConstraint(SpringLayout.WEST, coursetextField, 18, SpringLayout.EAST, coursenameLabel);
		sl_InsertPanel.putConstraint(SpringLayout.NORTH, coursenameLabel, -1, SpringLayout.NORTH, coursetextField);
		sl_InsertPanel.putConstraint(SpringLayout.NORTH, coursetextField, 30, SpringLayout.NORTH, InsertPanel);
		InsertPanel.add(coursetextField);
		coursetextField.setColumns(10);
		
		JLabel profLabel = new JLabel("Professor(s)");
		sl_InsertPanel.putConstraint(SpringLayout.WEST, profLabel, 19, SpringLayout.EAST, coursetextField);
		profLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		InsertPanel.add(profLabel);
		
		proftextField = new JTextField();
		sl_InsertPanel.putConstraint(SpringLayout.NORTH, proftextField, 1, SpringLayout.NORTH, coursenameLabel);
		sl_InsertPanel.putConstraint(SpringLayout.WEST, proftextField, 7, SpringLayout.EAST, profLabel);
		InsertPanel.add(proftextField);
		proftextField.setColumns(10);
		
		JLabel numLabel = new JLabel("Number of Students");
		sl_InsertPanel.putConstraint(SpringLayout.NORTH, numLabel, 18, SpringLayout.SOUTH, coursetextField);
		sl_InsertPanel.putConstraint(SpringLayout.EAST, numLabel, -241, SpringLayout.EAST, InsertPanel);
		numLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		InsertPanel.add(numLabel);
		
		numtextField = new JTextField();
		sl_InsertPanel.putConstraint(SpringLayout.SOUTH, profLabel, -21, SpringLayout.NORTH, numtextField);
		sl_InsertPanel.putConstraint(SpringLayout.NORTH, numtextField, 1, SpringLayout.NORTH, numLabel);
		sl_InsertPanel.putConstraint(SpringLayout.WEST, numtextField, 6, SpringLayout.EAST, numLabel);
		InsertPanel.add(numtextField);
		numtextField.setColumns(10);
		
		insertButton = new JButton("Insert");
		sl_InsertPanel.putConstraint(SpringLayout.NORTH, insertButton, 27, SpringLayout.SOUTH, numtextField);
		sl_InsertPanel.putConstraint(SpringLayout.WEST, insertButton, 172, SpringLayout.WEST, InsertPanel);
		insertButton.setFont(new Font("Arial", Font.PLAIN, 15));
		InsertPanel.add(insertButton);
		
		confirmButton = new JButton("Confirm");
		springLayout.putConstraint(SpringLayout.NORTH, confirmButton, 25, SpringLayout.SOUTH, InsertPanel);
		springLayout.putConstraint(SpringLayout.WEST, confirmButton, 239, SpringLayout.WEST, this.getContentPane());
		confirmButton.setFont(new Font("Arial", Font.PLAIN, 15));
		confirmButton.addActionListener(this);
		this.getContentPane().add(confirmButton);
		
		this.setAlwaysOnTop(true);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png"));
		this.setTitle("Secretary");
		this.setSize(600,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== insertButton) {
			String numOfStudents = numtextField.getText();
			String profs = proftextField.getText();
			Course c = new Course(coursetextField.getText(),Integer.parseInt(numOfStudents));
			this.addProfsToCourse(c, profs);
			secr.addCourse(c);
			
		}else {
			this.dispose();
			new LoginGUI();
		}
		
	}

	public void addProfsToCourse(Course c, String profsInput) {
		String[] names = profsInput.split(",");
		for(String s : names) {
			s.trim();
			c.addProf(s);
		}
	}
}
