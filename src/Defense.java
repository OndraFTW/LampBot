package strategy;

import java.io.*;
import java.util.Scanner;
import java.util.NoSuchElementException;

class Defense extends Information
{
	int attacker;
	int attacker_loss;
	int my_soldiers_loss;
	int my_farmers_loss;
	int my_loss;
	
	String directory;
	
	KingdomState state;
	Limits limits;
	Mood mood;
	
	private Defense(String directory) throws FileNotFoundException, IOException, NoSuchElementException
	{
		Scanner scanner=new Scanner(new File(directory, "obrana.txt")).useDelimiter("[= \n]");
		this.directory=directory;
		
		//System.err.println("Defense:");
		
		attacker=getNumber(scanner);
		attacker_loss=getNumber(scanner);
		my_soldiers_loss=getNumber(scanner);
		my_farmers_loss=getNumber(scanner);
		my_loss=getNumber(scanner);
		
		scanner.close();
	}
	
	public static Defense getDefense(String directory)
	{
		try
		{
			return new Defense(directory);
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
		if(my_loss==0)
		{
			if(state.army()>=limits.high_army/2)
			{
				return mood.highest()+1;
			}
			else
			{
				return old_score+100;
			}
		}
		else
		{
			return old_score-100;
		}
	}
}

