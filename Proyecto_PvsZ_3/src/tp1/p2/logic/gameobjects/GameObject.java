package tp1.p2.logic.gameobjects;

import static tp1.p2.view.Messages.status;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;

/**
 * Base class for game non playable character in the game.
 *
 */
public abstract class GameObject implements GameItem {

	protected GameWorld game;
	protected int col;
	protected int row;
	protected int hp;
	protected int dmg;


	protected GameObject() {
	}

	protected GameObject(GameWorld game, int col, int row, int endurance, int dmg) {
		this.game = game;
		this.col = col;
		this.row = row;
		this.hp = endurance;
		this.dmg = dmg;
	}

	public boolean isInPosition(int col, int row) {
		
		return this.col == col && this.row == row;
	}

	public int getCol() {
		
		return col;
	}

	public int getRow() {
		
		return row;
	}
	
	abstract public boolean isAlive();

	public String toString() {
		
		if (isAlive()) {
			return status(this.getSymbol(),this.hp);
		}
		
		else {
			return " ";
		}
	}
	
	abstract protected String getSymbol();

	abstract public String getDescription();

	abstract public void update();
	
	abstract public void onEnter();
	
	abstract public void onExit();
}
