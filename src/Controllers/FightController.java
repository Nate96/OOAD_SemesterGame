package Controllers;

import BattleComponents.Fight;
import PlayableComponents.Playable;

public interface FightController 
{
	public void startFight(int index);
	public void determineWinner();
	public void resetPlayerStats();
	public Fight getFight();
	public Playable getWinner();
	public boolean isDone();
	
}
