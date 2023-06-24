package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.logic.gameobjects.PlantFactory;
import tp1.p2.view.Messages;

public class ShowRecordCommand extends Command {

	@Override
	protected String getName() {
		return Messages.COMMAND_SHOW_RECORD_NAME;
	}
	@Override
	protected String getShortcut() {
		return Messages.COMMAND_SHOW_RECORD_SHORTCUT;
	}
	@Override
	public String getDetails() {
		return Messages.COMMAND_SHOW_RECORD_DETAILS;
	}
	@Override
	public String getHelp() {
		return Messages.COMMAND_SHOW_RECORD_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException {

		// TODO add your code here HACER VERGAS
		/* @formatter:on */
			System.out.println(game.getLevel() + " record is " +  game.levelfromtext(game.getLevel()));
			System.out.println(" ");
		

 		return false;
	}

}
