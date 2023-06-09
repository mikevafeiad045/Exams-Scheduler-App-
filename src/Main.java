
public class Main {
	
	
	public static void main(String[] args) {
		
		
		ExamScheduler ES = new ExamScheduler(10,10,10,10,10,"05-05-23","17-05-23");
		
		
		Course c = new Course("Algorithms",100);
		
		//new ScheduleExamGUI(ES,c);
		Secretary S= new Secretary(10,10,10,10,10,"05-05-23","17-05-23");
		//new RoomsGUI(ES);
		new SecretaryGUI2(S, ES);
		
		//new LoginGUI();
		//new ScheduleExamGUI(ES, c);
		
		new InsertCourseGUI(S, ES);
	}

}
