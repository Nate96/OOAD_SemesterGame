package PlayableComponents;

import java.util.ArrayList;
import java.util.List;

import PetConponents.Skills;

public class PlayerInformation implements Comparable<PlayerInformation>
{
	private Integer numberOfWins;
	private Skills chosenSkill;
	private int playerIndex;
	private List<Double> totalRandomDiff;

	public PlayerInformation(int index)
	{
		this.numberOfWins = 0;
		this.chosenSkill = null;
		this.playerIndex = index;
		this.totalRandomDiff = new ArrayList<>();
	}
	
	/**
	 * Adds all the elements in the array and returns the value
	 * @return
	 */
	public double getTotalRandomDiff()
	{
		int total = 0;
		for (int i = 0; i < totalRandomDiff.size(); i++)
		{
			total += totalRandomDiff.get(i);
		}
		return total;
	}
	
	/**
	 * adds the value to the array 
	 * @param damage
	 */
	public void updateTotalRandomDiff(double damage)
	{
		totalRandomDiff.add(damage);
	}
	
	public void resetTotalRandomDiff()
	{
		chosenSkill = null;
		totalRandomDiff = new ArrayList<>();
	}
	public int getPlayerIndex()
	{
		return playerIndex;
	}

	public int getNumberOfWins()
	{
		return this.numberOfWins;
	}

	public void updateNumberOfWins()
	{
		this.numberOfWins++;
	}

	public Skills getChossenSkill()
	{
		return chosenSkill;
	}

	public void setChosenSkill(Skills chosenSkill)
	{
		this.chosenSkill = chosenSkill;
	}

	@Override
	public int compareTo(PlayerInformation playerInfo)
	{
		return numberOfWins.compareTo(playerInfo.numberOfWins);
	}


}
