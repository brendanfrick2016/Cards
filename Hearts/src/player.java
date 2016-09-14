import java.util.ArrayList;

public class player {

	private hand myHand;
	private int myScore;
	private int pointsThisHand;
	private int playerNumber;
	private round currentRound;
	
	public player(int pn, round r)
	{
		playerNumber = pn;
		newRound(r);
	}
	
	public boolean playTwo()
	{
		if(myHand.removeCard(new card(2,'C'))!=null)
		{
			currentRound.playCard(new card(2, 'C'),playerNumber);
			return true;
		}
		return false;
			
	}
	
	public void test()
	{
		System.out.println(myHand.getSafetyValue('H'));
	}
	
	public void newRound(round r)
	{
		currentRound = r;
		myHand = new hand(currentRound);
	}
	
	public void playCard()
	{
		card play = null;
		card winning = currentRound.getCurrentWinner();
		int numPoints = currentRound.getNumPointsInPot();
		int ytp = currentRound.yetToPlay();
		boolean queen = currentRound.getDeck().has(new card(12, 'S'))
				&& !myHand.has(new card(12,'S'));
		int safe = myHand.getSafetyValue(winning.suit);
		
		ArrayList<card> choices = myHand.getSuit(winning.suit);
		int numInHand = choices.size();
		int numInDeck = currentRound.getDeck().getSuit(winning.suit).size()-numInHand;
		card cut = myHand.underCut(winning);
		
		
		
		if(choices.isEmpty())
			play = findWasteCard();
		
		else
		{
			if(safe>Math.min(numInDeck, numInHand))
				if(cut==null)
					play = choices.get(0);
				else
					play = cut;
			
			
				
		}
		
	}
	
	public card findWasteCard()
	{
	return null;	
	}

	public String toString()
	{
		return "Player "+playerNumber+": "+myHand.toString();
	}
}
