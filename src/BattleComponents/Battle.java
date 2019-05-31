package BattleComponents;

import java.util.ArrayList;
import java.util.List;

import Controllers.FightController;
import Damage.Calculatable;
import PlayableComponents.Playable;
import PlayableComponents.PlayerInformation;

public class Battle
{

	private GameSettings gameSettings;
	private List<FightController> fightList;
	private List<PlayerInformation> playerInformationList;
	private Calculatable damageCalculator;
	private List<Playable> playerList;
	private int numberOfFights;

	public Battle(GameSettings gameSettings)
	{
		this.damageCalculator = gameSettings.getDamageCalculator();
		this.playerList = gameSettings.getPlayerList();
		this.numberOfFights = gameSettings.getNumberOfFights();
		this.damageCalculator = gameSettings.getDamageCalculator();
		
		this.fightList = new ArrayList<>();
		this.playerInformationList = new ArrayList<>();

		for (int i = 0; i < playerList.size(); i++)
			this.playerInformationList.add(new PlayerInformation(i));

	}

	public List<FightController> getFightList()
	{
		return fightList;
	}

	public Calculatable getDamageCalculator()
	{
		return damageCalculator;
	}

	public int getNumberOfFights()
	{
		return numberOfFights;
	}

	public GameSettings getGameSettings()
	{
		return gameSettings;
	}

	public List<PlayerInformation> getPlayerInformationList()
	{
		return this.playerInformationList;
	}

	public List<Playable> getPlayerList()
	{
		return this.playerList;
	}
	
	public Calculatable getCalculator()
	{
		return damageCalculator;
	}

}