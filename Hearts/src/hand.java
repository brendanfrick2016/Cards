import java.util.*;
public class hand extends groupOfCards{
	
	public deck d;
	public hand(deck d)
	{
		super(d.getHand());
		this.d= d;
		
		sort();
	}
	
	public card underCut(card winner)
	{
		ArrayList<card> mySuit = getSuit(winner.suit);
		card prev = null;
		for(card i: mySuit)
		{
			if(i.num>winner.num)
				return prev;
			prev =i;
		}
			return prev;
	}

}
