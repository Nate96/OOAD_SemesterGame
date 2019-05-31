package UIControllers;

import java.util.Observable;

import BattleComponents.Battle;
import BattleComponents.Fight;
import Controllers.FightController;
import Events.FightStartEvent;
import Events.FightStartEvent.FightStartEventBuilder;
import PlayableComponents.Playable;

public class UIFightController extends Observable implements FightController
{

	private Fight fight;
	
	public UIFightController(Battle battle, int id)
	{
		this.fight = new Fight(battle, id);
	}
	
	public Fight getFight()
	{
		return fight;
	}
	
	@Override
	public void startFight(int index) 
	{
		FightStartEventBuilder fightBuilder = new FightStartEventBuilder();
		fightBuilder = fightBuilder.withFightNumber(fight.getBattle().getNumberOfFights())
				.withPlayerEventInformationList(fight.getPlayerInfoList());
		
		FightStartEvent startFight = fightBuilder.buildFightStartEvent();
		
		setChanged();
		notifyObservers(startFight);
		
		resetPlayerStats();
		
	}

	@Override
	public void determineWinner() 
	{
		for (int i = 0; i < fight.getBattle().getPlayerList().size(); i++)
		{
			if (fight.getBattle().getPlayerList().get(i).isAwake())
			{
				fight.setWinner(fight.getBattle().getPlayerInformationList().get(i));
				fight.getBattle().getPlayerInformationList().get(i).updateNumberOfWins();
			}
		}
	}

	@Override
	public void resetPlayerStats() 
	{
		for (int i = 0; i < fight.getBattle().getPlayerList().size(); i++)
		{
			fight.getBattle().getPlayerList().get(i).reset();
		}
		
	}

	@Override
	public Playable getWinner() 
	{
		return fight.getWinner();
	}

	@Override
	public boolean isDone() 
	{
		int numberOfPetsAlive = 0;
		for (int i = 0; i < fight.getBattle().getPlayerList().size(); i++)
		{
			if (fight.getBattle().getPlayerList().get(i).isAwake())
				numberOfPetsAlive++;
		}

		return numberOfPetsAlive <= 1;
	}
	

}
