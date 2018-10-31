/**
 * 
 */
package player;

import game.Table;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import card.Card;
import densan.s.game.drawing.Drawer;
import densan.s.game.image.ImageLoader;
import densan.s.game.input.KeyInput;

/**
 * プレイヤーの行動定義
 * @author K.M
 *
 */
public class Player extends Table{
	private ArrayList<Card> handCard 
	= new ArrayList<Card>(10);
	private String name;//プレイヤー名
	private int tip=100;//所持チップ
	private int bet = 10;//いくら賭けているか
	Image betCommand = ImageLoader.load("image/BET.png");
	Image tipCommand = ImageLoader.load("image/TIP.png");
	public Player(String name){
		this.name = name;
	}
	/**
	 * カードを引く(drawだと描画と被る)
	 */
	public void pullCard(){
		handCard.add(deck.get(0));
		handCard.get(0).setCenterPos(FRAMEX*0.2,FRAMEY*0.7);
		deck.remove(0);
	}
	/**
	 * いくら賭けるか
	 */
	public void choiceBet(){
		//System.out.println("所持チップは"+tip+"です.いくら賭けますか?");
		if(KeyInput.isPressing(KeyEvent.VK_UP)){
			if(tip>bet)bet++;
		}
		else if(KeyInput.isPressing(KeyEvent.VK_DOWN)){
			if(bet>0)bet--;
		}
		//System.out.println(bet+"チップ賭けました");
	}
	/**
	 * 引数のチップを所持チップに加算
	 */
	public void calcTip(int pay){
		tip+=pay;
	}
	/**
	 * 手札表示(文字)
	 */
	public void showHand(){
//		System.out.print(name+"の手札:");
//		for(Card cards : handCard){
//			System.out.printf(cards.getCardType()+",");
//		}
	}
	/**
	 * 手札リセット
	 */
	public void resetHand(){
		handCard.clear();
	}
	/**
	 * 名前返す
	 * @return
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * betのセット
	 */
	public void setBet(int bet){
		this.bet=bet;
	}
	/**
	 *掛け金を返す 
	 */
	public int getBet(){
		return bet;
	}
	/**
	 * 所持チップを返す
	 */
	public int getTip(){
		return tip;
	}
	/**
	 * 手札をリストで返す
	 * @return
	 */
	public  ArrayList<Card> getHand(){
		return handCard;
	}
	/**
	 * チップ描画
	 * @param d
	 */
	public void drawTip(Drawer d){
		d.drawImage(tipCommand, FRAMEX*0.62,FRAMEY*0.65);
		d.drawImage(betCommand, FRAMEX*0.72,FRAMEY*0.75);
		d.setColor(Color.yellow);
		d.setColor(Color.BLACK);
		d.setFontSize(50);
		d.drawString(""+tip,FRAMEX*0.85,FRAMEY*0.81);
		d.drawString(""+bet,FRAMEX*0.85,FRAMEY*0.90);
	}
}
