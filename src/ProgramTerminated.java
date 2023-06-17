import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * A custom program termination class extending WindowAdapter abstract class
 * Applied to gui windows when data needs to be saved when "X" is selected, before termination
 * 
 * @version 17 Jun 2023
 * @author Michalis Vafeiadis
 */
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
