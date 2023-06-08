import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Canvas;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.FlowLayout;

public class CalendarGUI extends JFrame{
	
	
	JPanel panel;
	
	public CalendarGUI() {
		
		panel=new JPanel();
		
		
		
		ImageIcon logo= new ImageIcon("logo.png");
		this.setIconImage(logo.getImage());
		
		this.setSize(500,500);
		this.setTitle("Calendar");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		this.setContentPane(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		this.setVisible(true);
	}

	

	public void actionPerformed(ActionEvent e) {
		
		
	}
}
