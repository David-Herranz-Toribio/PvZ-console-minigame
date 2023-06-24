package tp1.p2.control.commands;


import tp1.p2.control.Command;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.Level;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class ResetCommand extends Command {

	private Level level;

	private long seed;

	public ResetCommand() {
	}

	public ResetCommand(Level level, long seed) {
		this.level = level;
		this.seed = seed;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_RESET_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_RESET_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_RESET_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_RESET_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException {
		// TODO add your code here
		if(level != null)
			game.reset(seed, level);
		else
			game.reset();
		
		return true;

	}

	@Override
	public Command create(String[] parameters) throws GameException {
		// TODO add your code here			
		
		if( parameters.length == 3) {
			this.level = Level.valueOfIgnoreCase(parameters[1]);
			
			if(level == null) {
    			throw new CommandParseException(Messages.ALLOWED_LEVELS);     		
			}
			else {
				
				String seedParam = "";			
				try {
					
					seedParam = parameters[2];
					seed = Long.parseLong(seedParam);
					
				} catch (NumberFormatException nfe) {					
	    			throw new CommandParseException(String.format(Messages.SEED_NOT_A_NUMBER_ERROR, seedParam), nfe);     		
				}
			}
		} 
		else if( parameters.length != 1)
			throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);     		
		
		
		return this;			
	}
}
