import java.util.*;
/*Variables Key
 * scores[] : holds the # of hands won by every individual player
 * bids[] : Holds the bid of every individual player
 * deck d : the main deck, cards are removed as they are played so it is only cards that are in hands
 * pot: an aL of cards that have been played in this trick
 * al: unimportant but dont delete it
 * hands: an array list of decks that are each players hands. cards are removed as theyre played
 * boolean spadesbroken: self-explanatory
 * boolean shouldWin : uses deckEval to decide whether or not we should try to win more hands
 * dE: is the deck evaluator...I recognize that this couldve been a class in spadesSim but i dont care
 * leadPlayer: index of the player who won the last hand
 * leadCard: card that was lead, use this card to find leadSuit
 * Card winner: is the current winning card in the pot
 * int winInd: is the index of the current winner for scoring later
 * r: The number of cards that have been played before you in that pot
 * i: number of pots that have been played
 * int player: index of current player
 * deck currentHand: the hand of the player
 * card played: is the card that the program selected for the current player to play
 * card high: the highest card of the lead suit. if value is zero than they have no cards in that suit
 * Other minor stuff
 * 
 */
public class SpadeSim3 {

    public static void main(String [] args) 
    {		
    		
			//Array of tricks won for each player
    		int [] scores = new int [4];
    		int [] bids = new int [4];
    		
    		//creates a generic deck of cards and shuffles
	    	deck d = new deck(true);
	    	d.shuffle();
	    	
	    	//turns that deck of cards into a simple arrayList of cards
	    	ArrayList<card> al = new ArrayList<card>(d.cards);
	    	
	    	//ArrayList of 4 random hands made by deck D and sorts hands by number
	       	ArrayList <deck> hands = new ArrayList<deck>();
	    		
	 		for(int i =0; i<4; i++)
	 		{
	    		hands.add(new deck(new ArrayList<card>(al.subList(i*13,(i+1)*13))));
	    		hands.get(i).sort();
	    		bids[i] = hands.get(i).getBid();
	    		System.out.println("Player "+(i+1)+": " +bids[i]);
	 		}
	 		System.out.println("Team 1 bid: "+(bids[0]+bids[2])+"  Team 2 bid: "+(bids[1]+bids[3]));
	 		deckEval dE = new deckEval(bids[0]+bids[2],bids[1]+bids[3]);
	    	// prints out the hands
	    	for(deck a:hands)
	    		System.out.println(a);
	   
	    	
	    	//spades aren't broken and leadplayer is player 1
	    	boolean spadesBroken = false;
	    	int leadPlayer = 0;
	    	
	    	//beggining of gameplay
	    	for(int i = 0; i<13; i++)
	    	{
	    		boolean shouldWin = dE.win(d,hands.get(leadPlayer),scores[0] +scores[2], scores[1]+scores[3],leadPlayer%2==0);
	    		card leadCard = null;
	    		if(!shouldWin)
	    			leadCard = hands.get(leadPlayer).removeCard(hands.get(leadPlayer).wasteCard(true));
	    		//finds the best lead card
	    		else
	    			leadCard = hands.get(leadPlayer).removeCard(hands.get(leadPlayer).leadCard(spadesBroken,d));
	    			
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
	    			
	    			shouldWin = dE.win(d,currentHand,scores[0] +scores[2], scores[1]+scores[3],player%2==0);
//Insert scenarios for Should win vs should lose	    			
	    			//true if current player has none of that suit
	    			if(shouldWin)
	    			{
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
		    		}
	    			else
	    			{
	    				played= currentHand.highSuit('S');
	    				if(played.num == 0)
	    				{
	    					card op1 = currentHand.highSuit('C');
	    					card op2 = currentHand.highSuit('H');
	    					card op3 = currentHand.highSuit('D');
	    					if(op1.num>op2.num&&op1.num>op3.num)
	    						played = op1;
	    					else if(op2.num>op3.num)
	    						played = op2;
	    					else
	    						played = op3;
	    				}
	    			}
	    			//plays the chosen played card
	    			d.removeCard(played);
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
	    		System.out.print(pot);
	    		System.out.println("Player " +(winInd +1)+" Won the Hand");	
	    		System.out.println("Team 1: "+ (scores[0]+scores[2])+"  Team2: "+(scores[1]+scores[3]) );
	    				
				}
				for(int s= 0; s<4; s++)
					System.out.println("Player "+(s+1)+": "+scores[s]+ " Difference of " + (scores[s] - bids[s]));
					
    	
    	}
    }
    
    
