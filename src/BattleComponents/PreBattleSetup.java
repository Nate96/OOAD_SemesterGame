package BattleComponents;

//package InputOutput;
import java.util.ArrayList;

import java.util.List;
import InputOutput.IOManager;
import PlayableComponents.InformationGetter;
import PlayableComponents.Playable;

// Get information from user(s) for executing the battle
public class PreBattleSetup implements InformationGetter
{

	IOManager ioManager;

	public PreBattleSetup(IOManager ioManager)
	{
		this.ioManager = ioManager;

	}
	
	public boolean requestUIOption()
	{
		boolean textBase = true;
		textBase = ioManager.desireUIVersion();
		return textBase;
	}

	public GameSettings run()
	{
		GameSettings tempgameSettings = new GameSettings();

		List<Playable> playerList = new ArrayList<>();
		long randomNumber = 0;
		
		randomNumber = ioManager.getRandomNumber();
		int numFight = 0;
		numFight = ioManager.getNumFight();
		playerList = ioManager.createPlayers();

		tempgameSettings.setSettings(playerList, numFight, randomNumber);
		return tempgameSettings;
	}

	@Override
	public String getPlayerName()
	{

		return null;
	}

	@Override
	public String getPetName()
	{
		// TODO Auto-generated method stub
		return null;
	}

}