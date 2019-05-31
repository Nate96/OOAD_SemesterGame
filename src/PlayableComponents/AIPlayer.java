package PlayableComponents;

import java.util.Observable;
import java.util.Random;

import Events.*;
import PetConponents.Pet;
import PetConponents.Skills;

@SuppressWarnings("deprecation")
public class AIPlayer extends Player
{
	private Random random;

	public AIPlayer(String name, Pet pet)
	{
		super(name, pet, PlayerTypes.COMPUTER);
		this.playerType = PlayerTypes.COMPUTER;
		this.random = new Random();
	}
	
	@Override
	public void update(Observable o, Object arg)
	{
		if (arg instanceof AttackEvent) {			
		}
		if(arg instanceof RoundStartEvent) {
		}
		if (arg instanceof FightStartEvent) {		
		}
	}

	@Override
	public Skills chooseSkill()
	{
		int min = 1, max = 5;
		
		int skillValue =  (random.nextInt((max - min) + 1) + min);
		
		if(skillValue == 1)
			return Skills.ROCK_THROW;
		else if(skillValue == 2)
			return Skills.PAPER_CUT;
		else if(skillValue == 3)
			return Skills.SCISSORS_POKE;
		else if(skillValue == 4)
			return Skills.SHOOT_THE_MOON;
		else if(skillValue == 5)
			return Skills.REVERSAL_OF_FORTUNE;
		else
			return null;
	}
	
	public Skills setSkillPredeiction() 
	{
		return this.predictedSkill = chooseSkill();
	}
	
	public Skills getPredictedSkill()
	{
		return this.predictedSkill;
	}
	
	public void setSkill(Skills skill)
	{
		this.choosenSkill = skill;
	}
	
	public Skills getSkill()
	{
		return this.choosenSkill;
	}
}
