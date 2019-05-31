package BattleComponents;

import java.util.ArrayList;
import java.util.List;

import Events.RoundStartEvent;
import Events.RoundStartEvent.EventStartBuilder;
import PlayableComponents.Playable;
import PlayableComponents.PlayerInformation;

public class Round
{
	private Fight fight;
	private int roundIndex;
	private List<Double> randomDamages;
	private List<PlayerInformation> attackList;
	private RoundStartEvent startEvent; 

	public Round(Fight fight, int roundIndex)
	{
		this.fight = fight;
		this.roundIndex = roundIndex;
		this.randomDamages = new ArrayList<Double>(
				fight.getBattle().getPlayerList().size());
		
		this.attackList = new ArrayList<>();
		
		for (int i = 0; i < fight.getBattle().getPlayerList().size(); i++)
		{
			if (getPlayerList().get(i).isAwake())
			{
				attackList.add(fight.getBattle().getPlayerInformationList().get(i));
				//addObserver(getFight().getBattle().getPlayerList().get(i));
			}
		}
		
		EventStartBuilder builder = new EventStartBuilder();
		builder = builder.withRoundNumber(roundIndex);
		this.startEvent = builder.buildRoundStartEvent();
		
	}
	
	public RoundStartEvent getStartEvent()
	{
		return startEvent;
	}
	
	public void setRandomDamage(double randomDamage)
	{
		randomDamages.add(randomDamage);
	}
	

	public List<Double> getDamages()
	{
		return randomDamages;
	}

	public int getRoundID()
	{
		return roundIndex;
	}
	
	public List<PlayerInformation> getAttackList()
	{
		return attackList;
	}

	public Fight getFight()
	{
		return fight;
	}

	public List<Playable> getPlayerList()
	{
		return fight.getBattle().getPlayerList();
	}

}
