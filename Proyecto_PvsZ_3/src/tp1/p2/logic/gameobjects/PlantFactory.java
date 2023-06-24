package tp1.p2.logic.gameobjects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class PlantFactory {

	private static final List<Plant> AVAILABLE_PLANTS = Arrays.asList(
		new Sunflower(),
		new Peashooter(),
		new WallNut(),
		new CherryBomb()
		);


	public static Plant spawnPlant(String plantName, GameWorld game, int col, int row)  throws GameException{
		int i = 0;

		Plant p = null;
		boolean ok = false;
		
		while(i < AVAILABLE_PLANTS.size() && !ok)  {
			
			p = AVAILABLE_PLANTS.get(i);
			if (plantName.equals(p.getName()) || plantName.equals(p.getSymbol().toLowerCase())) {
				p = p.generatePlant(game, col, row);
				ok = true;				
			}
			else			
				i++;
		}
		if(!ok)
			throw new CommandExecuteException(Messages.INVALID_GAME_OBJECT);

		
		return p;
		
	}

	public static List<Plant> getAvailablePlants() {
		
		return Collections.unmodifiableList(AVAILABLE_PLANTS);
	}

	private PlantFactory() {
	}
}
