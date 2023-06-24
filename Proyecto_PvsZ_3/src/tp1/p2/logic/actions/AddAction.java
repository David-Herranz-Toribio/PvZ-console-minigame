package tp1.p2.logic.actions;

import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.GameObject;

public class AddAction implements GameAction {

	private GameObject gobj;
	public AddAction(GameObject g) {
		gobj = g;
	}

	@Override
	public void execute(GameWorld game) {
		// TODO add your code here
		game.addGameObject(gobj);
	}


}