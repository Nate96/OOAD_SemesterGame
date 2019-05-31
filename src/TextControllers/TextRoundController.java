package TextControllers;

import java.util.List;
import java.util.Observable;

import BattleComponents.Fight;
import BattleComponents.Round;
import Controllers.RoundController;
import Damage.Damage;
import Damage.DamageCalculator;
import Events.AttackEvent;
import Events.AttackEvent.AttackEventBuilder;
import PetConponents.Pet;
import PetConponents.Skills;
import PlayableComponents.Playable;
import PlayableComponents.PlayerInformation;
import PlayableComponents.PlayerTypes;

@SuppressWarnings("deprecation")
public class TextRoundController extends Observable implements RoundController
{
	private Round round;
	
	public TextRoundController(Fight fight, int roundCount)
	{
		round = new Round(fight, roundCount);
		
		List<Playable> playerList = round.getFight().getBattle().getPlayerList();
		
		for(Playable playable : playerList)
		{
			this.addObserver(playable);
		}
		
	}

	public DamageCalculator getDamageCalculator()
	{
		return (DamageCalculator) round.getFight().getBattle().getCalculator();
	}

	@Override
	public List<Playable> getPlayerList()
	{
		return this.round.getFight().getBattle().getPlayerList();
	}

	public List<PlayerInformation> getInformationList()
	{
		return round.getFight().getBattle().getPlayerInformationList();
	}

	@Override
	public void startRound()
	{
		setChanged();
		notifyObservers(round.getStartEvent()); //Right here ultron is going first.
		clearChanged();
		
		run();

	}
	
	public void run()
	{
		for (int i = 0; i < round.getAttackList().size(); i++)
		{
			if(round.getFight().getBattle().getPlayerList().get(round.getAttackList().get(i).getPlayerIndex()).getPlayerTypes() == PlayerTypes.HUMAN)
				displayRecharge(round.getFight().getBattle().getPlayerList().get(round.getAttackList().get(i).getPlayerIndex()).getPet());
			
			Skills choosenSkill = round.getFight().getBattle().getPlayerList().get(round.getAttackList().get(i).getPlayerIndex()).chooseSkill();
				
				while(!setPlayerSkill(choosenSkill, round.getAttackList().get(i).getPlayerIndex()))
				{
					if(round.getFight().getBattle().getPlayerList().get(round.getAttackList().get(i).getPlayerIndex()).getPlayerTypes() == PlayerTypes.HUMAN)
						System.out.println("Skill is on cooldown. Please Try Again");
					choosenSkill = round.getFight().getBattle().getPlayerList().get(round.getAttackList().get(i).getPlayerIndex()).chooseSkill();
				}
				
				if(choosenSkill == Skills.SHOOT_THE_MOON)
				{
					setPlayerPredictedSkill(round.getFight().getBattle().getPlayerList().get(i).chooseSkill(), i);
				}

			
			AttackEventBuilder builder = new AttackEvent.AttackEventBuilder();
			builder.withAttackingSkillChoice(round.getFight().getBattle().getPlayerList().get(round.getAttackList().get(i).getPlayerIndex()).getSkill());
			builder.withAttackPlableIndex(i);
			
			AttackEvent attackEvent = builder.buildAttackEvent();
			setChanged();
			notifyObservers(attackEvent); //Something (bad) is happening here...
			clearChanged();
		}
		
		attack();
		
	}
	
	@Override
	public boolean setPlayerSkill(Skills skill, int playerIndex)
	{
		if(getPlayerList().get(playerIndex).getPet().canUseSkill(skill))
		{
			getPlayerList().get(playerIndex).setSkill(skill);
			getPlayerList().get(playerIndex).getPet().chargeSkills();
			round.getFight().getBattle().getPlayerList().get(playerIndex).getPet().useSkill(skill);
			return true;
		}
		return false;
			
	}
	
	/**
	 * Iterates through the attack list and has all the pets attack there proper target.
	 */
	@Override
	public void attack()
	{
		double damage = 0.0;
		getDamageCalculator().setRoundController(this);
		
		for (int attackerIndex = 0; attackerIndex < round.getAttackList().size(); attackerIndex++)
		{
			int attackListVictimIndex = (attackerIndex + 1) % round.getAttackList().size();
			int playerListVictimIndex = round.getAttackList().get(attackListVictimIndex).getPlayerIndex();
			String attackerPlayerName = round.getFight().getBattle().getPlayerList().get(round.getAttackList().get(attackerIndex).getPlayerIndex()).getPlayerName();
			String attackerChosenSkill = round.getFight().getBattle().getPlayerList().get(round.getAttackList().get(attackerIndex).getPlayerIndex()).getSkill().toString();
			String victimName = round.getFight().getBattle().getPlayerList().get(playerListVictimIndex).getPlayerName();
			
			System.out.println();
			System.out.println(attackerPlayerName + " has chosen " + attackerChosenSkill + " and has applied damage to "  + victimName);
			getPlayerList().get(playerListVictimIndex).getPet().takeDamage(damage = calculateDamage(round.getAttackList().get(attackerIndex), round.getAttackList().get(attackListVictimIndex)));
			
			if(round.getFight().getBattle().getPlayerList().get(playerListVictimIndex).getCurrentHp() < 0)
				System.out.println(round.getFight().getBattle().getPlayerList().get(playerListVictimIndex).getPetName() + " has fallen asleep!");
			
			round.getAttackList().get(attackListVictimIndex).updateTotalRandomDiff(-damage);
			round.getAttackList().get(attackerIndex).updateTotalRandomDiff(damage);
		}
	}

	/**
	 * Creates a Damage class and adds the random Damage to the RandomDamageDiff ArrayList.
	 * @param index
	 * @return
	 */
	@Override
	public double calculateDamage(PlayerInformation attacker, PlayerInformation victim)
	{
		attacker.setChosenSkill(getPlayerList().get(victim.getPlayerIndex()).getSkill());
		victim.setChosenSkill(getPlayerList().get(attacker.getPlayerIndex()).getSkill());
		
		Damage damage = getDamageCalculator().calculteDamage(attacker, victim);
		return damage.getTotalDamage();

	}

	@Override
	public void setPlayerPredictedSkill(Skills skill, int playerIndextAttackList) {
		getPlayerList().get(round.getAttackList().get(playerIndextAttackList).getPlayerIndex()).setSkill(skill);
		
	}
	
	public void displayRecharge(Pet pet)
	{
		Skills[] temp = Skills.values();
		System.out.println();
		System.out.println(pet.getName() + " has the following recharges.");
		for(int i = 0; i < 5; i++)
		{
			if (pet.canUseSkill(temp[i]))
				System.out.printf("%-22s %5s %n", temp[i].toString() + ": ",  0);
			else
				System.out.printf("%-22s %5s %n", temp[i].toString() + ": ",  pet.getSkill(temp[i]).getCurrentRechargeTime());
				//System.out.printf(temp[i].name().toString() + " : " + pet.getSkill(temp[i]).getCurrentRechargeTime());
		}
	}


}
