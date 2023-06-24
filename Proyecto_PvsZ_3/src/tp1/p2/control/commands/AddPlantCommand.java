package tp1.p2.control.commands;


import tp1.p2.control.Command;
import tp1.p2.logic.GameWorld;
import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.logic.gameobjects.PlantFactory;
import tp1.p2.view.Messages;

public class AddPlantCommand extends Command implements Cloneable {

	private int col;

	private int row;

	private String plantName;

	private boolean consumeCoins;

	public AddPlantCommand() {
		this(true);
	}

	public AddPlantCommand(boolean consumeCoins) {
		this.consumeCoins = consumeCoins;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_ADD_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_ADD_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_ADD_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_ADD_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException{

		try {
			
			game.correctPosition(col, row);			
			Plant plant = PlantFactory.spawnPlant(this.plantName, game, col, row);

			if(consumeCoins){
				game.enoughSuncoins(plant);
				game.addItem(plant);
				game.update();						
			}
			else{
				game.addGameObject(plant);
				game.update();
			}
			
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
				col = Integer.parseInt(parameters[2]);
                row = Integer.parseInt(parameters[3]);
                plantName = parameters[1];
               
            }
            catch (NumberFormatException e) {  
                throw new CommandParseException(e.getMessage() + ". Only Integer for Plant Position", e);
              //  Messages.INVALID_POSITION.formatted(parameters[1], parameters[2])
            }
			
		}			
		else {
			throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);  
		}
		
		 return this;
	}
}
