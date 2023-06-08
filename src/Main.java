
public class Main {
	
	
	public static void main(String[] args) {
		
		
		ExamScheduler ES = new ExamScheduler(10,10,10,10,10,"05-05-23","17-05-23");
		
		Course c = new Course("Algorithms",100);
		
		
		
		//new ScheduleExamGUI(ES,selectedCourse);
		
		//new RoomsGUI(ES);
		//new SecretaryGUI2(ES);
		
		//new LoginGUI();
		//new ScheduleExamGUI(ES, c);
		Secretary scheduler= new Secretary(10,10,10,10,10,"05-05-23","17-05-23");
		new InsertCourseGUI(scheduler, ES);
		//new CalendarGUI(ES);
	}
	


}
