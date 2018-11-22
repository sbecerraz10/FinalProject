package exception;

public class PlayerDoesNotExist extends Exception{
	
	public static final String MESSAGE = "El jugador buscado no se encuentra registrado";

	public PlayerDoesNotExist(){
		super(MESSAGE);
	}
}
