package tp1.p2.control.commands;


import tp1.p2.control.Command;
import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.actions.AddAction;
import tp1.p2.logic.actions.GameAction;
import tp1.p2.logic.gameobjects.Zombie;
import tp1.p2.logic.gameobjects.ZombieFactory;
import tp1.p2.view.Messages;

public class AddZombieCommand extends Command implements Cloneable {

	private int col;

	private int row;

	private int zombieIdx;


	public AddZombieCommand() {
		
	}

	public AddZombieCommand(int zombieIdx, int col, int row) {
		this.zombieIdx = zombieIdx;
		this.col = col;
		this.row = row;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_ADD_ZOMBIE_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_ADD_ZOMBIE_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_ADD_ZOMBIE_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_ADD_ZOMBIE_HELP;
	}
	
	@Override
	public boolean execute(GameWorld game) throws GameException{

		try {			
			game.correctPosition(col, row);	
			Zombie zombie = ZombieFactory.spawnZombie(zombieIdx, game, col, row);
			
			GameAction aux = new AddAction(zombie);
			game.pushAction(aux);
			game.update();
				
		}catch(CommandExecuteException cth){
			throw new CommandExecuteException("Execution exception-> " + cth.getMessage(), cth);
			//throw new GameException("texto", cth);
		}   
		return true;
	}

	@Override
	public Command create(String[] parameters) throws GameException{
		
		if	(parameters.length == 4) {
			try {
                zombieIdx = Integer.parseInt(parameters[1]);
                
                try {
					col = Integer.parseInt(parameters[2]);
	                row = Integer.parseInt(parameters[3]);
	                
	            }
	            catch (NumberFormatException e) {
	                throw new CommandParseException(e.getMessage() + ". Only Integer for Zombie position", e);
	            }
                
            }
            catch (NumberFormatException e) {
            	throw new CommandParseException(Messages.INVALID_GAME_OBJECT.formatted(parameters[1], parameters[2]), e);
            }
				
		}
			
		else {
			throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);  
		}
		
		return this;
	}
}
