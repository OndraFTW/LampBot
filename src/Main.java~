package strategy;

import java.io.*;
import java.net.URLDecoder;
import java.util.Scanner;
import java.util.NoSuchElementException;


public class Main
{
	static KingdomState state;
	static Attack attack;
	static Defense defense;
	static Mood mood;
	static Limits limits;
	
	static String directory;
	
	public static void main(String[] args)
	{
		state=new KingdomState(args);
		
		try
		{
			directory=URLDecoder.decode(KingdomState.class.getProtectionDomain().getCodeSource().getLocation().getPath(), "UTF-8");
			directory=directory.substring(0, directory.lastIndexOf(File.separatorChar));
		}
		catch(UnsupportedEncodingException e)
		{
			directory="./";
		}
		
		attack=Attack.getAttack(directory);
		defense=Defense.getDefense(directory);
		
		mood=new Mood(directory);
		limits=new Limits(directory, state.territories);
		
		if(attack!=null)
		{
			attack.state=state;
			attack.limits=limits;
			attack.mood=mood;
		}
		
		if(defense!=null)
		{
			defense.state=state;
			defense.limits=limits;
			defense.mood=mood;
		}
		
		changeMood();
		changeLimits();
		
		String action=getAction();
		System.out.print(action);
		
		//mood.print();
		//limits.print();
		
		if(state.rounds!=1)
		{
			mood.save();
			limits.save();
		}
		else
		{
			mood.delete();
			limits.delete();
		}
	}
	
	static String getAction()
	{
		if(hunger() ||
			(mood.farming>=mood.growth && mood.farming>=mood.attack_1 &&
			mood.farming>=mood.attack_2 && mood.farming>=mood.attack_3))
		{
			return farm();
		}
		else if((mood.growth>=mood.farming && mood.growth>=mood.attack_1 &&
			mood.growth>=mood.attack_2 && mood.growth>=mood.attack_3))
		{
			return grow();
		}
		else if((mood.attack_1>=mood.growth && mood.attack_1>=mood.farming &&
			mood.attack_1==mood.attack_2 && mood.attack_1==mood.attack_3))
		{
			return attack((int)(Math.random()*3.0+1));
		}
		else if((mood.attack_1>=mood.growth && mood.attack_1>=mood.farming &&
			mood.attack_1>=mood.attack_2 && mood.attack_1>=mood.attack_3))
		{
			return attack(1);
		}
		else if((mood.attack_2>=mood.growth && mood.attack_2>=mood.farming &&
			mood.attack_2>=mood.attack_1 && mood.attack_2>=mood.attack_3))
		{
			return attack(2);
		}
		else if((mood.attack_3>=mood.growth && mood.attack_3>=mood.farming &&
			mood.attack_3>=mood.attack_1 && mood.attack_3>=mood.attack_2))
		{
			return attack(3);
		}
		else
		{
			return grow();
		}
	}
	
	static void changeMood()
	{
		int rounds_to_hunger=state.roundsToHunger();
		int farms=state.farms();
		int army=state.army();
		
		if(rounds_to_hunger<=limits.hunger &&  rounds_to_hunger<=state.rounds)
		{
			mood.farming+=500;
		}
		else if(rounds_to_hunger<=limits.enought &&  rounds_to_hunger<=state.rounds)
		{
			mood.farming+=200;
		}
		
		if(farms<=limits.low_farms)
		{
			mood.growth+=200;
		}
		else if(farms<=limits.high_farms)
		{
			mood.growth+=100;
		}
		
		if(army<=limits.low_army)
		{
			mood.growth+=200;
		}
		else if(army<=limits.high_army)
		{
			mood.growth+=100;
		}
		else
		{
			mood.attack_1+=100;
			mood.attack_2+=100;
			mood.attack_3+=100;
		}
		
		if(state.territories<limits.initial_territories)
		{
			mood.attack_1+=200;
			mood.attack_2+=200;
			mood.attack_3+=200;
		}
		else if(state.territories<2*limits.initial_territories)
		{
			mood.attack_1+=100;
			mood.attack_2+=100;
			mood.attack_3+=100;
		}
		
		if(attack!=null)
		{
			switch(attack.target)
			{
				case 1:mood.attack_1=attack.getNewScore(mood.attack_1);	break;
				case 2:mood.attack_2=attack.getNewScore(mood.attack_2);	break;
				case 3:mood.attack_3=attack.getNewScore(mood.attack_3);	break;
			}
		}
		
		if(defense!=null)
		{
			switch(defense.attacker)
			{
				case 1:mood.attack_1=defense.getNewScore(mood.attack_1);	break;
				case 2:mood.attack_2=defense.getNewScore(mood.attack_2);	break;
				case 3:mood.attack_3=defense.getNewScore(mood.attack_3);	break;
			}
		}
	}
	
	static void changeLimits()
	{
		if(attack!=null)
		{
			if(attack.my_gain==0 && attack.my_loss!=0)
			{
				double q=(attack.my_loss*state.weapon_level/2.0)/(0.75*limits.high_army);
				
				if(q>1.0)
				{
					limits.high_army*=q;
					limits.high_farms*=q;
					limits.low_army*=q;
					limits.low_farms*=q;
					return;
				}
			}
		}
		
		if(defense!=null)
		{
			if(defense.my_loss!=0)
			{
				double q=(defense.my_soldiers_loss*state.weapon_level/2.0+defense.my_loss)/(0.75*limits.high_army);
				
				if(q>1.0)
				{
					limits.high_army*=q;
					limits.high_farms*=q;
					limits.low_army*=q;
					limits.low_farms*=q;
				}
			}
		}
	}
	
	static boolean hunger()
	{
		int rounds_to_hunger=state.roundsToHunger();
		return rounds_to_hunger<=limits.hunger &&  rounds_to_hunger<=state.rounds;
	}
	
	static String grow()
	{
		if(state.army()>limits.low_army && state.farms()>limits.low_farms && state.espionage_level<limits.espionage)
		{
			return "t";
		}
		else
		{
			int army=state.army();
			int farms=state.farms();
			
			if(army<limits.high_army && farms<limits.high_farms)
			{
				return army<=farms?incArmy():incFarms();
			}
			else if(army<=limits.high_army)
			{
				return incArmy();
			}
			else if(farms<=limits.high_farms)
			{
				return incFarms();
			}
			else
			{
				return army<=farms?incArmy():incFarms();
			}
		}
	}
	
	static String farm()
	{
		return state.consumption()>=state.production()?incFarms():"s";
	}
	
	static String attack(int target)
	{
		return state.army()>limits.low_army?"u "+target+" "+state.soldiers:grow();
	}
	
	static String incArmy()
	{
		if((0.75*state.weapon_level)<=state.soldiers)
		{
			return "z";
		}
		else
		{
			return "v";
		}
	}
	
	static String incFarms()
	{
		if(state.farm_level<=state.farmers)
		{
			return "f";
		}
		else
		{
			return "r";
		}
	}
	
	static String incEspionage()
	{
		return "t";
	}
}

