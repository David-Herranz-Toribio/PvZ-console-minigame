package tp1.p2.logic;

import java.util.ArrayList;
import java.util.List;
import tp1.p2.view.Messages;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.gameobjects.GameObject;

public class GameObjectContainer {

	public List<GameObject> gameObjects;

	public GameObjectContainer() {
		gameObjects = new ArrayList<>();
	}
	
	public boolean correctPosition(int col, int row){
		int i = 0;
		GameItem gameobject;
		boolean ok = true;
		
		while(i < gameObjects.size() && ok)  {
			
			gameobject = gameObjects.get(i);
			
			if(gameobject.isInPosition(col, row)) {
				if(!gameobject.fillPosition())
					ok = false;	
			}
					
				i++;
		}
		
		return ok;
		
	}
	
	public void addGameObject(GameObject gameobject) {
		
		gameObjects.add(gameobject);
		gameobject.onEnter();
	}
		
	public String toString(int col, int row) {
		
		StringBuilder buffer = new StringBuilder();
		boolean sunPainted = false;
		boolean sunAboutToPaint = false;

		for (GameObject g : gameObjects) {
			
			if(g.isAlive() && g.isInPosition(col, row)) {
				
				String objectText = g.toString();
				sunAboutToPaint = objectText.indexOf(Messages.SUN_SYMBOL) >= 0;
				
				if (sunAboutToPaint) {
					
					if (!sunPainted) {
						buffer.append(objectText);
						sunPainted = true;
					}
				} else {
					buffer.append(objectText);
				}
			}
		}

		return buffer.toString();
	}

	public GameItem ItemForU(int col, int row){
		int i = 0;
		GameItem gameobject = null;
		boolean ok = false;
		
		while(i < gameObjects.size() && !ok)  {
			
			gameobject = gameObjects.get(i);
			if(gameobject.isInPosition(col, row)){
				ok = true;				
			}
			else			
				i++;
		}
		if(!ok)
			gameobject = null;
		
		return gameobject;
	}

	public void update(){
		
		for(GameItem gameobject : gameObjects) {

			if(gameobject.isAlive())
				gameobject.update();							
		}
	}
	
	public boolean removeDead() {
		int i = 0;
		GameObject gameobject;
		boolean ok = false;
		
		while(i < gameObjects.size() && !ok)  {
			
			gameobject = gameObjects.get(i);
			if(!gameobject.isAlive()) {
				gameObjects.remove(gameobject);
				gameobject.onExit();
				ok = true;
			}
			else			
				i++;
		}
		
		return ok;
	}
}
