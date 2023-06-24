package tp1.p2.logic.actions;

import tp1.p2.logic.GameWorld;

public class ExplosionAction implements GameAction {

	private int col;

	private int row;

	private int damage;
	
	public ExplosionAction(int col, int row, int damage) {
		this.col = col;
		this.row = row;
		this.damage = damage;
	}

	@Override
	public void execute(GameWorld game) {
		int colAux = col - 1;
		int rowAux = row - 1; 
		
		if(row < 0)
			rowAux++;
		if(col < 0)
			colAux++;
		
		while(colAux < col + 2 &&  colAux != GameWorld.NUM_COLS) {

			while(rowAux < row + 2 && rowAux != GameWorld.NUM_ROWS) {
				
				game.zombieAttacks(colAux, rowAux, damage);
				
				rowAux++;
				
			}
			rowAux = row - 1;
			colAux++;

		}
	}
}