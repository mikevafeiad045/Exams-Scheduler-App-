import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class Login_GUI extends JFrame {
	
	private static JButton loginButton;
	private static JPasswordField passwordField;
	private static JLabel passwordLabel;
	
	public Login_GUI() {
		
		// Label Settings and Styling
		ImageIcon logo1= new ImageIcon("logo1.png");//Creating Icon
		passwordLabel= new JLabel("Password");//Creating Label
		passwordLabel.setIcon(logo1);
		passwordLabel.setHorizontalTextPosition(JLabel.CENTER);//Settings for the location of the 
		passwordLabel.setVerticalTextPosition(JLabel.BOTTOM);//text compare to the icon
		passwordLabel.setIconTextGap(25);
		passwordLabel.setForeground(new Color(000000));
		this.add(passwordLabel);
		
		//Password Field Settings
		passwordField= new JPasswordField(10);
		this.add(passwordField);
		
		 //Login Button Settings
		loginButton= new JButton("Login");
		loginButton.setBounds(100, 100, 100, 50);
		ButtonListener listener= new ButtonListener();//Creating the button listener
		loginButton.addActionListener(listener);//Connecting the button listener with the button
		loginButton.setBounds(100, 100, 200, 100);
		this.add(loginButton);
		
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER,1000,50));
		this.getContentPane().setBackground(new Color(87,87,87));
		this.setVisible(true);
		this.setSize(300,400);
		this.setTitle("Log In Page");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Frame Icon
		ImageIcon logo= new ImageIcon("logo.png");
		this.setIconImage(logo.getImage());
		
		
		
	}
	
	class ButtonListener implements ActionListener{
		
		//private boolean flag=true;

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			String inputPass = String.valueOf(passwordField.getPassword());
			
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
