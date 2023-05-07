
public class LogIn {
	
	public LogIn()
	{
		
	}
	
	public int checkUserProperty(String password) {
		if(password.equals("$3cReT4rY")) {
			return 1;
		}else if(password.equals("pR0!3$$oR")) {
			return 2;
		}else {//WRONG PASSWORD
			return 0;
		}
	}
}
