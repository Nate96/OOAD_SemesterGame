package PetConponents;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Pet
{
	private double currentHp, maxHp;

	private String name;

	private Map<Skills, PetSkill> skillMap;
	private PetTypes type;

	/**
	 * Takes in a name and type and creates a pet.
	 * 
	 * @param name
	 * @param type
	 */
	public Pet(String name, PetTypes type, double health)
	{
		this.currentHp = health;
		this.name = name;
		this.type = type;
		this.skillMap = new HashMap<Skills, PetSkill>();
		setMaxHealth(health);

		skillMap.put(Skills.ROCK_THROW, new PetSkill(Skills.ROCK_THROW));
		skillMap.put(Skills.PAPER_CUT, new PetSkill(Skills.PAPER_CUT));
		skillMap.put(Skills.SCISSORS_POKE, new PetSkill(Skills.SCISSORS_POKE));
		skillMap.put(Skills.SHOOT_THE_MOON,
				new PetSkill(Skills.SHOOT_THE_MOON));
		skillMap.put(Skills.REVERSAL_OF_FORTUNE,
				new PetSkill(Skills.REVERSAL_OF_FORTUNE));
	}

	public Double getHealth()
	{
		return currentHp;
	}

	public void setMaxHealth(double hp)
	{
		this.maxHp = hp;
	}

	public void resetHealth()
	{
		currentHp = maxHp;
	}

	public double getMaxHealth()
	{
		return this.maxHp;
	}

	public String getName()
	{
		return name;
	}

	public PetTypes getType()
	{
		return this.type;
	}

	public PetSkill getSkill(Skills skill)
	{
		return this.skillMap.get(skill);
	}

	public Map<Skills, PetSkill> getSkills()
	{
		return this.skillMap;
	}

	@Override
	public String toString()
	{
		return this.name + ": " + type.toString();
	}

	public Boolean isSleeping()
	{
		return this.currentHp <= 0;
	}
	
	public void useSkill(Skills skill)
	{
		getSkill(skill).setChargeTime();
	}

	/**
	 * Apply damage to the pet's health.
	 * 
	 * @param damage
	 * @return True if the pet is still awake and returns false if the pet is
	 *         asleep after taking damage.
	 */
	public void takeDamage(double damage)
	{
		this.currentHp -= damage;
		DecimalFormat df = new DecimalFormat("##.00");
		System.out.println(name + " has taken " + df.format(damage)
				+ " damage. \n" + name +"'s current health is now "
				+ df.format(currentHp));
	}

	/**
	 * Checks to see if the pet can attack with skill
	 * 
	 * @param skill
	 * @return True if the skill isn't on cool down and returns false if the
	 *         skill is on cool down.
	 */
	public boolean canUseSkill(Skills skill)
	{
		return !skillMap.get(skill).isCharging();
	}


	public void chargeSkills()
	{
		getSkill(Skills.PAPER_CUT).charge();
		getSkill(Skills.REVERSAL_OF_FORTUNE).charge();
		getSkill(Skills.ROCK_THROW).charge();
		getSkill(Skills.SCISSORS_POKE).charge();
		getSkill(Skills.SHOOT_THE_MOON).charge();
	}

}