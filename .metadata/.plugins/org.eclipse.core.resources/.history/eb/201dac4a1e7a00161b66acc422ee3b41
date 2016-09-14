import java.util.ArrayList;

public class groupOfCards {

	private ArrayList<card> cards = new ArrayList<card>();
	
	
	public groupOfCards()
	{
		for(int i =2; i<15; i++)
			cards.add(new card(i,'S'));
		for(int i =2; i<15; i++)
			cards.add(new card(i,'C'));
		for(int i =2; i<15; i++)
			cards.add(new card(i,'H'));
		for(int i =2; i<15; i++)
			cards.add(new card(i,'D'));
	}
	public groupOfCards(ArrayList<card> al)
	{
		for(card i : al)
			cards.add(i);
	}
	
	
	public card removeCard(card c)
	{
		for(int i = 0; i<cards.size(); i++)
		{
			if(cards.get(i).toString().equals(c.toString()))
				return cards.remove(i);
		}
		return null;
	}
	public card getNextCard()
	{
		return cards.remove(0);
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
	
	public card highSuit(char s)
	{
		card max = new card(0,s);
		for(card a: cards)
		{
			if(a.suit==s&&a.num>max.num)
				max=a;
		}
		return max;
	}
	
	public ArrayList<card> getSuit(char s)
	{
			ArrayList<card>al =  new ArrayList<card>();
			for(card a:cards)
				if(a.suit==s)
					al.add(a);
			return al;
	}
	
	public String toString()
	{
		String s = "";
		for(card a:cards)
			s+=a.toString()+" ";
			
		return s;
	}
	
}
