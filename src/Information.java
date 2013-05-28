package strategy;

import java.io.*;
import java.util.Scanner;
import java.util.NoSuchElementException;

abstract class Information
{
	int getNumber(Scanner scanner) throws IOException, NoSuchElementException
	{
		while(!scanner.hasNextInt())
		{
			scanner.next();
		}
		
		int r=scanner.nextInt();
		
		return r;
	}
}

