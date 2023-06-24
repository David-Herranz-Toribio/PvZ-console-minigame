package tp1.p2.logic.gameobjects;
import tp1.p2.logic.Game;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Peashooter extends Plant {
	
	public static final int PEASHOOTER_COST = 50;
	public static final int PEASHOOTER_DMG = 1;
	public static final int PEASHOOTER_HP = 3;
	
	public Peashooter() {
		super(null, -1, -1, PEASHOOTER_HP, PEASHOOTER_DMG, PEASHOOTER_COST);
	}
	
	public Peashooter(int col, int row, GameWorld game) {
		super(game, col, row, PEASHOOTER_HP, PEASHOOTER_DMG, PEASHOOTER_COST);
	}
	 
	 
	public Plant generatePlant(GameWorld game, int col, int row) {
		 
		return new Peashooter(col, row, game);
	}

	public String getDescription() {
		
		return Messages.PLANT_DESCRIPTION.formatted(getShortcut(), this.cost, this.dmg, this.hp);
	}
	
	public String getName(){
		
		return Messages.PEASHOOTER_NAME.toLowerCase();
	}

	public String getShortcut(){
		
		return Messages.PEASHOOTER_NAME_SHORTCUT;
	}
	
	public String getSymbol(){
		
		return Messages.PEASHOOTER_SYMBOL;
	}
	 
	public void update() {
		
		boolean hit = false;
		int targetCol = this.col + 1;
		int targetRow  = this.row ;
		
		while (!hit && targetCol < Game.NUM_COLS) {
		
			hit = game.plantAttacks(targetCol, targetRow,this.dmg);
			
			targetCol++;
		}
	}	
}
