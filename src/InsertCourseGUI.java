import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
//import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;*/

import javax.swing.*;
import javax.swing.border.LineBorder;

public class InsertCourseGUI extends JFrame implements ActionListener{
	
	private JTextField coursetextField;
	private JTextField proftextField;
	private JTextField numtextField;
	private JButton insertButton;
	private JButton confirmButton;
	private ExamScheduler ES;
	private JScrollPane scrollPane;
	private JList<String> list;
	private DefaultListModel<String> listModel;
	
	public InsertCourseGUI() {
		
		ES = ExamScheduler.getInstance();	
		
		SpringLayout springLayout = new SpringLayout();
		this.getContentPane().setLayout(springLayout);
		
		JLabel insertLabel = new JLabel("Insert Courses");
		springLayout.putConstraint(SpringLayout.EAST, insertLabel, -77, SpringLayout.EAST, getContentPane());
		insertLabel.setHorizontalAlignment(SwingConstants.CENTER);
		insertLabel.setFont(new Font("Arial", Font.BOLD, 25));
		this.getContentPane().add(insertLabel);
		
		JPanel InsertPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, InsertPanel, 61, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, InsertPanel, 73, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, InsertPanel, -324, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, InsertPanel, -65, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, insertLabel, 0, SpringLayout.WEST, InsertPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, insertLabel, -6, SpringLayout.NORTH, InsertPanel);
		InsertPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
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
		insertButton.setBackground(new Color(128, 255, 255));
		insertButton.setForeground(new Color(41, 41, 41));
		sl_InsertPanel.putConstraint(SpringLayout.NORTH, insertButton, 26, SpringLayout.SOUTH, numtextField);
		sl_InsertPanel.putConstraint(SpringLayout.WEST, insertButton, 163, SpringLayout.WEST, InsertPanel);
		sl_InsertPanel.putConstraint(SpringLayout.SOUTH, insertButton, -29, SpringLayout.SOUTH, InsertPanel);
		sl_InsertPanel.putConstraint(SpringLayout.EAST, insertButton, 272, SpringLayout.WEST, InsertPanel);
		insertButton.setFont(new Font("Arial", Font.PLAIN, 15));
		InsertPanel.add(insertButton);
		
		insertButton.addActionListener(this);
		
		confirmButton = new JButton("Save & Exit");
		springLayout.putConstraint(SpringLayout.NORTH, confirmButton, 208, SpringLayout.SOUTH, InsertPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, confirmButton, -69, SpringLayout.SOUTH, getContentPane());
		confirmButton.setBackground(new Color(253, 253, 253));
		confirmButton.setForeground(new Color(0, 215, 11));
		springLayout.putConstraint(SpringLayout.WEST, confirmButton, 237, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, confirmButton, -231, SpringLayout.EAST, getContentPane());
		confirmButton.setFont(new Font("Arial", Font.PLAIN, 15));
		confirmButton.addActionListener(this);
		this.getContentPane().add(confirmButton);
		
		scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 263, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, insertLabel);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, -502, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 189, SpringLayout.SOUTH, InsertPanel);
		getContentPane().add(scrollPane);
		
		list = new JList<>();
		scrollPane.setViewportView(list);
		
		listModel = new DefaultListModel<>();	
		list.setModel(listModel);
	   
	    //list.setBounds(0, 0, 484, 461);
       // list.setFont(list.getFont().deriveFont(14.0f));
		list.setFont(new Font("Arial", Font.PLAIN, 16));
        list.setFixedCellHeight(35);
        list.setVisible(true);
		
		
		JButton discardButton = new JButton("Discard all & Exit ");
		springLayout.putConstraint(SpringLayout.NORTH, discardButton, -58, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, discardButton, -23, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, discardButton, -25, SpringLayout.EAST, getContentPane());
		discardButton.setForeground(new Color(255, 255, 255));
		discardButton.setBackground(new Color(255, 77, 81));
		discardButton.addActionListener(this);
		getContentPane().add(discardButton);
		
		addWindowListener(new ProgramTerminated());
		
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
			
			if(this.checkTextFields()) {
				String courseName = coursetextField.getText().trim();
				String profs = proftextField.getText().trim();
				String numOfStudents = numtextField.getText().trim();
				
				Course c = new Course(courseName,Integer.parseInt(numOfStudents));
				c.addProfs(profs);
				ES.addCourse(c);
			
				listModel.addElement(courseName + "  |  " + profs + "  |  " + numOfStudents);
				
				coursetextField.setText("");
				proftextField.setText("");
				numtextField.setText("");
			}else {
				JOptionPane.showMessageDialog(this,"All the fields are Required!");
				
			}
			
			
			
		}else if(e.getSource()== confirmButton) {
			this.dispose();
			LoginGUI.getInstance().setVisible(true);

		}else {
			System.exit(0);
		}
		
	}
	
	public boolean checkTextFields() {
		
		if(coursetextField.getText().equals("") 
			|| proftextField.getText().equals("")
			|| numtextField.getText().equals("")
			) 
		{
				return false;
		
		}else {

				return true;
		}
	}
}
