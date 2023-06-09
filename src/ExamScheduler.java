import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ExamScheduler extends Secretary{
	
	static final int EXAM_ZONES = 6;
	private ArrayList<ExamDate> dates = new ArrayList<>();

	
	 public ExamScheduler(int period, int capacityAud, int capacityAmph, int numberOfAud, 
			 int numberOfAmph, String startDate, String endDate) {
		 
		super(period, capacityAud, capacityAmph, numberOfAud, numberOfAmph,startDate,endDate);
	}
	 
	 
	 public Course[] findDate(String day) { //Searching the date and if it isn't in the list dates adding it.
	    	for(ExamDate d: dates) {
	    		if (d.day.equals(day)) {//Date found
	    			return d.getZone();
	    		}
	    	}
	    	//Not found, Adding it to dates
	    	ExamDate selectedDate = new ExamDate(day);
	    	dates.add(selectedDate);
	    	return selectedDate.getZone();
	    }

	public int calcRemainingStudents(int students, int capacity) {
		
		int remainingStudents = students-capacity;
		if(remainingStudents <=0) return 0;
		return remainingStudents;
		 
	}
	
	
	public int [] SplitDate(String outputDate) {
		//Converts input date from date chooser to simple date format
		//Returns array of size 3, containing the day, month and year int values
		
		//SimpleDateFormat inputFormat = new SimpleDateFormat ("EEE MMMM dd HH:mm:ss zzzz yyyy");
		//SimpleDateFormat inputFormat = new SimpleDateFormat ("MMM dd, yyyy");
		//SimpleDateFormat outputFormat = new SimpleDateFormat ("dd-MM-yy");
		
		int parts[] = new int[3];
		
		 try {
	            
	            String[] temp = outputDate.split("-");
	            //Convert parts (string) to int
	            parts[0] = Integer.parseInt(temp[0]);
	            parts[1] = Integer.parseInt(temp[1]);
	            parts[2] = Integer.parseInt(temp[2]);
	            
	            
	     } catch (Exception e) {
	            e.printStackTrace();
	            System.exit(1);
	     }
		 
		 return parts;
		 
	}
	
	public String ConvertDate(String inputDate) {
		//Converts input date from date chooser to simple date format
		//Returns array of size 3, containing the day, month and year int values
		
		//SimpleDateFormat inputFormat = new SimpleDateFormat ("EEE MMMM dd HH:mm:ss zzzz yyyy");
		SimpleDateFormat inputFormat = new SimpleDateFormat ("MMM dd, yyyy");
		SimpleDateFormat outputFormat = new SimpleDateFormat ("dd-MM-yy");
		
		Date date=null;
		try {
			 date = inputFormat.parse(inputDate);
		}catch(Exception e) {
			e.printStackTrace();
		}
	    
	    String outputDate = outputFormat.format(date);
	    
	    return outputDate; 
    
	}


	public ArrayList<ExamDate> getDates() {
		return dates;
	}
	

	public void saveToAvailabilityFile(ExamScheduler e)
	{		
        ArrayList<ExamDate> tempDates = e.getDates();
        
        try 
        {			       	
        	FileOutputStream fileOut = new FileOutputStream("dates.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(tempDates);
			out.close();
		    fileOut.close();			
		}		
		catch(IOException i) 
        {
			i.printStackTrace();
		} 
	}
	
	public void openAvailabilityFile()
	{
		try 
		{			
			FileInputStream fileIn = new FileInputStream("dates.ser");			
			ObjectInputStream in = new ObjectInputStream(fileIn);			
				
			ArrayList<ExamDate> inDates = (ArrayList<ExamDate>)in.readObject();
				
			fileIn.close();
			in.close();
				
			for(ExamDate d: inDates)
			{
				dates.add(d);
			}
		}
		catch(FileNotFoundException e) 
		{			
			e.printStackTrace();
		}
		catch(IOException e) 
		{				
			e.printStackTrace();			
		} 
		catch (ClassNotFoundException e) 
		{				
			e.printStackTrace();
		}
	}
	
	public void saveToRoomsFile(Secretary s)
	{   		
        ArrayList<Room> tempRooms = s.getRoomList();
        
        try 
        {			       	
        	FileOutputStream fileOut = new FileOutputStream("rooms.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(tempRooms);
			out.close();
		    fileOut.close();			
		}		
		catch(IOException i) 
        {
			i.printStackTrace();
		} 
	}
	
	public void openRoomsFile(Secretary s)
	{		
		try 
		{			
			FileInputStream fileIn = new FileInputStream("rooms.ser");			
			ObjectInputStream in = new ObjectInputStream(fileIn);			
				
			ArrayList<Room> outRooms = (ArrayList<Room>)in.readObject();
				
			fileIn.close();
			in.close();
				
			for(Room r: outRooms)
			{
				s.addRoom(r);
			}
		}
		catch(FileNotFoundException e) 
		{			
			e.printStackTrace();
		}
		catch(IOException e) 
		{				
			e.printStackTrace();			
		} 
		catch (ClassNotFoundException e) 
		{				
			e.printStackTrace();
		}					
	}
		
	public void saveToCourseFile(Secretary s) 
	{		
		ArrayList<Course> tempCourses = s.getCourseList();
        
        try {
			if(s.getPeriod()==0)
			{        	
        	    FileOutputStream fileOut = new FileOutputStream("xeimerino.ser");
			    ObjectOutputStream out = new ObjectOutputStream(fileOut);
			    out.writeObject(tempCourses);
			    out.close();
			    fileOut.close();			
			}
			else if(s.getPeriod()==1)
			{
				FileOutputStream fileOut = new FileOutputStream("earino.ser");
			    ObjectOutputStream out = new ObjectOutputStream(fileOut);
			    out.writeObject(tempCourses);
			    out.close();
			    fileOut.close();
			}
		}
		catch(IOException i) {
			i.printStackTrace();
		}          
	}
	
	public void openFromCourseFile(Secretary s) 
	{        
		try 
		{
			if(s.getPeriod()==0 || s.getPeriod()==2)
			{
				FileInputStream fileIn = new FileInputStream("xeimerino.ser");			
				ObjectInputStream in = new ObjectInputStream(fileIn);			
				
				ArrayList<Course> outCourses = (ArrayList<Course>)in.readObject();
				
				fileIn.close();
				in.close();
				
				for(Course c: outCourses)
				{
					s.addCourse(c);
				}
			}
			if(s.getPeriod()==1 || s.getPeriod()==2)
			{
				FileInputStream fileIn = new FileInputStream("earino.ser");			
				ObjectInputStream in = new ObjectInputStream(fileIn);			
				
				ArrayList<Course> outCourses = (ArrayList<Course>)in.readObject();
				
				fileIn.close();
				in.close();
				
				for(Course c: outCourses)
				{
					s.addCourse(c);
				}
			}					
		}		 
		catch(FileNotFoundException e) 
		{			
			e.printStackTrace();
		}
		catch(IOException e) 
		{				
			e.printStackTrace();			
		} 
		catch (ClassNotFoundException e) 
		{				
			e.printStackTrace();
		}
    }	
}
