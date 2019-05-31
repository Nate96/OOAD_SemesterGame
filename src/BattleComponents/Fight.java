package BattleComponents;


import Events.PlayerEventInformation;
import Events.PlayerEventInformation.PlayerEventInformationBuilder;
import PlayableComponents.Playable;
import PlayableComponents.PlayerInformation;

import java.util.ArrayList;
import java.util.List;

import Controllers.RoundController;

/* 
 * Class to handle the fights that happen inside of a battle.
 */
public class Fight
{

	private Battle battle;
	private int fightId;
	private List<RoundController> roundList;
	private List<PlayerEventInformation> playerEventInfoList;
	private PlayerInformation winner;

	/*
	 * Constructor to get the set the playerList.
	 */
	public Fight(Battle battle, int id)
	{
		this.fightId = id;
		this.battle = battle;
		
		this.roundList = new ArrayList<>();
		for (int i = 0; i < battle.getPlayerInformationList().size(); i++)
		{
			battle.getPlayerList()
					.get(battle.getPlayerInformationList().get(i).getPlayerIndex())
					.reset();
		}
		
		this.winner = null;
		
		this.playerEventInfoList = new ArrayList<>();
		for (int i = 0; i < battle.getPlayerList().size(); i++)
		{
			PlayerEventInformationBuilder infoBuilder = new PlayerEventInformationBuilder(); //Need more info here
			infoBuilder = infoBuilder.withPlayerTypes(battle.getPlayerList().get(i).getPlayerTypes()).withPetType(battle.getPlayerList().get(i).getPetType());
			infoBuilder = infoBuilder.withPetName(battle.getPlayerList().get(i).getPetName());
			infoBuilder = infoBuilder.withStartingHP(battle.getPlayerList().get(i).getPet().getMaxHealth());
			playerEventInfoList.add(infoBuilder.buildPlayerEventInformation());
			
		}

	}

	public List<PlayerEventInformation> getPlayerInfoList()
	{
		return playerEventInfoList; //Some values are null here....
	}

	public List<RoundController> getRoundList()
	{
		return roundList;
	}

	public Playable getWinner()
	{
		return getBattle().getPlayerList().get(winner.getPlayerIndex());
	}

	public Battle getBattle()
	{
		return battle;
	}

	public int getFightId()
	{
		return fightId;
	}
	
	public void setPlayerEventInfoList(List<PlayerEventInformation> list )
	{
		playerEventInfoList = list;
		
	}
	
	public void setWinner(PlayerInformation player)
	{
		winner = player;
	}


}
