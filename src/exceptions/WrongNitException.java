package exceptions;

@SuppressWarnings("serial")
public class WrongNitException extends Exception {

	public WrongNitException() {
		super("Restaurant Nit does not exist!");
	}

}
