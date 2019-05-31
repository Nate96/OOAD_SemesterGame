package PetConponents;

import java.util.HashMap;
import java.util.Map;

public class Opponent 
{
	private Pet pet; 
	private Map<Skills, PetSkill> usedSkills;
	
	public Opponent(String name, double startHealth)
	{
		this.pet = new Pet(name, null, startHealth);
		this.usedSkills = new HashMap<>();
	}
	
	public float getHealth()
	{
		return (float) ((pet.getHealth() / pet.getMaxHealth()) * 100);
	}
	
	public Map<Skills, PetSkill> getUsedSkills()
	{
		return usedSkills;
	}
	
	public PetSkill getUsedSkill(Skills skill)
	{
		return usedSkills.get(skill);
	}

}
