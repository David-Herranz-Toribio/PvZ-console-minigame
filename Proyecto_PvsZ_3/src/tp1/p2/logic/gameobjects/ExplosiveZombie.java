package tp1.p2.logic.gameobjects;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.actions.ExplosionAction;
import tp1.p2.view.Messages;

public class ExplosiveZombie extends Zombie{
	public static final int EXPLOSIVE_HP = 5;
	public static final int EXPLOSIVE_DMG = 1;
	public static final int EXPLOSIVE_SPEED = 2;
	public static final int EXPLOSION_DMG = 10;


	public ExplosiveZombie() {
		super(null, -1, -1, EXPLOSIVE_HP, EXPLOSIVE_DMG, EXPLOSIVE_SPEED);

	}
	public ExplosiveZombie(int col, int row, GameWorld game) {
		super(game, col, row, EXPLOSIVE_HP, EXPLOSIVE_DMG, EXPLOSIVE_SPEED);
	}
	@Override
	protected String getSymbol() {

		return Messages.EXPLOSIVE_ZOMBIE_SYMBOL;
	}

	public String getDescription() {
		
		return Messages.ZOMBIE_DESCRIPTION.formatted(getName(), this.speed, this.dmg, this.hp);
	}
	
	public String getName(){
		
		return Messages.EXPLOSIVE_ZOMBIE_NAME;
	}

	@Override
	public void onExit() {
		int danio = 10;
		if(this.hp < -1)
			danio = 20;
		
		
		game.addPoints(danio);

		game.zombieDied();
		game.pushAction(new ExplosionAction(this.col,this.row,EXPLOSION_DMG));
		
	}

	@Override
	public Zombie generateZombie(GameWorld game, int col, int row) {

		return new ExplosiveZombie(col, row, game);
	}
	
}
