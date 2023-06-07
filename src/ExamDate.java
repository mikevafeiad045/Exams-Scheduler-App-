
public class ExamDate {
	String day;

    private Course[] zones = new Course[6];

    public ExamDate(String d) {
        day=d;
    }

    public void addCourse(Course c,int index) { //Adding course to table zones;
        zones[index] = c;
    }

    public int calcRow(String s) { //Calculating the row of the time period

        String temp=s.substring(0,2);//getting the first 2 chars of the string (exam start hour)
        int start = Integer.parseInt(temp);//converting String to int

        switch(start) {
        case 9:
            return 0;

        case 11: 
            return 1;

        case 13: 
            return 2;

        case 15:
            return 3;

        case 17: 
            return 4;

        case 19: 
            return 5;

        default: 
            return -1;//wrong input
        }

    }

    public void checkAvailibility(Course c, String s) { //Checking and adding if the time zone is not occupied
        int num = calcRow(s);
        if (zones[num]!= null) {
            //GUI OCCUPIED!
        }else addCourse(c,num);

    }

    public Course[] getZone() {
        return zones;
    }


}
