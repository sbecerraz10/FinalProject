package exception;

public class NicknameNotValid extends Exception {
	
	public final static String MESSAGE= "Error en el nickname, debe contener al menos 4 caracteres";
	
	public NicknameNotValid() {
		super(MESSAGE);
	}

}
