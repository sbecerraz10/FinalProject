package exception;

public class CharacterNotChoosen extends Exception{

	public static final String MESSAGE = "No ha seleccionado un personaje";
	
	public CharacterNotChoosen() {
		super(MESSAGE);
	}
}
