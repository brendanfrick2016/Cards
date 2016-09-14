import java.util.*;
public class round {

	private deck d;
	private int roundNumber;
	private ArrayList<card>pot = new ArrayList<card>();
	private card winning;
	private int winner;
	private int numPointsInPot;
	
	public round(int rN)
	{
		roundNumber = rN;
		d= new deck();
	}
	public void replenish()
	{
		d = new deck();
		d.sort();
	}
	public deck getDeck()
	{
		return d;
	}
	
	public int getNumPointsInPot()
	{
		return numPointsInPot;
	}
	
	public card getCurrentWinner()
	{
		return winning;
	}
	public int yetToPlay()
	{
		return 4-pot.size()-1;
	}
	public void playCard(card c, int index)
	{
		index--;
		if(pot.isEmpty())
		{
			winning = c;
			winner = index;
		}
		else if(c.suit== winning.suit&&c.num>winning.num)
		{
			winning = c;
			winner = index;
		}
		
		if(c.suit == 'H')
			numPointsInPot ++;
		else if(c.suit == 'S'&&c.num == 12)
		{
			numPointsInPot+=16;
		}
		d.removeCard(c);
		pot.add(index,c);
	}
}
