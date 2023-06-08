import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class LoginGUI extends JFrame implements ActionListener{
	
	private static JButton loginButton;
	private static JPasswordField passwordField;
	private static JLabel passwordLabel;
	
	public LoginGUI() {
		
		// Label Settings and Styling
		ImageIcon logo1= new ImageIcon("logo1.png");//Creating Icon
		passwordLabel= new JLabel("Password");//Creating Label
		passwordLabel.setIcon(logo1);
		passwordLabel.setHorizontalTextPosition(JLabel.CENTER);//Settings for the location of the 
		passwordLabel.setVerticalTextPosition(JLabel.BOTTOM);//text compare to the icon
		passwordLabel.setIconTextGap(20);
		passwordLabel.setForeground(new Color(000000));
		passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		this.add(passwordLabel);
		
		//Password Field Settings
		passwordField= new JPasswordField(10);
		this.add(passwordField);
		
		 //Login Button Settings
		loginButton= new JButton("Login");
		loginButton.addActionListener(this);//Connecting the button with the action
		this.add(loginButton);
		
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER,1000,50));
		this.getContentPane().setBackground(new Color(87,87,87));
		this.setSize(300,400);
		this.setTitle("Log In Page");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Frame Icon
		ImageIcon logo= new ImageIcon("logo.png");
		this.setIconImage(logo.getImage());
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==loginButton) {
			String inputPass = String.valueOf(passwordField.getPassword());
			
			if(inputPass.equals("$3cReT4rY")) {
				this.dispose();
				RoomsGUI roomsGUI= RoomsGUI.getInstance();
				if(roomsGUI!=null) {
					roomsGUI.setVisible(true);
				}
				else {
					new InsertCourseGUI(null);
				}
			}else if(inputPass.equals("pR0!3$$oR")) {
				this.dispose();
				//new ProfGUI().getVisible(true);
			}else {//Wrong Password
				JOptionPane.showMessageDialog(null,"Wrong Password! Please try again.","ERROR",JOptionPane.ERROR_MESSAGE);
				passwordField.setText("");
			}
			
		}
		
	}
	
}
