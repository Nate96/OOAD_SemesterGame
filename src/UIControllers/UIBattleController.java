package UIControllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import BattleComponents.Battle;
import BattleComponents.GameSettings;
import Controllers.BattleController;
import Events.PlayerEventInformation;
import Events.PlayerEventInformation.PlayerEventInformationBuilder;
import PlayableComponents.PlayerInformation;

@SuppressWarnings("deprecation")
public class UIBattleController extends Observable implements BattleController {

	private Battle battle; 
	
	public UIBattleController(GameSettings gameSettings)
	{
		this.battle = new Battle(gameSettings);
	}

	public Battle getBattle()
	{
		return battle;
	}

	@Override
	public void startBattle() 
	{
		for (int i = 0; i < battle.getPlayerList().size(); i++)
		{
			battle.getPlayerInformationList().add(new PlayerInformation(i));
			this.addObserver(battle.getPlayerList().get(i));
		}	
	}


	@Override
	public List<PlayerInformation> determineWinners() 
	{
		int mostWins = 0;
		List<PlayerInformation> winnerList = new ArrayList<>();
		
		for(int i = 0; i < battle.getPlayerInformationList().size(); i++)
		{
			if(mostWins >= battle.getPlayerInformationList().get(i).getNumberOfWins())
			{
				winnerList.add(battle.getPlayerInformationList().get(i));
			}
			else
			{
				winnerList.clear();
				winnerList.add(battle.getPlayerInformationList().get(i));
			}
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
			list.add(builder.buildPlayerEventInformation());
		}
		return list;
	}
	
	public void endBattle()
	{
		this.deleteObservers();
	}

}
