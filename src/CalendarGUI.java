import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class CalendarGUI extends JFrame{
	
	
	public CalendarGUI() {
		
		
		
		
		ImageIcon logo= new ImageIcon("logo.png");
		this.setIconImage(logo.getImage());
		
		this.setSize(1900,1000);
		this.setTitle("Calendar");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	

	public void actionPerformed(ActionEvent e) {
		
		
	}

}
