package PlayableComponents;

import java.util.Observable;

import PetConponents.Pet;
import PetConponents.PetTypes;
import PetConponents.Skills;

@SuppressWarnings("deprecation")
public abstract class Player implements Playable
{

	protected PlayerTypes playerType;
	private String name;
	private Pet pet;
	protected Skills predictedSkill;
	protected Skills choosenSkill;
	@SuppressWarnings("unused")
	private Observable subject;

	public Player(String name, Pet pet, PlayerTypes type)
	{
		this.pet = pet;
		this.name = name;
		this.playerType = type;
		this.choosenSkill = null;
		this.predictedSkill = null;
		this.subject = null;
	}

	public void setPredictedSkill(Skills skill)
	{
		predictedSkill = skill;
	}

	@Override
	public String getPlayerName()
	{
		return name;
	}

	@Override
	public String getPetName()
	{
		return pet.getName();
	}

	@Override
	public PlayerTypes getPlayerTypes()
	{
		return playerType;
	}

	@Override
	public PetTypes getPetType()
	{
		return pet.getType();
	}

	@Override
	public double getCurrentHp()
	{
		return pet.getHealth();
	}

	@Override
	public void updateHp(double hp)
	{
		pet.takeDamage(hp);
	}

	@Override
	public void resetHp()
	{
		pet.setMaxHealth(pet.getMaxHealth());
	}

	@Override
	public void setCurrentHp(double currentHp)
	{
		pet.takeDamage(currentHp);
	}

	@Override
	public boolean isAwake()
	{
		return pet.getHealth() > 0;
	}

	@Override
	public Skills getSkillPredeiction()
	{
		return predictedSkill;
	}

	@Override
	public double calculateHpPercent()
	{
		return (pet.getHealth() / pet.getMaxHealth()) * 100;
	}

	@Override
	public void decrementRechargeTimes()
	{
		pet.chargeSkills();
	}

	@Override
	public void resetRechargeTime(Skills skill)
	{
		pet.getSkill(skill).resetSkill();;
	}

	@Override
	public Pet getPet()
	{
		return pet;
	}

	@Override
	public void reset()
	{
		//pet.get
		pet.resetHealth();
		pet.getSkill(Skills.PAPER_CUT).resetSkill();
		pet.getSkill(Skills.ROCK_THROW).resetSkill();
		pet.getSkill(Skills.SCISSORS_POKE).resetSkill();
		pet.getSkill(Skills.REVERSAL_OF_FORTUNE).resetSkill();
		pet.getSkill(Skills.SHOOT_THE_MOON).resetSkill();
	}
}
