package tp1.p2.control.exceptions;

public class RecordException extends GameException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecordException(String unknownCommand) {
		// TODO Auto-generated constructor stub
		super(unknownCommand);
	}
	public RecordException(Throwable cause) {
		super(cause);
	}

	public RecordException(String message, Throwable cause) {
		super(message, cause);
	}

}
