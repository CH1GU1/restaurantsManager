package exceptions;

@SuppressWarnings("serial")
public class NullCodeException extends Exception{

	public NullCodeException() {
		super("Code does not exists!");
	}

}
