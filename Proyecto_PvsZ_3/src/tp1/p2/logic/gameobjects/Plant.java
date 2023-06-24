package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public abstract class Plant extends GameObject {
	
	protected int cost;
	
	protected Plant() {
		
	}
	
	public Plant(GameWorld game, int col, int row, int endurance, int dmg, int cost) {
		super(game, col, row, endurance, dmg);
		this.cost = cost;
	}
	
	public abstract Plant generatePlant(GameWorld game, int col, int row);
	
	public boolean isAlive() {
		
		return this.hp > 0;
	}
	
	abstract protected String getName();

	abstract protected String getShortcut();
	
	abstract public String getDescription();
	
	public int getCost() {
		
		return this.cost;
	}
	
	public void update() {
		//nada
	}
	
	public boolean receiveZombieAttack(int damage) {
		
		this.hp-= damage;
		return true;
	}
	public boolean receivePlantAttack(int damage) {
		
		return false;
	}
	
	@Override
	public boolean fillPosition() {

		return false;
	}

	
	@Override
	public boolean catchObject() {

		return false;
	}
	public void onEnter() {
		//nada
	}
	
	public void onExit() {
		//nada
	}
}
