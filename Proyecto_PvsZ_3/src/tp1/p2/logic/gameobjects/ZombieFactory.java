package tp1.p2.logic.gameobjects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.GameException;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class ZombieFactory {

	/* @formatter:off */
	private static final List<Zombie> ZOMBIE_LIST = Arrays.asList(
		new Common(),
		new BucketHead(),
		new Sporty(),
		new ExplosiveZombie()
	);
	/* @formatter:on */
	
	public static boolean isValidZombie(int zombieIdx) {
		return zombieIdx >= 0 && zombieIdx < ZOMBIE_LIST.size();
	}
	public static List<Zombie> getAvailableZombies() {
		
		return Collections.unmodifiableList(ZOMBIE_LIST);
	}
	
	public static Zombie spawnZombie(int zombieType, GameWorld game, int col, int row) throws GameException {
		if(!isValidZombie(zombieType))
			throw new CommandExecuteException(Messages.INVALID_GAME_OBJECT);
		
		return	ZOMBIE_LIST.get(zombieType).generateZombie(game, col, row);
	}
	/*
	 * Avoid creating instances of this class
	 */
	private ZombieFactory() {
	}


}
