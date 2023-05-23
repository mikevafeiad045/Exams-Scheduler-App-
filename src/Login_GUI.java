import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class Login_GUI extends JFrame {
	
	private JPanel panel;
	private JButton loginButton;
	private JPasswordField passwordField;
	private JLabel passwordLabel;
	
	public Login_GUI() {
		
		panel= new JPanel();
		
		passwordLabel= new JLabel("Password");
		panel.add(passwordLabel);
		
		passwordField= new JPasswordField(10);
		panel.add(passwordField);
		
		loginButton= new JButton("Login");
		panel.add(loginButton);
		ButtonListener listener= new ButtonListener();
		loginButton.addActionListener(listener);
		
		
		
		this.setContentPane(panel);
		this.setVisible(true);
		this.setSize(400,400);
		this.setTitle("Log In Page");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
	}
	
	class ButtonListener implements ActionListener{
		
		//private boolean flag=true;

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			String inputPass =  new String(passwordField.getPassword());
			
			if(inputPass.equals("$3cReT4rY")) {
				RoomsGUI roomsGUI= RoomsGUI.getInstance();
				if(roomsGUI!=null) {
					roomsGUI.setVisible(true);
				}
			}else if(inputPass.equals("pR0!3$$oR")) {
				//new ProfGUI().getVisible(true);
			}else {//Wrong Password
				JOptionPane.showMessageDialog(null,"Wrong Password! Please try again.","ERROR",JOptionPane.ERROR_MESSAGE);
				passwordField.setText("");
			}
			
			
		}
		
	}
	

}
