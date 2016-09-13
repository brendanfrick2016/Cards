
import java.util.*;
public class deck {
		
		public  ArrayList <card> cards;
		public char avoidSuit;
		public char supportSuit;
	
    	public deck(boolean aceHigh)
    	{
    		System.out.println("HELLO");
    		avoidSuit = ' ';
    		supportSuit = ' ';
    		
    		cards = new ArrayList <card>();
    		 if(aceHigh)
    		{
    			for(int i =2; i<15; i++)
	    			cards.add(new card(i,'S',aceHigh));
	    		for(int i =2; i<15; i++)
	    			cards.add(new card(i,'C',aceHigh));
	    		for(int i =2; i<15; i++)
	    			cards.add(new card(i,'H',aceHigh));
	    		for(int i =2; i<15; i++)
	    			cards.add(new card(i,'D',aceHigh));
    		}
    		else
	    	{
	    		for(int i =1; i<14; i++)
	    			cards.add(new card(i,'S',aceHigh));
	    		for(int i =1; i<14; i++)
	    			cards.add(new card(i,'C',aceHigh));
	    		for(int i =1; i<14; i++)
	    			cards.add(new card(i,'H',aceHigh));
	    		for(int i =1; i<14; i++)
	    			cards.add(new card(i,'D',aceHigh));
	    	}	    			
  
    	}
    	public deck(ArrayList<card> al)
    	{
    		cards = new ArrayList<card>(al);
    	}
    	public void sort()
    	{
    		boolean sorted = false;
    		while(!sorted)
    		{
    			sorted = true;
	    		for(int i = 0; i<cards.size()-1; i++)
	    		{
	    			if(cards.get(i).num>cards.get(i+1).num)
	    			{
	    				card temp = cards.get(i);
	    				cards.set(i,cards.get(i+1));
	    				cards.set(i+1, temp);
	    				sorted = false;
	    			}
	    		}
    		}
    	}
    	public void shuffle()
    	{
    		for(int x = 5000; x>0; x--)
    		{
    			int r = (int)(Math.random()*(52));
    			int r2 = (int)(Math.random()*(52));
    			card c = cards.get(r);
    			cards.set(r, cards.get(r2));
    			cards.set(r2, c);
    		}
    	}
    	public card removeCard(card c )
    	{
    	 	return cards.remove(cards.indexOf(c));
    	}
    	public card removeCard(String str)
    	{
    		for(int i =0; i<cards.size(); i++)
    		{
    			if(cards.get(i).toString().equals(str))
    				return cards.remove(i);
    		}
    	 	return null;
    	}
    	//returns the highest card of this suit from the hand
    	public card highSuit(char c)
    	{
    		sort();
    		ArrayList<card> al = this.getSuit(c);
    		if(al.isEmpty())
    			return new card(0,c,true);
    		return al.get(al.size()-1);
    	}
    	//dont really know the purpose of this
    	public card highSuit(char c, deck d)
    	{
    		ArrayList<card> al = d.getSuit(c);
    		for(card p:this.getSuit(c))
    			for(int a = 0; a<al.size(); a++)
    				if(p.num == al.get(a).num)
    					al.remove(a);
    		deck temp = new deck(al);
    	//	System.out.print(temp);
    		return temp.highSuit(c);
    	
    	}
    	//finds the lowest card of a suit, used with the method, waste card
    	public card lowSuit(char c)
    	{
    		ArrayList<card> al = this.getSuit(c);
    		if(al.isEmpty())
    			return wasteCard(false);
    		return al.get(0);
    	}
    	//finds a good card to lead from the lead hand
    	public card leadCard(boolean spadesBroken, deck d)
    	{
    		
    		if(!spadesBroken)
    		{	    			
	    		for(int i = cards.size()-1; i>=0; i--)
	    			if(cards.get(i).suit!='S')
	    			{
	    				//System.out.print(this.highSuit(cards.get(i).suit,d).num+" " + cards.get(i)+" hey ");
	    				if(cards.get(i).num>=this.highSuit(cards.get(i).suit,d).num)
	    					return cards.get(i);
	    			}
    		}	
	    	else 
	    	{
	    		for(int i = cards.size()-1; i>=0; i--)
	    			if(cards.get(i).num>=this.highSuit(cards.get(i).suit,d).num)
	    				return cards.get(i);
	    	}
	    	if(supportSuit!=' ')
	    		return this.lowSuit(supportSuit);
	    	return this.wasteCard(true);
    			
    	}
    	//used for the last player to beat the previous highest card by the minimum amout possible to conserve their good cards
    	public card next(card winner, card leadCard)
    	{    
    		int n = winner.num;
    		char c = leadCard.suit;
    		ArrayList<card> al = this.getSuit(c);
    		for(int i = 0; i<al.size(); i++)
    			if(al.get(i).num>n)
    				return al.get(i);
    			if(!al.isEmpty())
    				return al.get(0);
    				
    			if(winner.suit == 'S')
    			{
    				al = this.getSuit('S');
    				for(int i = 0; i<al.size(); i++)
    					if(al.get(i).num>n)
    						return al.get(i);
    			}
    					return this.lowSuit('S');
    			
    	}
    	//finds least valuable card
    	public card wasteCard(boolean lead)
    	{
    		card small = cards.get(0);
    		for(int i = cards.size()-1; i>=0; i--)
    			if(cards.get(i).suit!='S'&&(cards.get(i).suit!=avoidSuit||!lead))
    				small = cards.get(i);
    				
    		if(small == null)
    			return cards.get(0);
	    			return small;
    	}
    	//returns arrayList of suit of that deck
    	public ArrayList<card> getSuit(char c)
    	{
    		ArrayList<card> temp = new ArrayList<card>();
    		for(card a:cards)
    			if(a.suit == c)
    				temp.add(a);
    		return temp;
    	}
    	//runs hand in a simpler version of spadeSim 1000 times
    	public int getBid()
    	{
    		int trials = 1000;
    		int tot = 0;
    		for(int q = 0; q<trials; q++)
    		{
    			deck temp = new deck(cards);
    		int [] scores = new int [4];
	    	deck d = new deck(true);
	    	for(card c : cards)
	    		d.removeCard(c.suit+""+c.num);
	    	d.shuffle();
	    	ArrayList<card> al = new ArrayList<card>(d.cards);
	       	ArrayList <deck> hands = new ArrayList<deck>();
	       		hands.add(temp);
	 		for(int i =1; i<4; i++)
	 		{
	    		hands.add(new deck(new ArrayList<card>(al.subList(i*13,(i+1)*13))));
	    		hands.get(i).sort();
	 		}

	 		d = new deck(true);
	 		hands.add(this);
	    	
	    	//spades aren't broken and leadplayer is player 1
	    	boolean spadesBroken = false;
	    	int leadPlayer = 0;
	    	
	    	//beggining of gameplay
	    	for(int i = 0; i<13; i++)
	    	{
	    		//finds the best lead card
	    		card leadCard = hands.get(leadPlayer).removeCard(hands.get(leadPlayer).leadCard(spadesBroken,d));
	    		int player = leadPlayer;
	    		//pot is an aL of cards that have been played in this trick
	    		ArrayList <card> pot = new ArrayList< card>();
	    		pot.add(null); pot.add(null); pot.add(null); pot.add(null);
	    		pot.set(leadPlayer,leadCard);
	    		//winner is the current winner
	    		card winner = leadCard;
	    		//winInd is for score later and for teamates leading hands
	    		int winInd = leadPlayer;
	    		//for the remaining three players
	    		for(int r = 0; r<3; r++)
	    		{
	    			//plays card played at the end
	    			card played = null;
	    			//goes to next player
	    			player = (player+1)%4;
	    			//current players hand
	    			deck currentHand = hands.get(player);
	    			//finds highest card in players hand of lead suit
	    			card high = currentHand.highSuit(leadCard.suit);
	    			//teamate is true if teamate is the current winner
	    			boolean teamate = (player+2)%4==winInd;
	    			//true if current player has none of that suit
	    			if(high.num==0)
	    			{
	    				hands.get((player+1)%4).avoidSuit = leadCard.suit; 
	    				hands.get((player+3)%4).avoidSuit = leadCard.suit; 
	    				hands.get((player+2)%4).supportSuit = leadCard.suit; 
	    				//true if this is the last player in the trick
	    				if(r==3) 
	    				{
	    					//if teamate is winning current player wastes a card
	    					if(teamate)
	    						played = currentHand.wasteCard(false);
	    					//deck will win the hand if possible or else it will waste a card
	    					else 
	    					{
	    						played = currentHand.next(winner,leadCard);
	    						//cant trump on first hand
	    						if(played.suit=='S'&&i==0)
	    							played = currentHand.wasteCard(false);
	    					}
	    				}
	    				else
	    				{
	    					//if teamate is winning or player has no spades or its 1st hand he wastes a card
	    					if(currentHand.highSuit('S')==null||teamate||i==0)
	    						played = currentHand.wasteCard(false);
	    					//otherwise he trumps the minimum	
	    					else
	    						played = currentHand.next(winner,leadCard);
	    					
	    				}
	    			}
	    			//if player has that suit
	    			else
	    			{
	    				//if current player can not beat current winner he wastes a card
	    				if(winner.suit == 'S'||high.num<winner.num)
	    					played = currentHand.lowSuit(leadCard.suit);
	    				//if he can beat the current leader and the current leader is not his teamate he will play his high card
	    				else if(!teamate)
	    					played = high;
	    				//if its the last hand and the teamates winning, player will throw
	    				else if(r==3)
	    					played = currentHand.lowSuit(leadCard.suit);
	    				//if the teamates winner is one less than current player than the current player will waste
	    				else if(winner.num+1==high.num)
	    					played = currentHand.lowSuit(leadCard.suit);
	    				//if there is a higher winner remaining the player will waste
	    				else if(high.num<d.highSuit(leadCard.suit,hands.get(player)).num)
	    					played = currentHand.lowSuit(leadCard.suit);
	    				//if else the player wins the hand
	    				else
	    					played = currentHand.highSuit(leadCard.suit);
	    				
	    					
	    			}
	    			//plays the chosen played card
	    			d.removeCard(played.suit+ "" + played.num);
	    			pot.set(player,played);
	    			currentHand.removeCard(played);
	    			
	    			//Sets winner and winInd to current card if it is winning
	    			if(played.suit == winner.suit && played.num>winner.num)
	    			{
	    					winner = played;
	    					winInd = player;
	    			}
	    			else if(winner.suit == 'S')
	    				if(played.suit == 'S'&&played.num>winner.num)
	    				{
	    					winner = played;
	    					winInd = player;
	    				}
	    			if(played.suit=='S'&&winner.suit!= 'S')
	    			{
	    					winner = played;
	    					winInd = player;
	    			}
	    		}
	    			
					//player who won the last trick leads
	    			leadPlayer = winInd;
	    			//increments score
	    			scores[winInd] ++;
	    		
				}
				tot+=scores[0];
				hands.clear();
			}
			return tot/trials;
    	}
    	//prints all the cards
    	public String toString()
    	{
    		String s = "";
    		for(card a:cards)
    			s+=a.toString()+" ";
    			
    		return s;
    	}
    
    }