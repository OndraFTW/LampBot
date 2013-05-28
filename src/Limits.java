package strategy;

import java.io.*;
import java.util.Scanner;
import java.util.NoSuchElementException;

class Limits extends Information
{
	static final String FILENAME="limits.txt";
	static String directory;
	
	public int initial_territories;
	public int hunger;
	public int enought;
	public int low_farms;
	public int high_farms;
	public int low_army;
	public int high_army;
	public int espionage;
	
	public Limits(String directory, int territories)
	{
		this.directory=directory;
		
		//System.err.println("Limits:");
		
		try
		{
			Scanner scanner=new Scanner(new File(directory, FILENAME)).useDelimiter("[ \n]");
			
			initial_territories=getNumber(scanner);
			hunger=getNumber(scanner);
			enought=getNumber(scanner);
			low_farms=getNumber(scanner);
			high_farms=getNumber(scanner);
			low_army=getNumber(scanner);
			high_army=getNumber(scanner);
			espionage=getNumber(scanner);
			
			scanner.close();
		}
		catch(FileNotFoundException e)
		{
			setDefaultValues(territories);
		}
		catch(IOException e)
		{
			setDefaultValues(territories);
		}
		catch(NoSuchElementException e)
		{
			setDefaultValues(territories);
		}
	}
	
	void setDefaultValues(int territories)
	{
		initial_territories=territories;
		hunger=2;
		enought=4;
		low_farms=3;
		high_farms=20;
		low_army=3;
		high_army=350;
		espionage=0;
	}
	
	void update()
	{
		//high_army+=1;
		//high_farms+=1;
	}
	
	void save()
	{
		update();
		try
		{
			Writer writer=new FileWriter(new File(directory, FILENAME));
			writer.write(Integer.toString(initial_territories)+" "+
						Integer.toString(hunger)+" "+
						Integer.toString(enought)+" "+
						Integer.toString(low_farms)+" "+
						Integer.toString(high_farms)+" "+
						Integer.toString(low_army)+" "+
						Integer.toString(high_army)+" "+
						Integer.toString(espionage)+"\n");
			writer.flush();
			writer.close();
		}
		catch(IOException e)
		{
			return;
		}
	}
	
	void delete()
	{
		(new File(directory, FILENAME)).delete();
	}
	
	void print()
	{
		update();
		System.err.print(Integer.toString(initial_territories)+" "+
						Integer.toString(hunger)+" "+
						Integer.toString(enought)+" "+
						Integer.toString(low_farms)+" "+
						Integer.toString(high_farms)+" "+
						Integer.toString(low_army)+" "+
						Integer.toString(high_army)+" "+
						Integer.toString(espionage)+"\n");
	}
}
