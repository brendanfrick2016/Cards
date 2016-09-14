import java.util.*;
public class heartsRunner {

	public static void main(String [] args)
	{
		ArrayList<player>players = new ArrayList<player>();
		round currentRound = new round(1);
		for(int i = 0; i<4; i++)
		{
			players.add(new player(i+1,currentRound));
		}
		currentRound.replenish();
		for(player p:players)
			p.test();
		
		
		
	}
}
