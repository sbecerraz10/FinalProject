package exception;

public class playerAlreadyExists extends Exception{
	
	public static final String  MESSAGE = "Ya existe un usuario con este nombre";
	
	public playerAlreadyExists(){
		super(MESSAGE);
	}

}
