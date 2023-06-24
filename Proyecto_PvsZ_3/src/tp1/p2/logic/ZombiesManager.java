package tp1.p2.logic;

import java.util.Random;
import tp1.p2.control.exceptions.GameException;

import tp1.p2.control.Level;
import tp1.p2.logic.gameobjects.ZombieFactory;

/**
 * Manage zombies in the game.
 *
 */
public class ZombiesManager {

	private GameWorld game;

	private Level level;

	private Random rand;

	private int remainingZombies;
	
	private int zombiesAlive;

	public ZombiesManager(GameWorld game, Level level, Random rand) {
		this.game = game;
		this.level = level;
		this.rand = rand;
		this.remainingZombies = level.getNumberOfZombies();
		this.zombiesAlive = 0;
	}

	/**
	 * Checks if the game should add (if possible) a zombie to the game.
	 * 
	 * @return <code>true</code> if a zombie should be added to the game.
	 */
	private boolean shouldAddZombie() {
		return rand.nextDouble() < level.getZombieFrequency();
	}

	/**
	 * Return a random row within the board limits.
	 * 
	 * @return a random row.
	 */
	private int randomZombieRow() {
		return rand.nextInt(GameWorld.NUM_ROWS);
	}

	private int randomZombieType() {
		return rand.nextInt(ZombieFactory.getAvailableZombies().size());
	}

	public void update()  throws GameException  {
		addZombie();
	}

	public boolean addZombie() throws GameException {
		int row = randomZombieRow();
		return addZombie(row);
	}

	public boolean addZombie(int row)  throws GameException {
		boolean canAdd = getRemainingZombies() > 0 && shouldAddZombie() && game.correctPosition(GameWorld.NUM_COLS, row);
		int zombieType = randomZombieType();

		if (canAdd) {
			// TODO add your code here
			zombiesAlive++;
			remainingZombies--;
			game.addGameObject(ZombieFactory.spawnZombie(zombieType, game, GameWorld.NUM_COLS, row));
		}
		return canAdd;		
	}
	public void reduceZombie() {
		
		zombiesAlive--;
	}
	public int getRemainingZombies() {
		
		return this.remainingZombies;
	}

	public boolean zombiesLoose() {
		
		return zombiesAlive == 0 && remainingZombies == 0;
	}

}
