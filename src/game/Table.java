package game;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;

import card.Card;
import densan.s.game.drawing.Drawer;
import densan.s.game.image.ImageLoader;
import densan.s.game.manager.GameManager;

/**
 * テーブル上のカードの動き
 * @author K.M
 *
 */
public class Table {
	public final double FRAMEX = GameManager.getInstance().getFrameWidth();//フレームのX座標
	public final double FRAMEY = GameManager.getInstance().getFrameHeight();//フレームのY座標
	protected static ArrayList<Card> deck 
	= new ArrayList<Card>(52);
	Image bg = ImageLoader.load("image/Green.png");//背景
	/**
	 * トランプカード一組セット後シャッフル
	 */
	public static void setDeck(){
		for(int i=1;i<=13;i++){
			deck.add(new Card(i, "ダイヤ", "image/d0"+i+".png"));
			deck.add(new Card(i, "スペード","image/s0"+i+".png"));
			deck.add(new Card(i, "ハート", "image/h0"+i+".png"));
			deck.add(new Card(i, "クローバー","image/c0"+i+".png"));
		}
		Collections.shuffle(deck);
	}
	/**
	 * デッキを破棄し新しくセット
	 */
	public void resetDeck(){
		deck.clear();
		setDeck();
	}
	/**
	 * 第二引数のカードのリストを描画
	 * @param d
	 * @param cards
	 */
	public void drawCard(Drawer d,ArrayList<Card> cards){
		for(Card card:cards){
			card.draw(d);
		}
	}
	/**
	 * 位置x,yから第3引数のカードのリストを並べる
	 * @param player
	 */
	public void lineCard(double x,double y,ArrayList<Card> cards){
		for(int i=0;i<cards.size();i++){
			//System.out.println("tes");
			cards.get(i).setScale(0.7);
			cards.get(i).setPos(x+(40*i),y);
		}
	}
	/**
	 * 背景描画
	 * @param d
	 */
	public void draw(Drawer d){
		d.drawScaleImage(bg, 0, 0, 3.5);
	}
}
