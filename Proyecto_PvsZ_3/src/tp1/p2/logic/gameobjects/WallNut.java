package tp1.p2.logic.gameobjects;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class WallNut extends Plant{
	
	public static final int NUT_COST = 50;
	public static final int NUT_DMG = 0;
	public static final int NUT_HP = 10;
	
	
	public WallNut() {
		super(null, -1, -1, NUT_HP, NUT_DMG, NUT_COST);
	}
	
	public WallNut(int col, int row, GameWorld game) {
		super(game, col, row, NUT_HP, NUT_DMG, NUT_COST);
	}
	
	
	public Plant generatePlant(GameWorld game, int col, int row) {
		
		return new WallNut(col, row, game);
	}
	
	public String getDescription() {
		
		return Messages.PLANT_DESCRIPTION.formatted(getShortcut(), this.cost, this.dmg, this.hp);
	}
	
	public String getName(){
		
		return  Messages.WALL_NUT_NAME.toLowerCase();
	}

	public String getShortcut(){
		
		return Messages.WALL_NUT_NAME_SHORTCUT;		 
	}
	
	public String getSymbol(){
		
		return Messages.WALL_NUT_SYMBOL;
	}
}
