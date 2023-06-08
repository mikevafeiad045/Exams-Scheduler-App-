import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JButton;


public class SecretaryGUI2 extends JFrame implements ActionListener, MouseListener{
	
	JButton viewProgButton;
	JButton logoutButton;
	JComboBox<String> courseBoxList;
	JLabel optionsLabel;
	JLabel resetLabel;
	ExamScheduler es;

	public SecretaryGUI2(ExamScheduler ES) {
		
		es=ES;
		
		courseBoxList = new JComboBox<String>();
		courseBoxList.addItem("-Courses-");
		courseBoxList.setSelectedItem("-Courses-");
		courseBoxList.setToolTipText("");
		courseBoxList.setForeground(new Color(255, 255, 255));
		courseBoxList.setFont(new Font("Arial", Font.PLAIN, 12));
		courseBoxList.setBackground(new Color(255, 255, 255));
		courseBoxList.setMaximumRowCount(30);
		courseBoxList.setBounds(119, 92, 190, 35);
		getContentPane().add(courseBoxList);
		
		optionsLabel = new JLabel("Options");
		optionsLabel.setForeground(new Color(61, 61, 61));
		optionsLabel.setBackground(new Color(255, 255, 255));
		optionsLabel.setFont(new Font("Arial", Font.PLAIN, 27));
		optionsLabel.setBounds(164, 36, 100, 46);
		getContentPane().add(optionsLabel);
		
		viewProgButton = new JButton("View Schedule");
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
		
		this.setSize(453,350);
		this.setTitle("Secretary options");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
		
		public void actionPerformed(ActionEvent e) {
		
			if(e.getSource()==viewProgButton) {
				new CalendarGUI(es);
			}else if(e.getSource()==logoutButton) {
				new LoginGUI(es);
			}
			this.dispose(); 
		}

		
		 
		public void mouseClicked(MouseEvent e) {
	            // Mouse click event handling
			 // JOptionPane.showMessageDialog(SecretaryGUI2.this, "Tappable text clicked!");
			
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
	

//}
