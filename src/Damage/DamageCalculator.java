package Damage;

import java.util.Random;

import Controllers.RoundController;
import PetConponents.PetTypes;
import PetConponents.Skills;
import PlayableComponents.Playable;
import PlayableComponents.PlayerInformation;

public class DamageCalculator implements Calculatable
{
	private RoundController roundController;
	private Random random;

	public DamageCalculator(long seed)
	{
		random = new Random(seed);
	}

	private Playable getPlayable(int index)
	{
		return roundController.getPlayerList().get(index);
	}

	public void setRoundController(RoundController roundController)
	{
		this.roundController = roundController;
	}

	/**
	 * adds the random damaged and conditional damaged
	 */
	@Override
	public Damage calculteDamage(PlayerInformation attacker, PlayerInformation victim)
	{
		return new Damage(randomDamage(attacker.getPlayerIndex()), condintionalDamage(attacker, victim));
	} 

	/**
	 * Picks a random integer between 0 and 5. A seed value is retrieved from
	 * the game settings.
	 * 
	 * @return random integer
	 */
	private double randomDamage(int playerIndex)
	{
		double max = 5;
		double min = 0;
		return min + (max - min) * random.nextDouble();
	}

	/**
	 * Checks to see if there are any conditional damage that can be applied.
	 * If not will return 0
	 * 
	 * @param type
	 * @param skill
	 * @param pet
	 * @return
	 */
	private double condintionalDamage(PlayerInformation attacker, PlayerInformation victim)
	{
		
		if (getPlayable(attacker.getPlayerIndex()).getPet().getType().equals(PetTypes.INTELLIGENCE))
			return intelligenceCondintionalDamage(attacker, victim);
		else if (getPlayable(victim.getPlayerIndex()).getPet().getType()
				.equals(PetTypes.POWER))
			return powerCondintionalDamage(attacker, victim);
		else
			return speedCondintionalDamage(attacker, victim);
	}

	/**
	 * Returns the conditional damage for the type speed
	 * 
	 * @param skill
	 * @param pet
	 * @return
	 */
	private double speedCondintionalDamage(PlayerInformation attacker, PlayerInformation victim)
	{
		double victimHealth = getPlayable(victim.getPlayerIndex()).calculateHpPercent();	
		
		if (attacker.getChossenSkill() == Skills.ROCK_THROW)
			if (victimHealth >= 75)
				if (victim.getChossenSkill() == Skills.SCISSORS_POKE
						|| victim.getChossenSkill() == Skills.PAPER_CUT)
					return 10;
		if (attacker.getChossenSkill() == Skills.SCISSORS_POKE)
			if (victimHealth < 75 && victimHealth >= 25)
				if (victim.getChossenSkill() == Skills.ROCK_THROW
						|| victim.getChossenSkill() == Skills.SCISSORS_POKE)
					return 10;
		if (attacker.getChossenSkill() == Skills.PAPER_CUT)
			if (victimHealth >= 0 && victimHealth < 25)
				if (victim.getChossenSkill() == Skills.ROCK_THROW
						|| victim.getChossenSkill() == Skills.SCISSORS_POKE)
					return 10;
		if (attacker.getChossenSkill() == Skills.SHOOT_THE_MOON)
			return shootTheMoon(attacker, victim);
		if (attacker.getChossenSkill() == Skills.REVERSAL_OF_FORTUNE)
			return reversalOfFortune(attacker);
		return 0;
	}

	/**
	 * Returns the conditional damage for the type power
	 * 
	 * @param skill
	 * @param pet
	 * @return
	 */
	private double powerCondintionalDamage(PlayerInformation attacker, PlayerInformation victim)
	{
		if (attacker.getChossenSkill() == Skills.ROCK_THROW)
			if (victim.getChossenSkill() == Skills.SCISSORS_POKE)
				return 5;
		if (attacker.getChossenSkill() == Skills.SCISSORS_POKE)
			if (victim.getChossenSkill() == Skills.PAPER_CUT)
				return 5;
		if (attacker.getChossenSkill() == Skills.PAPER_CUT)
			if (victim.getChossenSkill() == Skills.ROCK_THROW)
				return 5;
		if (attacker.getChossenSkill() == Skills.SHOOT_THE_MOON)
			return shootTheMoon(attacker, victim);
		if (attacker.getChossenSkill() == Skills.REVERSAL_OF_FORTUNE)
			return reversalOfFortune(attacker);
		return 1;
	}

	/**
	 * Returns the conditional damage for the type intelligence
	 * 
	 * @param skill
	 * @param pet
	 * @return
	 */
	private double intelligenceCondintionalDamage(PlayerInformation attacker, PlayerInformation victim)
	{	
		if (attacker.getChossenSkill() == Skills.ROCK_THROW)
		{
			if (getPlayable(victim.getPlayerIndex()).getPet().getSkill(Skills.SCISSORS_POKE).isCharging())
				return 3;
			if (getPlayable(victim.getPlayerIndex()).getPet().getSkill(Skills.ROCK_THROW).isCharging())
				return 2;
		} else if (attacker.getChossenSkill() == Skills.SCISSORS_POKE)
		{
			if (getPlayable(victim.getPlayerIndex()).getPet().getSkill(Skills.PAPER_CUT).isCharging())
				return 3;
			if (getPlayable(victim.getPlayerIndex()).getPet().getSkill(Skills.SCISSORS_POKE).isCharging())
				return 2;
		} else if (attacker.getChossenSkill() == Skills.PAPER_CUT)
		{
			if (getPlayable(victim.getPlayerIndex()).getPet().getSkill(Skills.ROCK_THROW).isCharging())
				return 3;
			if (getPlayable(victim.getPlayerIndex()).getPet().getSkill(Skills.PAPER_CUT).isCharging())
				return 2;
		} else if (attacker.getChossenSkill() == Skills.SHOOT_THE_MOON)
			return shootTheMoon(attacker, victim);
		else if (attacker.getChossenSkill() == Skills.REVERSAL_OF_FORTUNE)
			return reversalOfFortune(attacker);
		return 0;
	}

	/**
	 * If the attacker predicts the skill that opponent will use return the
	 * conditional damage.
	 * 
	 * @param attacker
	 * @param opponent
	 * @return
	 */
	private double shootTheMoon(PlayerInformation attacker, PlayerInformation victim)
	{
		if(getPlayable(attacker.getPlayerIndex()).getSkillPredeiction() == getPlayable(victim.getPlayerIndex()).getSkill())
			return 20;
		return 0;
	}

	private double reversalOfFortune(PlayerInformation attacker)
	{
		return attacker.getTotalRandomDiff();
	}

}
