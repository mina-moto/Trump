package main;

import game.Game;
import game.blackjack.Blackjack;
import densan.s.game.drawing.Drawer;
import densan.s.game.manager.GameManager;
import densan.s.game.manager.Updatable;
/**
 * 
 * @author K.M
 *
 */
public class Main implements Updatable{
	private static Game nowScene;

	public Main(){
		nowScene = new Blackjack();
	}
	@Override
	public void update() {
		nowScene.update();
	}
	@Override
	public void draw(Drawer d) {
		nowScene.draw(d);
	}
	/**
	 * 
	public static void changeScene(Scene scene) {
		// TODO 自動生成されたメソッド・スタブ
		nowScene = scene;
	}
	**/
	
	public static void main(String[] args) {
		GameManager.getInstance().createFrame(800,800);
		GameManager.getInstance().setUpdatable(new Main());
	}
}
