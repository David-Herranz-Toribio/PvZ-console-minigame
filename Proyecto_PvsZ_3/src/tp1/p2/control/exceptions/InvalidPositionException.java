package tp1.p2.control.exceptions;

public class InvalidPositionException extends CommandExecuteException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidPositionException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public InvalidPositionException(Throwable cause) {
		super(cause);
	}

	public InvalidPositionException(String message, Throwable cause) {
		super(message, cause);
	}

}
