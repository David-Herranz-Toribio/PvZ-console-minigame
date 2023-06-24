package tp1.p2.logic.gameobjects;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sunflower extends Plant{
	
	public static final int SUNFLOWER_COST = 20;
	public static final int SUNFLOWER_DMG = 0;
	public static final int SUNFLOWER_HP = 1;
	public static final int SUN_CYCLES = 4;
	
	private int cyclesAlive;
	
	
	public Sunflower() {
		super(null, -1, -1, SUNFLOWER_HP, SUNFLOWER_DMG, SUNFLOWER_COST);
	}
	
	public Sunflower(int col, int row, GameWorld game) {
		super(game, col, row, SUNFLOWER_HP, SUNFLOWER_DMG, SUNFLOWER_COST);
		cyclesAlive = 0;
	}
	
	
	public Plant generatePlant(GameWorld game, int col, int row) {
		
		return new Sunflower(col, row, game);
	}
	
	public String getDescription() {
		
		return Messages.PLANT_DESCRIPTION.formatted(getShortcut(), this.cost, this.dmg, this.hp);
	}
	
	public String getName(){
		
		return  Messages.SUNFLOWER_NAME.toLowerCase();
	}

	public String getShortcut(){
		
		return Messages.SUNFLOWER_NAME_SHORTCUT;		 
	}
	
	public String getSymbol(){
		
		return Messages.SUNFLOWER_SYMBOL;
	}
	 
	
	public void update() {

		if (cyclesAlive >= SUN_CYCLES) {
			cyclesAlive = 0;
			game.addSun();
		}
		else {
			cyclesAlive++;
		}
		
	}	
}
