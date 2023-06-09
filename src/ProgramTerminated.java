import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ProgramTerminated extends WindowAdapter{
	
	private ExamScheduler ES;
	
	public ProgramTerminated(ExamScheduler ES) {
		
		this.ES = ES;
	}

	public void windowClosing(WindowEvent e) {
		ES.saveToAvailabilityFile(ES);
		ES.saveToCourseFile(ES);
		ES.saveToRoomsFile(ES);
		
		System.exit(0);
	}
}
