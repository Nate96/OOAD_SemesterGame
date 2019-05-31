package Events;

import PetConponents.Skills;

public class AttackEvent extends BaseEvent
{

	private Integer attackingPlayableIndex;
	private Integer victimPlayableIndex;
	private Skills attackingSkillChoice;
	private Skills predictedSkillEnum;
	private Double randomDamage;
	private Double conditionalDamage;

	// default values for Integers and Skills are null
	private final double RAMDONE_DAMAGE_DEFAULT = 0;
	private final double CONDITIONAL_DAMAGE_DEFAULT = 0;

	private AttackEvent(AttackEventBuilder builder) // Object needs to be a
																	// builder
	{
		super(EventTypes.ATTACK);
		if (builder.attackingPlayableIndex != null)
			this.attackingPlayableIndex = builder.attackingPlayableIndex;
		if (builder.victimPlayableIndex != null)
			this.victimPlayableIndex = builder.victimPlayableIndex;
		if (builder.attackingSkillChoice != null)
			this.attackingSkillChoice = builder.attackingSkillChoice;
		if (builder.predictedSkillEnum != null)
			this.predictedSkillEnum = builder.predictedSkillEnum;
		if (builder.randomDamage != null)
			this.randomDamage = builder.randomDamage;
		else
			this.randomDamage = this.RAMDONE_DAMAGE_DEFAULT;
		if (builder.conditionalDamage != null)
			this.conditionalDamage = builder.conditionalDamage;
		else
			this.conditionalDamage = this.CONDITIONAL_DAMAGE_DEFAULT;
	}

	public Integer getAttackingPlayableIndex()
	{
		return attackingPlayableIndex;
	}

	public Integer getVictimPlayableIndex()
	{
		return victimPlayableIndex;
	}

	public Skills getAttackingSkillChoice()
	{
		return attackingSkillChoice;
	}

	public Skills getPredictedSkillEnum()
	{
		return predictedSkillEnum;
	}

	public double getRandomDamage()
	{
		return randomDamage;
	}

	public double getConditionalDamage()
	{
		return conditionalDamage;
	}

	@Override
	public String toString()
	{
		return "Attack Event";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(CONDITIONAL_DAMAGE_DEFAULT);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(RAMDONE_DAMAGE_DEFAULT);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((attackingPlayableIndex == null) ? 0
				: attackingPlayableIndex.hashCode());
		result = prime * result + ((attackingSkillChoice == null) ? 0
				: attackingSkillChoice.hashCode());
		result = prime * result + ((conditionalDamage == null) ? 0
				: conditionalDamage.hashCode());
		result = prime * result + ((predictedSkillEnum == null) ? 0
				: predictedSkillEnum.hashCode());
		result = prime * result
				+ ((randomDamage == null) ? 0 : randomDamage.hashCode());
		result = prime * result + ((victimPlayableIndex == null) ? 0
				: victimPlayableIndex.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AttackEvent other = (AttackEvent) obj;
		if (Double.doubleToLongBits(CONDITIONAL_DAMAGE_DEFAULT) != Double
				.doubleToLongBits(other.CONDITIONAL_DAMAGE_DEFAULT))
			return false;
		if (Double.doubleToLongBits(RAMDONE_DAMAGE_DEFAULT) != Double
				.doubleToLongBits(other.RAMDONE_DAMAGE_DEFAULT))
			return false;
		if (attackingPlayableIndex == null)
		{
			if (other.attackingPlayableIndex != null)
				return false;
		} else if (!attackingPlayableIndex.equals(other.attackingPlayableIndex))
			return false;
		if (attackingSkillChoice != other.attackingSkillChoice)
			return false;
		if (conditionalDamage == null)
		{
			if (other.conditionalDamage != null)
				return false;
		} else if (!conditionalDamage.equals(other.conditionalDamage))
			return false;
		if (predictedSkillEnum != other.predictedSkillEnum)
			return false;
		if (randomDamage == null)
		{
			if (other.randomDamage != null)
				return false;
		} else if (!randomDamage.equals(other.randomDamage))
			return false;
		if (victimPlayableIndex == null)
		{
			if (other.victimPlayableIndex != null)
				return false;
		} else if (!victimPlayableIndex.equals(other.victimPlayableIndex))
			return false;
		return true;
	}

	////////////////////////////////////////////////////////////////////////////////////
	// builder

	public static class AttackEventBuilder
	{
		private Integer attackingPlayableIndex;
		private Integer victimPlayableIndex;
		private Skills attackingSkillChoice;
		private Skills predictedSkillEnum;
		private Double randomDamage;
		private Double conditionalDamage;

		public AttackEventBuilder withAttackPlableIndex(int index)
		{
			this.attackingPlayableIndex = index;
			return this;
		}

		public AttackEventBuilder withVictimPlayableIndex(int index)
		{
			this.victimPlayableIndex = index;
			return this;
		}

		public AttackEventBuilder withAttackingSkillChoice(Skills skill)
		{
			this.attackingSkillChoice = skill;
			return this;
		}

		public AttackEventBuilder withPredictedSkill(Skills skill)
		{
			this.predictedSkillEnum = skill;
			return this;
		}

		public AttackEventBuilder withRandomDamage(double damage)
		{
			this.randomDamage = damage;
			return this;
		}

		public AttackEventBuilder withConditionalDamage(double damage)
		{
			this.conditionalDamage = damage;
			return this;
		}

		public AttackEvent buildAttackEvent()
		{
			return new AttackEvent(this);
		}
	}

}
