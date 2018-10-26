package game.blackjack;

import java.awt.Color;

import densan.s.game.drawing.Drawer;

/**
 * @author K.M
 *
 */
public class Command {
	private String choice="hit";//hitかstandどっちを選択しているか
	public void setChoice(String choice){
		this.choice=choice;
	}
	public String getChoice(){
		return choice;
	}
	public void draw(Drawer d){
		d.setColor(Color.BLUE);
		d.fillRect(100,340, 100, 70);
		d.fillRect(390,340, 170, 70);
		d.setColor(Color.WHITE);
		if(choice.equals("hit"))d.drawRect(100,340, 100, 70);
		else if(choice.equals("stand"))d.drawRect(390,340, 170, 70);
		d.setColor(Color.BLACK);
		d.drawString("Hit", 110,390);
		d.drawString("or", 280, 390);
		d.drawString("Stand", 400, 390);
	}
}
