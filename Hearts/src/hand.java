import java.util.*;
public class hand extends groupOfCards{
	
	private round r;
	public hand(round r)
	{
		super(r.getDeck().getHand());
		this.r = r;
		sort();
	}
	
	public card underCut(card winner)
	{
		ArrayList<card> mySuit = getSuit(winner.suit);
		System.out.println(mySuit);
		card prev = null;
		for(card i: mySuit)
		{
			if(i.num>winner.num)
				return prev;
			prev =i;
		}
			return null;
	}
	//returns the number of hands you can lose safely in the suit
	public int getSafetyValue(char suit)
	{
		
		int count = 0;
		ArrayList<card> mySuit = getSuit(suit);
		if(mySuit.isEmpty())
			return 0;
		ArrayList<card> allSuit = r.getDeck().getSuit(suit);
		for(int x = 0; x<allSuit.size(); x++)
		{
			for(card c2 :mySuit)
				if(allSuit.get(x).toString().equals(c2.toString()))
					allSuit.remove(x);
				
		}
		System.out.println(mySuit);
		System.out.println(allSuit);
		
		if(allSuit.size()>1&&allSuit.get(1).num>mySuit.get(0).num)
			return 0;
		while(mySuit.size()>0&&allSuit.size()>1&&mySuit.get(0).num<allSuit.get(0).num)
		{
			count++;
			mySuit.remove(0);
			if(allSuit.size()>2)
				allSuit.remove(0);
			
			allSuit.remove(1);
		}
		if(mySuit.size()==0)
			return 100;
		
		return count;
	}

}
