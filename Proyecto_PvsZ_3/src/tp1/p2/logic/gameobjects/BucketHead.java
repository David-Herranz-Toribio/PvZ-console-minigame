package tp1.p2.logic.gameobjects;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class BucketHead extends Zombie{
	public static final int BUCKET_HEAD_HP = 8;
	public static final int BUCKET_HEAD_DMG = 1;
	public static final int BUCKET_HEAD_SPEED = 4;

	public BucketHead() {
		super(null, -1, -1, BUCKET_HEAD_HP, BUCKET_HEAD_DMG, BUCKET_HEAD_SPEED);

	}
	public BucketHead(int col, int row, GameWorld game) {
		super(game, col, row, BUCKET_HEAD_HP, BUCKET_HEAD_DMG, BUCKET_HEAD_SPEED);
	}
	@Override
	protected String getSymbol() {

		return Messages.BUCKET_HEAD_ZOMBIE_SYMBOL;
	}

	public String getDescription() {
		
		return Messages.ZOMBIE_DESCRIPTION.formatted(getName(), this.speed, this.dmg, this.hp);
	}
	
	public String getName(){
		
		return Messages.BUCKET_HEAD_ZOMBIE_NAME;
	}

	@Override
	public Zombie generateZombie(GameWorld game, int col, int row) {

		return new BucketHead(col, row, game);
	}


}
