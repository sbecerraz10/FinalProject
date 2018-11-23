package exception;

public class CharacterDoesNotExist extends Exception {
	
	public static final String MESSAGE = "El personaje buscado no existe";

	public CharacterDoesNotExist() {
		super(MESSAGE);
	}
}
