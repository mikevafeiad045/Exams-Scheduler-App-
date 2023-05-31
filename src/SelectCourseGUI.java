import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

public class SelectCourseGUI extends JFrame implements ActionListener {
	
	private JButton btnConfirm;
	private JComboBox courseBox;
	
	
	public SelectCourseGUI() {
		
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
		
		courseBox = new JComboBox();
		courseBox.setFont(new Font("Arial", Font.PLAIN, 15));
		springLayout.putConstraint(SpringLayout.NORTH, courseBox, 29, SpringLayout.SOUTH, selectLabel);
		springLayout.putConstraint(SpringLayout.EAST, courseBox, 0, SpringLayout.EAST, selectLabel);
		this.getContentPane().add(courseBox);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setFont(new Font("Arial", Font.PLAIN, 15));
		springLayout.putConstraint(SpringLayout.NORTH, btnConfirm, 31, SpringLayout.SOUTH, courseBox);
		springLayout.putConstraint(SpringLayout.WEST, btnConfirm, 149, SpringLayout.WEST, this.getContentPane());
		this.getContentPane().add(btnConfirm);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
