
package game.blackjack;
import game.Game;
import game.Table;

import java.awt.Color;
import java.awt.event.KeyEvent;

import player.Player;
import card.Card;
import densan.s.game.drawing.Drawer;
import densan.s.game.input.KeyInput;

/**
 * 実際に順番にブラックジャックを行う部分
 * @author K.M
 *
 */
public class Blackjack implements Game{
	private static Blackjack bj;
	{
		bj=this;
	}
	public static Blackjack getInstance(){
		return bj;
	}
	private Table table;
	private Player player;
	private Player dealer;
	private Command com;
	private String now="bet";
	public Blackjack(){
		table = new Table();
		Table.setDeck();
		player = new Player("プレイヤー");
		dealer = new Dealer("ディーラー");
		com = new Command();
	}
	/**
	 * 引数のプレイヤーの手札の数字の合計値を計算
	 * aceは21を超えない範囲で1か11かより良い方で計算
	 * @param player
	 */
	public int handTotal(Player player){
		int total=0;
		boolean isAce=false;
		for(Card cards : player.getHand()){
			if(cards.getNum()==1)isAce=true;
 			if(cards.getNum()>10) total+=10;
			else total+=cards.getNum();
		}
		if(total<=11&&isAce==true)total+=10;
		return total;
	}
	/**
	 * 引数のプレイヤーの手がバーストかそうでないか判定
	 * @param handCard
	 * @return
	 */
	public boolean burst(Player player) {
		if (handTotal(player) > 21)return true;
		return false;
	}
	/**
	 * ディーラーの手が17以上になるまで引き続ける
	 */
	public void dealerAction(){
		while(dealer.getHand().isEmpty()||handTotal(dealer)<17){
			drawAction(dealer);
		}
	}
	/**
	 * 引数のプレイヤーでカードを引いて表示
	 */
	public void drawAction(Player player){
		player.pullCard();
//		player.showHand();
//		System.out.println("合計値"+handTotal(player));	
//		if(burst(player)) System.out.println("バースト");
	}
	/**
	 * ヒットかステイか選択
	 */
	public void choiceAction(){
		if(handTotal(player)>=21)now="judge";
		if(KeyInput.isPress(KeyEvent.VK_LEFT))com.setChoice("hit");
		if(KeyInput.isPress(KeyEvent.VK_RIGHT))com.setChoice("stand");
		if(KeyInput.isPress(KeyEvent.VK_Z)){
			if(com.getChoice().equals("hit")){
				drawAction(player);
			}
			else if(com.getChoice().equals("stand"))now="judge";
		}
	}

	/**
	 * 21からカードの合計値を引いて小さい方の勝利
	 */
	public void judge() {
		int p = 21 - handTotal(player);
		int d = 21 - handTotal(dealer);
		if (p == d || (burst(player) && burst(dealer))) {
			player.calcTip(player.getBet());
			now = "draw";// 引き分け
		} else if (((!burst(player) && p < d) || (!burst(player) && burst(dealer)))) {
			player.calcTip(player.getBet() * 2);
			now = "win";
		} else if (((!burst(dealer) && p > d) || (burst(player) && !burst(dealer))))
			now = "loss";
	}

	@Override
	public void update() {
		switch (now) {
		case "bet":
			player.choiceBet();
			if (KeyInput.isPress(KeyEvent.VK_Z)) {
				player.calcTip(-player.getBet());
				now = "give";
			}
			break;
		case "give":
			drawAction(dealer);
			drawAction(player);
			drawAction(player);
			now = "choice";
//			System.out.println("Hit or Stand?");
			break;
		case "choice":
			choiceAction();
			break;
		case "judge":
			dealerAction();
			judge();
			break;
		default:
			if (KeyInput.isPress(KeyEvent.VK_Z)) {
				table.resetDeck();
				player.resetHand();
				dealer.resetHand();
				now="bet";
			}
			break;
		}
	}
	@Override
	public void draw(Drawer d){
		table.draw(d);
		table.lineCard((table.FRAMEX*0.3),table.FRAMEY*0.6,player.getHand());
		table.lineCard((table.FRAMEX*0.3),table.FRAMEY*0.1,dealer.getHand());
		//System.out.println("tes");
		table.drawCard(d, dealer.getHand());
		table.drawCard(d, player.getHand());
		d.setColor(Color.BLACK);
		switch (now) {
		case "choice":
			((Dealer) dealer).drawReverseCard(d);
			com.draw(d);
			break;
		case "win":
			d.drawStringCenter("You Win", table.FRAMEX/2, table.FRAMEY/2-10);
			break;
		case "loss":
			d.drawStringCenter("You Loss", table.FRAMEX/2, table.FRAMEY/2-10);
			break;
		case "draw":
			d.drawStringCenter("Draw", table.FRAMEX/2, table.FRAMEY/2-10);
			break;
		}
		player.drawTip(d);
	}
}