import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ProgramTerminated extends WindowAdapter{
	
	private ExamScheduler ES;
	
	public ProgramTerminated(/*ExamScheduler ES*/) {
		
		ES = ExamScheduler.getInstance();
		
		//this.ES = ES;
	}

	public void windowClosing(WindowEvent e) {
		ES.saveToAvailabilityFile();
		ES.saveToCourseFile();
		ES.saveToRoomsFile();
		
		System.exit(0);
	}
}
