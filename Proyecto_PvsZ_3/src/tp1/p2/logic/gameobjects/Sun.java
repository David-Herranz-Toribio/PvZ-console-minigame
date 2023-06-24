package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sun extends GameObject {
	
	// Remember that a Sun is updated the very same cycle is added to the container
	public static final int SUN_COOLDOWN = 10;
	public static final int SUNS_GENERATED = 10;
	public static int generatedSuns = 0;
	public static int sunsCatched = 0;
	
	
	protected Sun() {
		
	}
 
	public Sun(GameWorld game, int col, int row) {
		super(game, col, row, SUN_COOLDOWN, 0);
		generatedSuns ++;
	}
	
	
	public boolean isAlive() {
		
		return this.hp > 0;
	}

	public String getDescription() {
		
		return Messages.SUN_DESCRIPTION;
	}
	
	public String getSymbol(){
		
		return Messages.SUN_SYMBOL;
	}
	 
	public void update() {
		
		this.hp--; 
	}
	
	public boolean receiveZombieAttack(int damage) {
		
		return false;
	}
	
	public boolean receivePlantAttack(int damage) {
		
		return false;
	}
	
	public void onEnter() {
	}
	
	public void onExit() {
	}
	
	@Override
	public boolean catchObject() {
		// TODO add your code here
		hp = 0;
		game.addSuns(SUNS_GENERATED);
		sunsCatched++;
		
		return true;
	}

	@Override
	public boolean fillPosition() {
		return true;
	}
}











	

