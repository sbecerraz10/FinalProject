package exception;

public class UserAlreadyExists extends Exception{
	
	public static final String  MESSAGE = "Ya existe un usuario con este nombre";
	
	public UserAlreadyExists(){
		super(MESSAGE);
	}

}
