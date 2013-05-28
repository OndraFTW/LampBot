package strategy;

class KingdomState
{
	String directory;
	
	public int rounds;
	public int territories;
	public int soldiers;
	public int farmers;
	public int weapon_level;
	public int farm_level;
	public int food;
	public int espionage_level;
	
	public KingdomState(String[] args)
	{
		rounds=Integer.parseInt(args[0]);
		territories=Integer.parseInt(args[1]);
		soldiers=Integer.parseInt(args[2]);
		farmers=Integer.parseInt(args[3]);
		weapon_level=Integer.parseInt(args[4]);
		farm_level=Integer.parseInt(args[5]);
		food=Integer.parseInt(args[6]);
		espionage_level=Integer.parseInt(args[7]);
	}
	
	int roundsToHunger()
	{
		if(food==0)
		{
			return 0;
		}
		else if((soldiers+farmers)==0)
		{
			return food;
		}
		else
		{
			return food/(soldiers+farmers);
		}
	}
	
	int consumption()
	{
		return farmers+soldiers;
	}
	
	int production()
	{
		return 3*farmers*(farm_level/3);
	}
	
	int farms()
	{
		return farmers*(farm_level/3);
	}
	
	int army()
	{
		return soldiers*(weapon_level/3);
	}
}

