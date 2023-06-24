package tp1.p2.logic.gameobjects;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Common extends Zombie{
	public static final int COMMON_HP = 5;
	public static final int COMMON_DMG = 1;
	public static final int COMMON_SPEED = 2;

	public Common() {
		super(null, -1, -1, COMMON_HP, COMMON_DMG, COMMON_SPEED);

	}
	public Common(int col, int row, GameWorld game) {
		super(game, col, row, COMMON_HP, COMMON_DMG, COMMON_SPEED);
	}
	@Override
	protected String getSymbol() {

		return Messages.ZOMBIE_SYMBOL;
	}

	public String getDescription() {
		
		return Messages.ZOMBIE_DESCRIPTION.formatted(getName(), this.speed, this.dmg, this.hp);
	}
	
	public String getName(){
		
		return Messages.ZOMBIE_NAME;
	}

	@Override
	public Zombie generateZombie(GameWorld game, int col, int row) {

		return new Common(col, row, game);
	}
	

}
