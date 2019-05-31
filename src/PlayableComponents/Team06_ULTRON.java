
package PlayableComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import Events.*;
import PetConponents.Pet;
import PetConponents.PetTypes;
import PetConponents.Skills;
//import edu.dselent.player.PetInstance;
//import edu.dselent.settings.PlayerInfo;
//import edu.dselent.skill.Skills;

@SuppressWarnings("deprecation")
public class Team06_ULTRON extends Player
{
	private Player opponent;
	private List<Double> randomDiff;
	private double currentHealth;

	public Team06_ULTRON(String name, Pet pet)
	{
		super("Ultron", new Pet("Ultron", PetTypes.INTELLIGENCE, pet.getMaxHealth()), PlayerTypes.ULTRON);
		this.currentHealth = pet.getMaxHealth();
		this.playerType = PlayerTypes.ULTRON;
		this.randomDiff = new ArrayList<>();
	}
	
	public void findOpponent(List<PlayerEventInformation> playerInfo)
	{
		for(int i = 0; i < playerInfo.size(); i++)
		{
			if(playerInfo.get(i).getPetName().equals(this.getPet().getName()))
			{
				int opponentIndex = (i + 1) % playerInfo.size();
				
				if(playerInfo.get(i).getPlayerType() == PlayerTypes.COMPUTER)
					this.opponent = new AIPlayer(null,  new Pet(playerInfo.get(opponentIndex).getPetName(), null , playerInfo.get(opponentIndex).getStartingHp()));
				else
					this.opponent = new Human(null,  new Pet(playerInfo.get(opponentIndex).getPetName(), null , playerInfo.get(opponentIndex).getStartingHp()));
			}
				
		}
	}
	
	@Override
	public void update(Observable o, Object arg)
	{
		if (arg instanceof AttackEvent) 
		{
			opponent.getPet().chargeSkills();
			opponent.getPet().useSkill(((AttackEvent) arg).getAttackingSkillChoice());
			
			double damageTaken = currentHealth - getCurrentHp();
			currentHealth = currentHealth - damageTaken;
			randomDiff.add(0 - damageTaken);
			
		}
		if(arg instanceof RoundStartEvent)  
		{
			
		}
		if (arg instanceof FightStartEvent) 
		{	
			findOpponent(((FightStartEvent) arg).getPlayerEventInformation());
		}
	}
	
	
	@Override
	public Skills chooseSkill()
	{
		Skills skill = null;
		if(opponent.getPet().getSkill(Skills.SHOOT_THE_MOON).isCharging())
			return ChooseSkillWithMaxDamage();			
		
		if(skill == null)
		{
			skill = ChooseRandomSkill(this);
			while(skill == Skills.SHOOT_THE_MOON || skill == Skills.REVERSAL_OF_FORTUNE)
			{
				skill = ChooseSkillWithMaxDamage();
			}
		}
		 return skill;
	}
	
	public boolean chooseShootTheMoon()
	{
		return false;
	}
	
	public boolean chooseReversalOfFortune()
	{
		return false;
	}
	
	public Skills ChooseSkillWithMaxDamage()
	{
		if(opponent.getPet().getSkill(Skills.ROCK_THROW).isCharging() && getPet().canUseSkill(Skills.PAPER_CUT))
			return Skills.PAPER_CUT;
		if (opponent.getPet().getSkill(Skills.ROCK_THROW).isCharging() && getPet().canUseSkill(Skills.ROCK_THROW))
				return Skills.ROCK_THROW;
		if(opponent.getPet().getSkill(Skills.SCISSORS_POKE).isCharging() && getPet().canUseSkill(Skills.ROCK_THROW))
			return Skills.ROCK_THROW;
		if(opponent.getPet().getSkill(Skills.SCISSORS_POKE).isCharging() && getPet().canUseSkill(Skills.SCISSORS_POKE))
			return Skills.SCISSORS_POKE;
		if(opponent.getPet().getSkill(Skills.PAPER_CUT).isCharging() && getPet().canUseSkill(Skills.SCISSORS_POKE))
			return Skills.SCISSORS_POKE;
		if(opponent.getPet().getSkill(Skills.PAPER_CUT).isCharging() && getPet().canUseSkill(Skills.PAPER_CUT))
			return Skills.PAPER_CUT;
		
		
		return ChooseRandomSkill(this);
	}
	
	
	public Skills ChooseRandomSkill(Player p)
	{
		Skills skill = null; 
		Random random = new Random();
		int min = 1, max = 5;
		
		int skillValue =  (random.nextInt((max - min) + 1) + min);
		
		if(skillValue == 1)
			skill = Skills.ROCK_THROW;
		else if(skillValue == 2)
			skill =  Skills.PAPER_CUT;
		else if(skillValue == 3)
			skill = Skills.SCISSORS_POKE;
		else if(skillValue == 4)
			skill = Skills.SHOOT_THE_MOON;
		else if(skillValue == 5)
			skill = Skills.REVERSAL_OF_FORTUNE;
		
		while(p.getPet().getSkill(skill).isCharging())
		{	
			if(skillValue == 1)
				skill = Skills.ROCK_THROW;
			else if(skillValue == 2)
				skill =  Skills.PAPER_CUT;
			else if(skillValue == 3)
				skill = Skills.SCISSORS_POKE;
			else if(skillValue == 4)
				skill = Skills.SHOOT_THE_MOON;
			else
				skill = Skills.REVERSAL_OF_FORTUNE;
			skillValue =  (random.nextInt((max - min) + 1) + min);
		}
		return skill;
	}
	
	
	public Skills setSkillPredeiction() 
	{
		return this.predictedSkill = ChooseRandomSkill(opponent);
		
	}
	
	public Skills getPredictedSkill()
	{
		return this.predictedSkill;
	}

	@Override
	public void setSkill(Skills skill) {
		this.choosenSkill = skill;
		
	}

	@Override
	public Skills getSkill() {
		return this.choosenSkill;
	}
	
}