package UIControllers;

import java.util.List;

import BattleComponents.GameSettings;
import PlayableComponents.Playable;

public class UIPreBattleSetupController 
{
	private GameSettings settings;
	
	public UIPreBattleSetupController()
	{
		this.settings = new GameSettings();
	}
	
	public void setup(List<Playable> playerList, int fightNum,
			long randomSeed)
	{
		settings.setSettings(playerList, fightNum, randomSeed);
	}
	
	public GameSettings getSettings()
	{
		return settings;
	}
}
