package tp1.p2.control.exceptions;

public class CommandParseException extends GameException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommandParseException(String unknownCommand) {
		// TODO Auto-generated constructor stub
		super(unknownCommand);
	}
	public CommandParseException(Throwable cause) {
		super(cause);
	}

	public CommandParseException(String message, Throwable cause) {
		super(message, cause);
	}

}
