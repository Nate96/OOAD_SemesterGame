package BattleComponents;

import java.util.ArrayList;
import java.util.List;

import Damage.Calculatable;
import Damage.DamageCalculator;
import PlayableComponents.Playable;

public class GameSettings
{
	private Calculatable damageCalculator;
	private List<Playable> playerList;
	private int numberOfFights;

	public void setSettings(List<Playable> pPlayerList, int numFight,
			long randomSeed)
	{
		this.playerList = pPlayerList;
		this.numberOfFights = numFight;
		this.damageCalculator = new DamageCalculator(randomSeed);
	}

	public DamageCalculator getDamageCalculator()
	{
		return (DamageCalculator) damageCalculator;
	}

	public List<Playable> getPlayerList()
	{
		return (ArrayList<Playable>) this.playerList;
	}

	public void setNumberOfFight(int number)
	{
		this.numberOfFights = number;
	}

	public int getNumberOfFights()
	{
		return this.numberOfFights;
	}

}
