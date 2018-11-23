package exception;

public class UserDoesNotExist extends Exception{
	
	public static final String MESSAGE = "El jugador buscado no se encuentra registrado";

	public UserDoesNotExist(){
		super(MESSAGE);
	}
}
