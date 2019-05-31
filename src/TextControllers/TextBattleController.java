package TextControllers;

import java.util.ArrayList;


import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

import BattleComponents.Battle;
import BattleComponents.GameSettings;
import Controllers.BattleController;
import Events.PlayerEventInformation;
import Events.PlayerEventInformation.PlayerEventInformationBuilder;
import PlayableComponents.PlayerInformation;

@SuppressWarnings("deprecation")
public class TextBattleController extends Observable implements BattleController 
{

	private Battle battle;

	public TextBattleController(GameSettings gameSettings)
	{
		this.battle = new Battle(gameSettings);
	}

	/**
	 * Runs all the fights and determine the winner.
	 */
	public Battle getBattle()
	{
		return this.battle;
	}

	@Override
	public void startBattle()
	{
		for (int i = 0; i < battle.getPlayerList().size(); i++)
		{
			//battle.getPlayerInformationList().add(new PlayerInformation(i));
			this.addObserver(battle.getPlayerList().get(i));
		}
		
		run();
		this.deleteObservers();
	}
	
	public void printPlayerWins()
	{
		System.out.println();
		for(int i = 0; i < battle.getPlayerInformationList().size(); i++)
		{
			String name = battle.getPlayerList().get(battle.getPlayerInformationList().get(i).getPlayerIndex()).getPlayerName();
			int numWins = battle.getPlayerInformationList().get(i).getNumberOfWins();
			System.out.println(name + " had " + numWins + " wins.");
		}
		System.out.println();
	}
	
	public void run()
	{
		for (int i = 0; i < battle.getNumberOfFights(); i++)
		{
			battle.getFightList().add(new TextFightController(battle, i));
			battle.getFightList().get(i).startFight(i);
		}
		
		List<PlayerInformation> winnerList = determineWinners();
		printPlayerWins();
		
		if(winnerList.size() == 1)
		{
			System.out.print("The winner is: ");
			System.out.print(battle.getPlayerList().get(winnerList.get(0).getPlayerIndex()).getPlayerName());
		}
		else
		{
			System.out.print("The winners are: ");
			for (int i = 0; i < winnerList.size(); i++)
			{
				System.out.print(battle.getPlayerList().get(winnerList.get(i).getPlayerIndex()).getPlayerName());
				if(i + 1 < winnerList.size())
					System.out.print(", ");
			}
		}
		Scanner scan=new Scanner(System.in);
	    System.out.println("\nPress ENTER key to close application. . . ");     
	    scan.nextLine();
	    System.exit(0);
	}

	/**
	 * Loops through the Map to find the player with the most wins. Does not
	 * work if more that one players have the same amount of wins.
	 * 
	 * @return the player that has the most wins
	 */
	@Override
	public List<PlayerInformation> determineWinners()
	{
		List<PlayerInformation> winnerList = new ArrayList<>();
		Collections.sort(battle.getPlayerInformationList(), Collections.reverseOrder());
		
		PlayerInformation winner = battle.getPlayerInformationList().get(0);
		winnerList.add(winner);
		for(int i = 1; i < battle.getPlayerInformationList().size(); i ++)
		{
			if(battle.getPlayerInformationList().get(i).getNumberOfWins() == winner.getNumberOfWins())
				winnerList.add(battle.getPlayerInformationList().get(i));
		}
		return winnerList;
	}

	
	@Override
	public List<PlayerEventInformation> buildInformationList()
	{
		List<PlayerEventInformation> list = new ArrayList<>();
		
		for (int i = 0; i < battle.getPlayerList().size(); i++)
		{
			PlayerEventInformationBuilder builder = new PlayerEventInformationBuilder();
			builder = builder
					.withPetType(battle.getPlayerList().get(i).getPetType());
			builder = builder.withPlayerTypes(
					battle.getPlayerList().get(i).getPlayerTypes());
		}
		return list;
	}
	

}
