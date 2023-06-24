package tp1.p2.logic;

import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.InvalidPositionException;
import tp1.p2.control.exceptions.NotCatchablePositionException;
import tp1.p2.control.exceptions.NotEnoughCoinsException;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Random;

import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.view.Messages;
import tp1.p2.logic.actions.AddAction;
import tp1.p2.logic.actions.GameAction;
import tp1.p2.control.Level;
import tp1.p2.control.Command;

public class Game implements GameStatus, GameWorld{
	
	public static final int INITIAL_SUNCOINS = 50;
	public static final int NUM_ROWS = 4;
	public static final int NUM_COLS = 8;

    private boolean playerQuits;
    private boolean zombiesWin;
    private ArrayDeque<GameAction> actions; 
    
    private int puntuacion;

    private int suncoins;
    private int cycles;
    private GameObjectContainer gameObjectContainer;
    private ZombiesManager zombiesManager;
    private SunsManager sunsManager;
    private Record record;
	private Level level;
	private Random rand;


	public Game(long seed, Level level) throws GameException {
    	this.level = level;
    	this.rand = new Random(seed);
    	reset();
	}
    
    public void reset()  throws GameException{
     	playerQuits = false;
     	zombiesWin = false;
		record = new Record(Messages.RECORD_FILENAME);

     	record.cargar();
    	suncoins = INITIAL_SUNCOINS;
    	cycles = 0;
    	zombiesManager = new ZombiesManager(this,level, rand);
    	this.gameObjectContainer = new GameObjectContainer();
    	this.puntuacion = 0;
    	this.sunsManager = new SunsManager(this, this.rand);
    	this.cycles = 0;
		this.actions = new ArrayDeque<>();
    }
    
    	
    public void reset(long seed, Level level)  throws GameException{
    	this.level = level;
    	this.rand = new Random(seed);
    	reset();
    }
    
    public boolean isFinished() {
    	
    	return zombiesManager.zombiesLoose() || doZombiesWin();
    }
    
	public boolean doZombiesWin() {
		
		return zombiesWin;
	}
    
    public boolean isPlayerQuits() {
    	
    	return playerQuits;
    }
    
    public boolean execute(Command command)throws GameException {
    	
    	command.execute(this);
 
    	return true;    	
    }
     
    public boolean correctPosition(int col, int row) throws CommandExecuteException {
    	if (!(this.gameObjectContainer.correctPosition(col, row) && col <= NUM_COLS && row < NUM_ROWS))
            throw new InvalidPositionException(Messages.INVALID_POSITION.formatted(col, row));
    	
    	return true;
    }
    public boolean isValidPosition(int col, int row) {
    	
    	return (this.gameObjectContainer.correctPosition(col, row) && col < NUM_COLS && row < NUM_ROWS);
    }
    public boolean enoughSuncoins(Plant plant) throws GameException {
    	
    	int plantCost =  plant.getCost();
    	boolean enough = this.suncoins >= plantCost;
    	
    	if (enough) {
    		suncoins -= plantCost;
    	}
    	else
    		throw new NotEnoughCoinsException(Messages.NOT_ENOUGH_COINS);
    	
    	return enough;
    }
    
    public void addGameObject(GameObject gameobject) {
    	
    	this.gameObjectContainer.addGameObject(gameobject);
    }
    
    
    public int getCycle(){
    	
    	return cycles;
    }
    
    public int getSuncoins() {
    	
    	return suncoins;
    }
    
    public void playerQuits(){
    	
    	playerQuits = true;
    }
    
    public void zombiesWin(){
    	
    	zombiesWin = true;
    }
    
    public void update()  throws GameException {
    	// 1. Execute pending actions
		executePendingActions();

		// 2. Execute game Actions
		// TODO add your code here
    	zombiesManager.addZombie();
    	
    	if(cycles%10 == 9)
    		addSun();
		// 3. Game object updates
		// TODO add your code here
    	gameObjectContainer.update();

		// 4. & 5. Remove dead and execute pending actions
		boolean deadRemoved = true;
		while (deadRemoved || areTherePendingActions()) {
			// 4. Remove dead
			deadRemoved = this.gameObjectContainer.removeDead();

			// 5. execute pending  
			executePendingActions();
		}

		this.cycles++;

		// 6. Notify commands that a new cycle started
		Command.newCycle();
		
		// 7. Update record
		// TODO your code here
    	
    }
        
    private void executePendingActions() {
    	
		while (!this.actions.isEmpty()) {
			GameAction action = this.actions.removeLast();
			action.execute(this);
		}
	}

	private boolean areTherePendingActions() {
		
		return this.actions.size() > 0;
	}
    
	public void pushAction(GameAction gameAction) {		
		
		this.actions.addLast(gameAction);
	}
	
	
    public boolean plantAttacks(int col, int row, int dmg) {
    	boolean ok = false;
    	GameItem g = gameObjectContainer.ItemForU( col,  row);
		if(g != null)
			ok = g.receivePlantAttack(dmg);
    	
    	return ok;
		  
    }
    
    public String positionToString(int x, int y) {

        StringBuilder pos_toS = new StringBuilder();
        pos_toS.append(gameObjectContainer.toString(x, y));  

        return pos_toS.toString();
    }
    
	public void zombieAttacks(int col, int row, int dmg) {
    	GameItem g = gameObjectContainer.ItemForU( col,  row);

		if(g != null)
			g.receiveZombieAttack(dmg);
	}

    public void addSuns(int suns){
    	
    	this.suncoins += suns; 
    }
    
    public int getRemainingZombies(){
    	
    	return zombiesManager.getRemainingZombies();
    }
    
	public void zombieDied() {
		
		zombiesManager.reduceZombie();
	}

	@Override
	public boolean tryToCatchObject(int col, int row) throws GameException{
		
		boolean ok ;
		GameItem g =  gameObjectContainer.ItemForU(col, row);
		if(g == null)
			ok = false;
		else
			ok = g.catchObject();
		
		if(!ok)
			throw new NotCatchablePositionException(Messages.NO_CATCHABLE_IN_POSITION.formatted(col, row));

		return ok;
	}

	@Override
	public int getGeneratedSuns() {

		return sunsManager.getGeneratedSuns();
	}

	@Override
	public int getCaughtSuns() {

		return sunsManager.getCatchedSuns();
	}
	
	public void addSun() {
		sunsManager.addSun();
	}
	public int getPuntuation() {

		return this.puntuacion;
	}
	public String getLevel() {

		return level.toString();
	}
	@Override
	public boolean addItem(GameObject gameObject) {
		pushAction(new AddAction(gameObject));
		
		return true;
	}

	public void guardar() throws GameException {
		// TODO Auto-generated method stub
		record.guardar(this.puntuacion, level.toString());
		
	}

	@Override
	public void addPoints(int points) {
		
		this.puntuacion += points;
	}
	public int levelfromtext(String level){
		
		return	record.gettextlevel(level);
	}
}
