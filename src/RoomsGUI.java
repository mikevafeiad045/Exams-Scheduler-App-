import javax.swing.JFrame;
import javax.swing.JPanel;

public class RoomsGUI extends JFrame {
	
	private static boolean instance=true;
	private JPanel panel;
	
	private RoomsGUI() {
		
		panel= new JPanel();
		
		this.setContentPane(panel);
		this.setVisible(true);
		this.setSize(400,400);
		this.setTitle("University Rooms");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public static RoomsGUI getInstance() {
		if(instance) {
			RoomsGUI roomsGUI= new RoomsGUI();
			instance=false;
			return roomsGUI;
		}
		return null;
		
	}
	
	

}
