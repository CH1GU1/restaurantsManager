package exceptions;

@SuppressWarnings("serial")
public class WrongIdException extends Exception{

	public WrongIdException() {
		super("The ID client does not exist!");
	}

}
