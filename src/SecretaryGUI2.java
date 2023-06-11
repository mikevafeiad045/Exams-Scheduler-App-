import java.awt.Color;
import java.awt.Cursor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;

import javax.swing.JComboBox;
import java.awt.Font;


import javax.swing.JButton;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.IOException;



public class SecretaryGUI2 extends JFrame implements ActionListener, MouseListener{
	
	JButton viewProgButton;
	JButton logoutButton;
	JComboBox<String> courseBoxList;
	JLabel optionsLabel;
	JLabel resetLabel;

	ExamScheduler ES;
	

	public SecretaryGUI2() {
		
		ES = ExamScheduler.getInstance();
		

		ArrayList<Course> tempList = ES.getCourseList();
		int sizeOfList = tempList.size();

		String[] listA = new String[sizeOfList];

		for (int i = 0; i < sizeOfList; i++) {
		    listA[i] = tempList.get(i).getCourseName();
		}
		
		courseBoxList = new JComboBox<String>();
		courseBoxList.addItem("-Courses-");
		courseBoxList.setSelectedItem("-Courses-");
		//courseBoxList.setToolTipText("");
		courseBoxList.setForeground(new Color(0, 0, 0));
		courseBoxList.setFont(new Font("Arial", Font.PLAIN, 12));
		courseBoxList.setBackground(new Color(255, 255, 255));
		courseBoxList.setMaximumRowCount(30);
		courseBoxList.setBounds(119, 92, 190, 35);
		courseBoxList.setModel(new DefaultComboBoxModel<String>(listA));
		getContentPane().add(courseBoxList);
		
		optionsLabel = new JLabel("Options");
		optionsLabel.setForeground(new Color(61, 61, 61));
		optionsLabel.setBackground(new Color(255, 255, 255));
		optionsLabel.setFont(new Font("Arial", Font.PLAIN, 27));
		optionsLabel.setBounds(164, 36, 100, 46);
		getContentPane().add(optionsLabel);
		
		viewProgButton = new JButton("View Schedule");
		viewProgButton.setForeground(new Color(42, 42, 42));
		viewProgButton.setBackground(new Color(153, 255, 153));
		viewProgButton.setBounds(152, 153, 123, 46);
		getContentPane().add(viewProgButton);
		
		logoutButton = new JButton("LogOut");
		logoutButton.setForeground(new Color(0, 0, 0));
		logoutButton.setBackground(new Color(255, 255, 255));
		
		
		logoutButton.addActionListener(this);
		viewProgButton.addActionListener(this);
	
		logoutButton.setBounds(330, 256, 76, 27);
		getContentPane().add(logoutButton);
		
		resetLabel = new JLabel("<html><u>Create new exams schedule</u></html>");
		resetLabel.setForeground(new Color(255, 0, 0));
		resetLabel.setBounds(25, 256, 173, 27);
		resetLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		
		resetLabel.addMouseListener(this);
		
		getContentPane().add(resetLabel);
		//Frame Icon
		ImageIcon logo= new ImageIcon("logo.png");
		this.setIconImage(logo.getImage());
		getContentPane().setLayout(null);
		
		addWindowListener(new ProgramTerminated());
		
		this.setSize(453,350);
		this.setTitle("Secretary options");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
		
		public void actionPerformed(ActionEvent e) {
		
			if(e.getSource()==viewProgButton) {
				new CalendarGUI();
				
			}else if(e.getSource()==logoutButton) {
				LoginGUI.getInstance().setVisible(true);;
				this.dispose(); 
				
			}
			
		}

		
		 
		public void mouseClicked(MouseEvent e) {
			
			ES.saveToRoomsFile();
			ES.saveToCourseFile();
			
			 //path of the file to delete
	        String filePath = "dates.ser";

	        // Creating a Path object with the specified file path
	        Path pathToDelete = Paths.get(filePath);


	        try {
	            Files.delete(pathToDelete);
	            System.out.println("File deleted successfully.");
	        } catch (IOException e1) {
	            System.out.println("Failed to delete the file: " + e1.getMessage());
	        }
			
			System.exit(0);
			
	    }

		
		public void mousePressed(MouseEvent e) {
		}
		public void mouseReleased(MouseEvent e) {	
		}
		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent e) {
		}
	 }
	

