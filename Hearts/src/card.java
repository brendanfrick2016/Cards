//simple, every card has .num and .suit, suits are capital chars and nums are a number 1-14
//You can create a card by giving the #,suit,true or just a string like KS or 6S
// the boolean should always be true for ace high
public class card {
	
	public int num;
	public char suit;
    
   		public card(int n, char s) 
    	{
    			if(n == 1)
    				n =14;//Aces High
    		num =n;
    		suit = s;
    	}
    	public card(String s)
    	{
    		char c = s.charAt(0);
    		if(c == 'K')
    			num = 13;
    		else if(c=='Q')
    			num = 12;
    		else if(c=='J')
    			num = 11;
    		else if(c=='A')
    			num = 14;
    		else
    			num = (int)c;
    		
    		suit = s.charAt(1);
    		
    		if(s.charAt(1) == 0)
    		{
    			num = 10;
    			suit = s.charAt(2);
    		}
    	}
    	
    	public String toString()
    	{
    		if(num==13)
    			return "K"+suit;
    		if(num==12)
    			return "Q"+suit;
    		if(num==11)
    			return "J"+suit;
    		if(num==1||num == 14)
    			return "A"+suit;
    		return num+""+suit;
    	}
}
    