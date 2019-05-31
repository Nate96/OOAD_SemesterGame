package PetConponents;

public class PetSkill
{
	private Skills skill;
	private int currentRechargeTime;

	public PetSkill(Skills skill)
	{
		this.skill = skill;
		this.currentRechargeTime = 0;
	}

	public Skills getSkill()
	{
		return this.skill;
	}

	public int getCurrentRechargeTime()
	{
		return this.currentRechargeTime;
	}

	// pre con: currentRechargeTime < 0;
	public void setChargeTime()
	{
		this.currentRechargeTime = this.skill.getRecharge();
	}
	
	public void resetSkill()
	{
		currentRechargeTime = 0;
	}

	public boolean isCharging()
	{
		return this.currentRechargeTime <= this.skill.getRecharge()
				&& this.currentRechargeTime > 0;
	}

	// don't need to check if rechargeTime = 0 it doesn't matter with
	// isCharging()
	public void charge()
	{
		this.currentRechargeTime--;
	}

	@Override
	public String toString()
	{
		return skill.toString();
	}
}
