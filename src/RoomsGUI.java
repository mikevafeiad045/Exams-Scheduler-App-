import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

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

/**
 * GUI class for the insertion of rooms(amphitheaters/auditoriums), their capacity, the exam start/end date and exam period.
 * Contains a "Confirm" button listener and a checkTextFieldsAndDates to ensure all fields have been filled out.
 *
 * @version 17 Jun 2023
 * @author Eleni Loula, Michalis Vafeiadis
 */
public class RoomsGUI extends JFrame implements ActionListener {

	private static RoomsGUI instance;

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
	private ExamScheduler ES;


	private RoomsGUI() {


		ES = ExamScheduler.getInstance();

		ES.openAvailabilityFile();
		ES.openRoomsFile();
		ES.openFromCourseFile();

		roomsPanel= new JPanel();
		roomsPanel.setLayout(new FlowLayout(FlowLayout.CENTER,1050,15));

		JLabel newExamPeriod = new JLabel("Create new Exam Period");
		newExamPeriod.setFont(new Font("Arial", Font.PLAIN, 27));
		roomsPanel.add(newExamPeriod);


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

		periodBox = new JComboBox<String>();
		periodBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Χειμερινή", "Εαρινή", "Επαναληπτική"}));
		periodPanel.add(periodBox);

		button = new JButton("Confirm");
		button.setBackground(new Color(153, 255, 153));
		button.setFont(new Font("Arial", Font.PLAIN, 20));
		button.addActionListener(this);
		roomsPanel.add(button);

		getContentPane().add(roomsPanel);

		addWindowListener(new ProgramTerminated());

		this.setSize(700,800);
		this.setTitle("Create new Exam Period");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImageIcon logo= new ImageIcon(getClass().getResource("/resources/logo.png"));
		this.setIconImage(logo.getImage());
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	/**
	 * Returns the singleton instance of RoomsGUI.
	 *
	 * @return the singleton instance
	 */
	public static RoomsGUI getInstance() {
		if(instance == null) {
			instance = new RoomsGUI();
		}
		return instance;
	}

	/**
	 * Called when an action event is generated by the Confirm button.
	 * This method handles the action event and checks if all the fields have been filled out. Depending on the result of the check,
	 * the input data gets used to set the properties of the already created (Singleton) Exam Scheduler Object, or an error message pops up,
	 * requiring that all fields are filled out.
	 * 
	 * @param e the action event that triggered the method
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==button) {

			if(checkTextFieldsAndDates()) {

				int period = periodBox.getSelectedIndex();
				int audnum = Integer.parseInt(audnumText.getText());
				int audcap = Integer.parseInt(audcapText.getText());
				int ampnum = Integer.parseInt(ampnumText.getText());
				int ampcap = Integer.parseInt(ampcapText.getText());


				Date tempStart = startdateChooser.getDate();
				String convertedStartDate = ES.ConvertDate(tempStart);

				Date tempFinal = finaldateChooser.getDate();
				String convertedFinalDate = ES.ConvertDate(tempFinal);

				ES.setPeriod(period-1);
				ES.setNumberOfAud(audnum);
				ES.setCapacityAud(audcap);
				ES.setNumberOfAmph(ampnum);
				ES.setCapacityAmph(ampcap);
				ES.setStartDate(convertedStartDate);
				ES.setEndDate(convertedFinalDate);


				//Creating auto generated code-names for rooms, depending on their quantity:
				for(int i=0; i<ampnum; i++) {
					Room room = new Room("AMP"+String.valueOf(i+1),1);
					ES.addRoom(room);
				}

				for(int i=0; i<audnum; i++) {
					Room room = new Room("AUD"+String.valueOf(i+1),0);
					ES.addRoom(room);
				}

				if(period != 2) {//If the exam period is not 2(=September), add courses
					new InsertCourseGUI();
				}else {//If exam period is September, new courses cannot be added - return to Login window.
					LoginGUI.getInstance().setVisible(true);
				}

				this.dispose();
			}else {
				JOptionPane.showMessageDialog(null,"All the fields are Required!");
			}

		}

	}

	/**
	 * Method for checking if all fields have been filled out
	 * 
	 * @return false if at least one field is empty, true if all fields are filled out
	 */
	public boolean checkTextFieldsAndDates() {

		if(audnumText.getText().equals("") 
				|| audcapText.getText().equals("")
				|| ampnumText.getText().equals("")
				|| ampcapText.getText().equals("")
				|| startdateChooser.getDate()==null
				|| finaldateChooser.getDate()==null
				) 
		{
			return false;

		}else {

			return true;
		}
	}
}
