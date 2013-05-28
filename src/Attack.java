package strategy;

import java.io.*;
import java.util.Scanner;
import java.util.NoSuchElementException;

class Attack extends Information
{
	int target;
	int attacker;
	int my_loss;
	int enemy_soldiers_loss;
	int enemy_farmers_loss;
	int my_gain;
	
	boolean failed;
	
	String directory;
	
	KingdomState state;
	Limits limits;
	Mood mood;
	
	private Attack(String directory) throws FileNotFoundException, IOException, NoSuchElementException
	{
		Scanner scanner=new Scanner(new File(directory, "utok.txt")).useDelimiter("[= \n]");
		this.directory=directory;
		
		//System.err.println("Attack:");
		
		target=getNumber(scanner);
		attacker=getNumber(scanner);
		my_loss=getNumber(scanner);
		enemy_soldiers_loss=getNumber(scanner);
		enemy_farmers_loss=getNumber(scanner);
		my_gain=getNumber(scanner);
		
		scanner.close();
	}
	
	public static Attack getAttack(String directory)
	{
		try
		{
			return new Attack(directory);
		}
		catch(FileNotFoundException e)
		{
			return null;
		}
		catch(IOException e)
		{
			return null;
		}
		catch(NoSuchElementException e)
		{
			return null;
		}
	}
	
	public int getNewScore(int old_score)
	{
		if(my_gain==0)
		{
			if(my_loss==0)
			{
				return Integer.MIN_VALUE;
			}
			else
			{
				return old_score/2;
			}
		}
		else
		{
			if(enemy_farmers_loss>my_gain)
			{
				return Integer.MIN_VALUE;
			}
			else if(my_loss==0)
			{
				return old_score+200;
			}
			else
			{
				return old_score+100;
			}
		}
	}
}

