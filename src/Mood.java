package strategy;

import java.io.*;
import java.util.Scanner;
import java.util.NoSuchElementException;

class Mood extends Information
{
	private final String FILENAME="mood.txt";
	String directory;
	
	public int growth;
	public int farming;
	public int attack_1;
	public int attack_2;
	public int attack_3;
	public int espionage;
	
	public Mood(String directory)
	{
		this.directory=directory;
		
		//System.err.println("Mood:");
		
		try
		{
			Scanner scanner=new Scanner(new File(directory, FILENAME)).useDelimiter("[ \n]");
			
			growth=getNumber(scanner)/2;
			farming=getNumber(scanner)/2;
			attack_1=getNumber(scanner)/2;
			attack_2=getNumber(scanner)/2;
			attack_3=getNumber(scanner)/2;
			espionage=getNumber(scanner)/2;
			
			scanner.close();
		}
		catch(FileNotFoundException e)
		{
			setDefaultValues();
		}
		catch(IOException e)
		{
			setDefaultValues();
		}
		catch(NoSuchElementException e)
		{
			setDefaultValues();
		}
	}
	
	void setDefaultValues()
	{
		growth=1000;
		farming=1000;
		attack_1=1000;
		attack_2=1000;
		attack_3=1000;
		espionage=1000;
	}
	
	int getNumber(Scanner scanner) throws IOException, NoSuchElementException
	{
		int n=super.getNumber(scanner);
		
		if(n<Integer.MIN_VALUE/8)
		{
			return Integer.MIN_VALUE;
		}
		else if(n<=2)
		{
			return 2;
		}
		else
		{
			return n;
		}
	}
	
	int highest()
	{
		int r=0;
		
		if(growth>r)
		{
			r=growth;
		}
		
		if(farming>r)
		{
			r=farming;
		}
		
		if(attack_1>r)
		{
			r=attack_1;
		}
		
		if(attack_2>r)
		{
			r=attack_2;
		}
		
		if(attack_3>r)
		{
			r=attack_3;
		}
		
		if(espionage>r)
		{
			r=espionage;
		}
		
		return r; 
	}
	
	void save()
	{
		try
		{
			Writer writer=new FileWriter(new File(directory, FILENAME));
			writer.write(Integer.toString(growth)+" "+
						Integer.toString(farming)+" "+
						Integer.toString(attack_1)+" "+
						Integer.toString(attack_2)+" "+
						Integer.toString(attack_3)+" "+
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
		System.err.print(Integer.toString(growth)+" "+
						Integer.toString(farming)+" "+
						Integer.toString(attack_1)+" "+
						Integer.toString(attack_2)+" "+
						Integer.toString(attack_3)+" "+
						Integer.toString(espionage)+"\n");
	}
}

