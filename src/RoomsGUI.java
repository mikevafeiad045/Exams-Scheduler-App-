import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class RoomsGUI extends JFrame implements ActionListener {
	
	private static boolean instance=true;
	
	private static ExamScheduler scheduler;
	
	private static JPanel roomsPanel;
	private static JLabel roomsLabel;
	private static JLabel periodLabel;
	
	//Components for Auditoriums
	private static JPanel audPanel;
	private static JLabel audLabel;
	private static JLabel audnumLabel;
	private static JLabel audcapLabel;
	private static JTextField audnumText;
	private static JTextField audcapText;
	
	//Components for Amphitheatres
	private static JPanel ampPanel;
	private static JLabel ampLabel;
	private static JLabel ampnumLabel;
	private static JLabel ampcapLabel;
	private static JTextField ampnumText;
	private static JTextField ampcapText;
	
	//Components for Exams Period
	private JPanel periodPanel;
	private JLabel startLabel;
	private JDateChooser startdateChooser;
	private JLabel finalLabel;
	private JDateChooser finaldateChooser;
	private static JButton button;
	private JComboBox<String> periodBox;
	
	
	
	public RoomsGUI() {
		
		roomsPanel= new JPanel();// Creating the panel that contains the components 
		roomsPanel.setLayout(new FlowLayout(FlowLayout.CENTER,1000,15));
		
		
		//Rooms Label Settings and Styling
		roomsLabel= new JLabel("Rooms");
		roomsLabel.setForeground(new Color(000000));
		roomsLabel.setFont(new Font("Arial", Font.BOLD, 25));
		roomsPanel.add(roomsLabel);
		
		//Creating and Styling components for Auditoriums Panel
		
		//The Labels and the TextFields
		audLabel= new JLabel("Auditoriums:");
		audLabel.setForeground(new Color(000000));
		audLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		
		audnumLabel=new JLabel("Numbers");
		audnumLabel.setForeground(new Color(000000));
		audnumLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		
		audcapLabel=new JLabel("Capacity");
		audcapLabel.setForeground(new Color(000000));
		audcapLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		
		audnumText= new JTextField(10);
		audcapText= new JTextField(10);
		
		//Adding the components in the Auditoriums Panel
		audPanel= new JPanel();
		audPanel.setPreferredSize(new Dimension(200,200));
		audPanel.setBackground(Color.LIGHT_GRAY);
		audPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		audPanel.setLayout(new FlowLayout(FlowLayout.CENTER,100,10));
		audPanel.add(audLabel);
		audPanel.add(audnumLabel);
		audPanel.add(audnumText);
		audPanel.add(audcapLabel);
		audPanel.add(audcapText);
		
		//Adding the Auditoriums Panel to Rooms Panel
		roomsPanel.add(audPanel);
		
		//Creating and Styling components for Amphitheaters Panel
		
		//The Labels and the TextFields
		ampLabel= new JLabel("Amphitheaters:");
		ampLabel.setForeground(new Color(000000));
		ampLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		
		ampnumLabel=new JLabel("Numbers");
		ampnumLabel.setForeground(new Color(000000));
		ampnumLabel.setFont(new Font("Arial", Font.PLAIN, 15));

		
		ampcapLabel=new JLabel("Capacity");
		ampcapLabel.setForeground(new Color(000000));
		ampcapLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		
		ampnumText= new JTextField(10);
		ampcapText= new JTextField(10);
		
		//Adding the components in the Amphitheaters Panel
		ampPanel= new JPanel();
		ampPanel.setPreferredSize(new Dimension(200,200));
		ampPanel.setBackground(Color.LIGHT_GRAY);
		ampPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		ampPanel.setLayout(new FlowLayout(FlowLayout.CENTER,100,10));
		ampPanel.add(ampLabel);
		ampPanel.add(ampnumLabel);
		ampPanel.add(ampnumText);
		ampPanel.add(ampcapLabel);
		ampPanel.add(ampcapText);
		
		//Adding the Amphitheaters Panel to Rooms Panel
		roomsPanel.add(ampPanel);
		
		//Creating and Styling the components for Period Panel
		
		periodLabel= new JLabel("Exams Period");
		periodLabel.setForeground(new Color(000000));
		periodLabel.setFont(new Font("Arial", Font.BOLD, 25));
		roomsPanel.add(periodLabel);
		
		periodPanel = new JPanel();
		roomsPanel.add(periodPanel);
		periodPanel.setPreferredSize(new Dimension(250,90));
		periodPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		startLabel = new JLabel("Starting Date");
		startLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		periodPanel.add(startLabel);
		
		startdateChooser = new JDateChooser();
		startdateChooser.setPreferredSize(new Dimension(90,20));
		periodPanel.add(startdateChooser);
		
		finalLabel = new JLabel("Final Date");
		finalLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		periodPanel.add(finalLabel);
		
		finaldateChooser = new JDateChooser();
		finaldateChooser.setPreferredSize(new Dimension(90,20));
		periodPanel.add(finaldateChooser);
		
		periodBox = new JComboBox();
		periodBox.setModel(new DefaultComboBoxModel(new String[] {"Χειμερινή", "Εαρινή", "Επαναληπτική"}));
		periodPanel.add(periodBox);
		
		button = new JButton("Confirm");
		button.setFont(new Font("Arial", Font.PLAIN, 20));
		button.addActionListener(this);
		roomsPanel.add(button);
		
		
		
		getContentPane().add(roomsPanel);
		this.setVisible(true);
		this.setSize(800,800);
		this.setTitle("University Rooms");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageIcon logo= new ImageIcon("logo.png");
		this.setIconImage(logo.getImage());
		
	}
	
	
	public static RoomsGUI getInstance() {
		if(instance) {
			RoomsGUI roomsGUI= new RoomsGUI();
			instance=false;
			return roomsGUI;
		}
		return null;
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==button) {
			
			if(checkTextFieldsAndDates()) {
				
				int period = periodBox.getSelectedIndex();
				int audnum = Integer.parseInt(audnumText.getText());
				int audcap = Integer.parseInt(audcapText.getText());
				int ampnum = Integer.parseInt(ampnumText.getText());
				int ampcap = Integer.parseInt(ampcapText.getText());
				
				//!!!
				//Πρωτον δεν μπορω να κανω ExamScheduler γιατι στον κατασκευαστη εχετε τον πινακα
				//Δευτερον στην Secretary  δεν εχετε βαλει στον κατασκευαστη για να περναω τις ημερομηνιες
				//Χρειαζεται να διορθωθουν αυτα για να μπορω να χρησιμοποιησω την ConvertAndSplitDate της ExamScheduler
				Secretary scheduler= new Secretary(period,audcap,ampcap,audnum,ampnum);
				new InsertCourseGUI(scheduler);
				this.dispose();
			}else {
				JOptionPane.showMessageDialog(null,"All the field are Required!");
			}
			
		}
		
	}
	
	public boolean checkTextFieldsAndDates() {
		
		if(audnumText.getText().equals("")) {
			return (false);
		}else if(audcapText.getText().equals("")) {
			return (false);
		}else if(ampnumText.getText().equals("")) {
			return (false);
		}else if(ampcapText.getText().equals("")) {
			return (false);
		}/*else if(startdateChooser.getDateFormatString().equals("")) {
			return (false);
		}else if(finaldateChooser.getDateFormatString().equals("")) {
			return (false);
		}//Δεν δουλευουν οι συνθηκες!!!
		*/
		
		else {
			System.out.println(startdateChooser.getDateFormatString());
			return(true);
		}
	}

}
