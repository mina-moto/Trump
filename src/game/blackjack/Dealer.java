/**
 * 
 */
package game.blackjack;
import java.awt.Image;

import player.Player;
import densan.s.game.drawing.Drawer;
import densan.s.game.image.ImageLoader;

/**
 * @author K.M
 *
 */
public class Dealer extends Player{
	Image rCard = ImageLoader.load("image/z01.png");//背景
	/**
	 * @param name
	 */
	public Dealer(String name) {
		super(name);
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	/**
	 * 裏返しのカードを描画
	 * @param d
	 * @param cards
	 */
	public void drawReverseCard(Drawer d){
		d.drawScaleImage(rCard,(FRAMEX*0.3)+40,FRAMEY*0.1, 0.7);
	}
}
