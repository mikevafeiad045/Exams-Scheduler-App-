
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SelectCourseGUI extends JFrame {
	
	private JPanel panel;
	private JButton selectedButton;
	private JLabel label;
	private JComboBox<String> courseBox;
	
	public SelectCourseGUI (){
		//Testing DropDown
		String[] courses= {"x","y"};
		
		//End of example
		panel= new JPanel();
		selectedButton= new JButton("Confirm");
		label= new JLabel("Select Course");
		courseBox= new JComboBox<String>(courses);
		
		panel.add(label);
		panel.add(courseBox);
		panel.add(selectedButton);
		
		
		ButtonListener listener= new ButtonListener();
		selectedButton.addActionListener(listener);
		courseBox.addActionListener(listener);
		
		
		
		this.setContentPane(panel);
		this.setVisible(true);
		this.setSize(400,400);
		this.setTitle("Select Course");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JComboBox<String> cb = (JComboBox)e.getSource();
	        String course= (String)cb.getSelectedItem();
	        new programExamsGUI(course);
	        
		}
		
	}

}
