package InputOutput;

import java.util.Scanner;

import PetConponents.Pet;
import PetConponents.PetTypes;

public class DefaultInputGetter implements Inputtable
{
	private Scanner scanner;
	private int count = 0;

	public DefaultInputGetter()
	{
		scanner = new Scanner(System.in);
	}

	public String getPlayerName()
	{
		String name = "";
		count++;
		String input = scanner.nextLine();
		while (!input.equals("1") && !input.equals("2"))
		{
			System.out.println("Please enter a valid answer.");
			input = scanner.nextLine();
		}

		if (input.equals("1"))
		{
			System.out.println("Please enter your desired name.");
			name = scanner.nextLine();
			System.out.println("Your name will be: " + name);
			return name;
		} 
		else
		{
			name = "Player " + count;
			System.out.println("Your name will be: " + name);
			return name;
		}
	}

	public int getPlayerNumber()
	{

		return Integer.parseInt(scanner.next());
	}

	public int getFightNumber()
	{
		return Integer.parseInt(scanner.next());
	}

	public long getRandomNumber()
	{
		return Long.parseLong(scanner.next());
	}

	public double getPetMaxHp()
	{
		double health = 100;
		String input;
		boolean valid = false;
		while (!valid)
		{
			try
			{
				System.out.println("Please enter the pet's HP.");
				input = scanner.nextLine();
				health = Integer.parseInt(input);
				valid = true;
			} catch (Exception e)
			{
				
			}

		}
		return health;
	}

	public Pet createPet(String petName)
	{
		boolean valid = false;
		String input;
		double petHP = getPetMaxHp();
		valid = false;
		System.out.println("Choose a pet:");
		System.out.println("1 : Speed");
		System.out.println("2 : Power");
		System.out.println("3 : Intelligence");
		while (!valid)
		{
			try
			{
				input = scanner.nextLine();
				int temp = Integer.parseInt(input);
				switch (temp)
				{
				case 1:
					return new Pet(petName, PetTypes.SPEED, petHP);
				case 2:
					return new Pet(petName, PetTypes.POWER, petHP);
				case 3:
					return new Pet(petName, PetTypes.INTELLIGENCE, petHP);
				default:
					throw new Exception();
				}
			} catch (Exception e)
			{
				System.out.println("Please enter a valid input for Pet Type!");
			}
		}
		return null;
	}

	@Override
	public void close() throws Exception
	{
		scanner.close();
	}

	@Override
	public String getPetName()
	{
		return scanner.nextLine();
	}

}
