package TextControllers;

import java.util.List;
import java.util.Observable;

import BattleComponents.Battle;
import BattleComponents.Fight;
import Controllers.FightController;
import Events.FightStartEvent;
import Events.FightStartEvent.FightStartEventBuilder;

import PlayableComponents.Playable;

@SuppressWarnings("deprecation")
public class TextFightController extends Observable implements FightController
{
	private Fight fight;
	private String lineBreak = "-----------------------------------------------------------";
	public TextFightController(Battle battle, int id)
	{
		fight = new Fight(battle, id);
		
		List<Playable> playerList = fight.getBattle().getPlayerList();
		
		for(Playable playable : playerList)
		{
			this.addObserver(playable);
		}
	}
	
	@Override
	public Fight getFight()
	{
		return fight;
	}
	
	@Override
	public Playable getWinner()
	{
		return fight.getWinner();
	}
	
	@Override
	public void startFight(int index)
	{	
		FightStartEventBuilder fightBuilder = new FightStartEventBuilder();//Null???
		fightBuilder = fightBuilder.withFightNumber(index).withPlayerEventInformationList(fight.getPlayerInfoList());
		FightStartEvent startFight = fightBuilder.buildFightStartEvent();
		setChanged();
		notifyObservers(startFight);
		
		resetPlayerStats();
		
		run();
	}
	
	private void run()
	{
		int roundID = 0;
		while (!isDone())
		{
			fight.getRoundList().add(new TextRoundController(fight, roundID));
			this.fight.getRoundList().get(roundID).startRound();
			System.out.println(lineBreak);
			roundID++;
		}
		//this.fight.getRoundList().get(roundID).
		determineWinner();
		
	}
	
	/**
	 * Loops through the list to find the last man standing.
	 */
	@Override
	public void determineWinner()
	{
		double largestHealth = Double.NEGATIVE_INFINITY;
		int playerIndex = -1;
		for (int i = 0; i < fight.getBattle().getPlayerList().size(); i++)
		{
			double petHealth = fight.getBattle().getPlayerList().get(i).getCurrentHp();
			playerIndex = petHealth > largestHealth ? i : playerIndex;
			largestHealth =  petHealth > largestHealth ? petHealth : largestHealth; 
		}
		System.out.println("\nThe winner of fight " + (fight.getFightId() + 1) + " is " 
				+ fight.getBattle().getPlayerList().get(playerIndex).getPlayerName());
		System.out.println(lineBreak);
		
		fight.setWinner(fight.getBattle().getPlayerInformationList().get(playerIndex));
		fight.getBattle().getPlayerInformationList().get(playerIndex).updateNumberOfWins();
	}

	/**
	 * Loops through the PlayerList to see if only one player is still
	 * Awake
	 * 
	 * @return
	 */
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
	
	@Override
	public void resetPlayerStats()
	{
		for(int i = 0; i < fight.getBattle().getPlayerList().size(); i++)
		{
			fight.getBattle().getPlayerList().get(i).reset();
			fight.getBattle().getPlayerInformationList().get(i).resetTotalRandomDiff();
		}
			
		
	}

}
