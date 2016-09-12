import java.util.*;
//win has the sole purpose of deciding whether this player should try to win more or not
//win gives shouldWin a true or a false

public class deckEval {

	//public deck hand;
	public deck cardsPlayed;
	public int oneBid;
	public int twoBid;
	
    public deckEval(int teamB, int oppB) //hand then cards played 
    {
    	//hand = new deck(h.cards);

    	
    	oneBid = teamB; twoBid = oppB; 
    }
    //deck d are the cards left, deck h is the hand of this player, deck cardsPlayed are all the cards that h
    //have been played....hCopy is a copy of the hand so i can remove things
    //auto wins are the # of hands I think this hand can safely win
    public boolean win(deck d, deck h, int oneScore, int twoScore, boolean isTeamOne)
    {
    	deck hCopy = new deck(h.cards);
    	cardsPlayed = new deck(true);
    	for(card c: d.cards)
    	{
    		cardsPlayed.removeCard(c.suit+""+c.num);
    	}
    	int autoWins = 0;
    	int rounds = h.cards.size();
    	while(hCopy.highSuit('S').num>d.highSuit('S').num)
    	{
    		autoWins++;
    		hCopy.removeCard('S'+""+hCopy.highSuit('S'));
    	}
    	int temp = h.getSuit('S').size()*2-d.getSuit('S').size();
    	if(temp>0)
    	{
    		if(autoWins==2&&temp>2)
    			autoWins++;
    		if(autoWins==1)
    			autoWins+=(temp+1)/2;
    		if(autoWins==0)
    			autoWins+= temp;
    			
    		
    	}
    	if(oneScore+autoWins>=oneBid&&isTeamOne&&twoScore+(rounds-autoWins-1)>twoBid)
    		return false;
    	else if(twoScore+autoWins>=twoBid&&!isTeamOne&&oneScore+(rounds-autoWins-1)>oneBid)
    		return false;
    	return true;
    }
    
}