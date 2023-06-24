package tp1.p2.logic.gameobjects;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;
public abstract class Zombie extends GameObject {
	protected int speed;
	protected int cyclesAlive;

	public Zombie(GameWorld game, int col, int row, int endurance, int dmg, int speed) {
		super(game, col, row, endurance, dmg);
		this.speed = speed;
		cyclesAlive = 0;
	}
	public boolean receiveZombieAttack(int damage) {

		return false;
	}
	public boolean receivePlantAttack(int damage) {
		
		this.hp-= damage;
		return true;
	}

	public boolean isAlive() {

		return this.hp > 0;
	}
	public abstract Zombie generateZombie(GameWorld game, int col, int row);

	protected abstract String getSymbol();

	public abstract String getDescription();

	@Override
	public void update() {

		if(!move())
			game.zombieAttacks(this.col-1,this.row,this.dmg);
		else
			if(col < 0)
				game.zombiesWin();
			
	}

	public void onEnter() {
		//nada
		
	}
	
	public void onExit() {
		int danio = 10;
		if(this.hp < -1)
			danio = 20;
		
		game.addPoints(danio);
		game.zombieDied();
	}
	
	protected boolean move() {
		
		boolean canMove = false;
		
		if(game.isValidPosition(this.col - 1, this.row) && this.speed == cyclesAlive ) {
			this.col--;
			canMove = true;
			cyclesAlive = 0;					
		}else
			cyclesAlive++;
	
		return canMove;
	}
	
	@Override
	public boolean catchObject() {

		return false;
	}
	@Override
	public boolean fillPosition() {

		return false;
	}
}