package player;

public class Coins extends Item{

	int amount;
	
	public Coins(int amount) {
		super(0, false, false, false);
		this.amount = amount;
	}
	
	public int getAmount() {return this.amount;}


}
