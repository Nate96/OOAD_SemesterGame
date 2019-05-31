package Damage;

public class Damage
{
	private double random;
	private double conditional;

	public Damage(double random, double conditonal)
	{
		this.random = random;
		this.conditional = conditonal;
	}

	public double getRandom()
	{
		return random;
	}

	public double getTotalDamage()
	{
		return random + conditional;
	}
}
