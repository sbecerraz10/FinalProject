package exception;

public class FieldNotChoosen extends Exception {

	public static final String MESSAGE = "No ha seleccionado un campo";
	
	public FieldNotChoosen() {
		super(MESSAGE);
	}
}
