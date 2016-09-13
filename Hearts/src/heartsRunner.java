import java.util.*;
public class heartsRunner {

	public static deck d;
	public static void main(String [] args)
	{
		deck d = new deck();
		hand h = new hand(d);
		System.out.println(h.underCut(new card("3S")));
		
		
		
		
	}
}
