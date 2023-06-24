package tp1.p2.logic.gameobjects;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class CherryBomb extends Plant{
	
	public static final int CHERRY_COST = 50;
	public static final int CHERRY_DMG = 10;
	public static final int CHERRY_HP = 2;
	public static final int CHERRY_CYCLES = 2;
	private int cyclesAlive;
	
	
	public CherryBomb() {
		super(null, -1, -1, CHERRY_HP, CHERRY_DMG, CHERRY_COST);
	}
	
	public CherryBomb(int col, int row, GameWorld game) {
		super(game, col, row, CHERRY_HP, CHERRY_DMG, CHERRY_COST);
		cyclesAlive = 0;
	}
	
	
	public Plant generatePlant(GameWorld game, int col, int row) {
		
		return new CherryBomb(col, row, game);
	}
	
	public String getDescription() {
		
		return Messages.PLANT_DESCRIPTION.formatted(getShortcut(), this.cost, this.dmg, this.hp);
	}
	
	public String getName(){
		
		return  Messages.CHERRY_BOMB_NAME.toLowerCase();
	}

	public String getShortcut(){
		
		return Messages.CHERRY_BOMB_NAME_SHORTCUT;		 
	}
	
	public String getSymbol(){
		if (cyclesAlive == 1) {
			return Messages.CHERRY_BOMB_SYMBOL;
		}
		else
			return Messages.CHERRY_BOMB_SYMBOL.toUpperCase();
	}
	
	public void update() {
		if (cyclesAlive >= CHERRY_CYCLES) {
			cyclesAlive = 0;
			this.hp = 0;
			
			BOOM();
	
		}
		else {
			cyclesAlive++;
		}
	}
	
	private void BOOM() {
		int colAux = col - 1;
		int rowAux = row - 1; 
		
		if(row < 0)
			rowAux++;
		if(col < 0)
			colAux++;
		
		while(colAux < col + 2 &&  colAux != GameWorld.NUM_COLS) {

			while(rowAux < row + 2 && rowAux != GameWorld.NUM_ROWS) {
				
				game.plantAttacks(colAux, rowAux, this.dmg);
				
				rowAux++;
				
			}
			rowAux = row - 1;
			colAux++;

		}
	}	
}
