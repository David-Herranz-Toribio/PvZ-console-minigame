package tp1.p2.logic;

import tp1.p2.control.Level;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.Level;
import tp1.p2.logic.actions.GameAction;
import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.logic.gameobjects.Plant;


public interface GameWorld {

	public static final int NUM_ROWS = 4;

	public static final int NUM_COLS = 8;

	public String getLevel();
	public int levelfromtext(String level);
	// TODO add your code here
	public void playerQuits();
	public void zombiesWin();

	
	public void addGameObject(GameObject gameobject);
	public boolean plantAttacks(int col, int row, int dmg);
	public boolean enoughSuncoins(Plant plant) throws GameException;
	public boolean correctPosition(int col, int row) throws GameException;
	
	public void zombieAttacks(int col, int row, int dmg);

    public void reset(long seed, Level level) throws GameException;
    public void reset() throws GameException;
    public void zombieDied();

	
    void update() throws GameException;
    
    
  	void addSun();

  	boolean tryToCatchObject(int col, int row) throws GameException;
	
	public void pushAction(GameAction aux);
	
	boolean addItem(GameObject gameObject);
	public void addSuns(int sunsGenerated);
	public boolean isValidPosition(int col, int row);
	public void addPoints(int points);
	
}
