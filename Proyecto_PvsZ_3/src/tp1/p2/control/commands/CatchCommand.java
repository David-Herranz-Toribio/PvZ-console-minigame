package tp1.p2.control.commands;


import tp1.p2.control.Command;
import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class CatchCommand extends Command {

	private static boolean caughtSunThisCycle = false;

	private int col;

	private int row;

	public CatchCommand() {
		caughtSunThisCycle = false;
	}
	
	@Override
	protected void newCycleStarted() {
		caughtSunThisCycle = false;
	}


	@Override
	protected String getName() {
		return Messages.COMMAND_CATCH_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_CATCH_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_CATCH_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_CATCH_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException {
		// TODO add your code here
		try {
		caughtSunThisCycle = game.tryToCatchObject(col, row);
		}catch(CommandExecuteException cth){
			throw new CommandExecuteException("Execution exception-> " + cth.getMessage(), cth);
			//throw new GameException("texto", cth);
		}   
		
		return true;
	}

	@Override
	public Command create(String[] parameters) throws GameException {
		// TODO add your code here
		if(!caughtSunThisCycle) {	
			if	(parameters.length == 3) {
				try {
					col = Integer.parseInt(parameters[1]);
	                row = Integer.parseInt(parameters[2]);
	               
	               
	            }
	            catch (NumberFormatException e) {
	                throw new CommandParseException(Messages.INVALID_POSITION.formatted(parameters[1], parameters[2]), e);
	            }
			}				
			else {
				throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);  
			}
		}else
			throw new CommandParseException(Messages.SUN_ALREADY_CAUGHT); 

		
		 return this;
	}
	
}
