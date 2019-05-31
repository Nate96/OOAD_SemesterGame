package InputOutput;

import java.util.ArrayList;

import java.util.List;

import PetConponents.Pet;
import PlayableComponents.AIPlayer;
import PlayableComponents.Human;
import PlayableComponents.Playable;
import PlayableComponents.Player;
import PlayableComponents.PlayerTypes;
import PlayableComponents.Team06_ULTRON;

public class IOManager
{
	private Inputtable inputGetter;
	private Outputtable outputSender;
	private int count = 1;
	private boolean ultronSelected = false;

	public IOManager(Inputtable inputGetter, Outputtable outputSender)
	{
		this.inputGetter = inputGetter;
		this.outputSender = outputSender;
	}

	public Inputtable getInputGetter()
	{
		return inputGetter;
	}

	public Outputtable getOutputSender()
	{
		return outputSender;
	}

	public List<Playable> createPlayers()
	{
		List<Playable> tempPlayerList = new ArrayList<>();
		outputSender.outputString("Enter number of players.");
		int numPlayers = 0;
		numPlayers = getPlayerNum();
		int i = 0;
		

		while (i < numPlayers)
		{
			outputSender.outputString("Custom name for Player " + count++ + "? (1 - yes, 2 - no)");
			String name = inputGetter.getPlayerName();
			outputSender.outputString("Please enter a name for the pet");
			String petName = inputGetter.getPetName();
			Pet tempPet = inputGetter.createPet(petName);
			
			PlayerTypes playerType = null;
			playerType = isHumanType();
			Player tempPlayer;
			if(playerType == PlayerTypes.COMPUTER)
			{
			    tempPlayer = new AIPlayer(name, tempPet);	
			}
			else if(playerType == PlayerTypes.ULTRON)
			{
				tempPlayer = new Team06_ULTRON("Ultron", tempPet);	
			}
			else
			{
			    tempPlayer = new Human(name, tempPet);	
			}
			
			tempPlayerList.add(tempPlayer);
			i++;
		}

		return tempPlayerList;
	}

	public PlayerTypes isHumanType()
	{
		PlayerTypes type = null;
		
		outputSender.outputString("Is this player a computer? (1 - yes, 2 - no)");
		String input = inputGetter.getPetName();
		while (!input.equals("1") && !input.equals("2"))
		{
			System.out.println("Please enter a valid answer.");
			input = inputGetter.getPetName();
		}
		if (input.equals("1"))
		{
			if(!ultronSelected)
			{
				outputSender.outputString("Is this Ultron? (1 - yes, 2 - no)");
				input = inputGetter.getPetName();
				while (!input.equals("1") && !input.equals("2"))
				{
					System.out.println("Please enter a valid answer.");
					input = inputGetter.getPetName();
				}
				if (input.equals("1") && !ultronSelected)
				{
					ultronSelected = true;
					type = PlayerTypes.ULTRON;
				}
				else
				{
					type = PlayerTypes.COMPUTER;
				}
			}
			else 
			{
				type = PlayerTypes.COMPUTER;
			}
		} 
		else
		{
			type = PlayerTypes.HUMAN;
		}
		return type;
	}

	public int getPlayerNum()
	{
		int numPlayer = 0;
		boolean valid = false;
		while (!valid)
		{
			try
			{
				numPlayer = inputGetter.getPlayerNumber();
				valid = true;
			} catch (Exception e)
			{
				outputSender.outputString("Please enter a number value.");
			}

		}

		while (numPlayer <= 1)
		{

			try
			{
				outputSender.outputString("Please number bigger than 1.");
				numPlayer = inputGetter.getPlayerNumber();
			} catch (Exception e)
			{
				outputSender.outputString("Please number bigger than 1.");
			}
		}
		return numPlayer;
	}

	public int getNumFight()
	{
		int numFight = 0;
		outputSender.outputString("Please enter the number of fights.");
		boolean valid = false;
		while (!valid)
		{
			try
			{
				numFight = inputGetter.getFightNumber();
				valid = true;
			} catch (Exception e)
			{
				outputSender.outputString("Please enter a number value.");
			}

		}

		while (numFight <= 0)
		{

			try
			{
				outputSender.outputString("Please number bigger than 0.");
				numFight = inputGetter.getFightNumber();
			} catch (Exception e)
			{
				outputSender.outputString("Please number bigger than 0.");
			}
		}
		return numFight;
	}
	
	public boolean desireUIVersion()
	{
		outputSender.outputString("Please Enter: ");
		outputSender.outputString("1. Text Version Of Game ");
		outputSender.outputString("2. GUI Version of Game");
		String input = inputGetter.getPetName();
		while (!input.equals("1") && !input.equals("2"))
		{
			System.out.println("Please enter '1' for Text Version, or '2' for GUI Version.");
			input = inputGetter.getPetName();
		}
		if (input.equals("2"))
		{
			return true;

		} 
		else
		{
			return false;
		}
	}

	public long getRandomNumber()
	{
		long randomNumber = 0;
		outputSender.outputString("Please enter the random number seed");
		boolean valid = false;
		while (!valid)
		{
			try
			{
				randomNumber = inputGetter.getFightNumber();
				valid = true;
			} catch (Exception e)
			{
				outputSender.outputString("Please enter a number value.");
			}

		}

		while (randomNumber <= 0)
		{

			try
			{
				outputSender.outputString("Please number bigger than 0.");
				randomNumber = inputGetter.getFightNumber();
			} catch (Exception e)
			{
				outputSender.outputString("Please number bigger than 0.");
			}
		}
		return randomNumber;
	}

	public void setName(String name)
	{

	}

	public void closeInput()
	{
		try
		{
			inputGetter.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void closeOutput()
	{
		try
		{
			outputSender.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
