package card;
import densan.s.game.drawing.Drawer;
import densan.s.game.object.ImageObjectBase;

/**
 * @author K.M
 *
 */
public class Card extends ImageObjectBase{
	protected String mark;
	private String imageName;
	private int num;
	/**
	 * 座標を設定する
	 * コンストラクタ
	 * 引数(カードの数字,x座標,y座標
	 * ,カードのマーク,カードの画像の名前)
	 * @param x
	 * @param y
	 * @param 
	 * @param imageName
	 */
	public Card(int num,double x,double y,String mark,String imageName){
		super(x, y, imageName);
		this.num=num;
		this.imageName=imageName;
		this.mark = mark;
	}
	
	/**
	 * 座標を設定しない
	 * コンストラクタ
	 * 引数(数字,カードのマーク,カードの画像の名前)
	 * @param mark
	 * @param imageName
	 */
	public Card(int num,String mark,String imageName){
		super(0, 0, imageName);
		this.num=num;
		this.imageName=imageName;
		this.mark = mark;
	}
	/**
	 * カードのマークを返す
	 * @return mark
	 */
	public String getMark(){
		return mark;
	}
	
	/**
	 * カードの画像名返す
	 * @return
	 */
	public String getImageName(){
		return imageName;
	}
	@Override
	public void update() {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	/**
	 * カードの数字返す
	 * @return
	 */
	public int getNum(){
		return num;
	}
	/**
	 * 何のカードか返す
	 * @return
	 */
	public String getCardType(){
		return mark+"の"+num;
	}
	public void draw(Drawer g){
		super.draw(g);
	}
}
