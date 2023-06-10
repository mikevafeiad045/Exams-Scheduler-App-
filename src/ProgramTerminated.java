import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ProgramTerminated extends WindowAdapter{
	
	private ExamScheduler ES;
	
	public ProgramTerminated() {
		
		ES = ExamScheduler.getInstance();
	}

	public void windowClosing(WindowEvent e) {
		ES.saveToAvailabilityFile();
		ES.saveToCourseFile();
		ES.saveToRoomsFile();
		
		System.exit(0);
	}
}
