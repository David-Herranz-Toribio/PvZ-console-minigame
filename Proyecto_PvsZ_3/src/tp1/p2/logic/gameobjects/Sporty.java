package tp1.p2.logic.gameobjects;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sporty extends Zombie{
	public static final int SPORTY_HP = 5;
	public static final int SPORTY_DMG = 1;
	public static final int SPORTY_SPEED = 1;

	public Sporty() {
		super(null, -1, -1, SPORTY_HP, SPORTY_DMG, SPORTY_SPEED);

	}
	public Sporty(int col, int row, GameWorld game) {
		super(game, col, row, SPORTY_HP, SPORTY_DMG, SPORTY_SPEED);
	}
	
	@Override
	protected String getSymbol() {

		return Messages.SPORTY_ZOMBIE_SYMBOL;
	}

	public String getDescription() {
		
		return Messages.ZOMBIE_DESCRIPTION.formatted(getName(), this.speed, this.dmg, this.hp);
	}
	
	public String getName(){
		
		return Messages.SPORTY_ZOMBIE_NAME;
	}

	@Override
	public Zombie generateZombie(GameWorld game, int col, int row) {
		
		return new Sporty(col, row, game);
	}

}
