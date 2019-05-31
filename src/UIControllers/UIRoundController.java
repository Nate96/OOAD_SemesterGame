package UIControllers;

import java.util.List;
import java.util.Observable;

import BattleComponents.Fight;
import BattleComponents.Round;
import Controllers.RoundController;
import Damage.Damage;
import Damage.DamageCalculator;
import PetConponents.Skills;
import PlayableComponents.Playable;
import PlayableComponents.PlayerInformation;

@SuppressWarnings("deprecation")
public class UIRoundController extends Observable implements RoundController 
{
	private Round round;
	
	public UIRoundController(Fight fight, int roundCount)
	{
		this.round = new Round(fight, roundCount);
	}
	
	public Round getRound()
	{
		return round;
	}
	
	public DamageCalculator getDamageCalculator()
	{
		return (DamageCalculator) round.getFight().getBattle().getCalculator();
	}
	
	public List<Playable> getPlayerList()
	{
		return this.round.getFight().getBattle().getPlayerList();
	}
	
	@Override
	public void startRound()
	{
		setChanged();
		notifyObservers(round.getStartEvent());
		clearChanged();
	}

	@Override
	public boolean setPlayerSkill(Skills skill, int playerIndex)
	{
		if(getPlayerList().get(playerIndex).getPet().canUseSkill(skill))
		{
			getPlayerList().get(playerIndex).setSkill(skill);
			getPlayerList().get(playerIndex).getPet().chargeSkills();
			round.getFight().getBattle().getPlayerList().get(round.getAttackList().get(playerIndex).getPlayerIndex()).getPet().useSkill(skill);
			return true;
		}
		return false;	
	}

	@Override
	public void attack()
	{
		double damage = 0.0;
		getDamageCalculator().setRoundController(this);
		
		for (int attackerIndex = 0; attackerIndex < round.getAttackList().size(); attackerIndex++)
		{
			int j = (attackerIndex + 1) % round.getAttackList().size();
			int victimIndex = round.getAttackList().get(j).getPlayerIndex();
			
			getPlayerList().get(victimIndex).getPet().takeDamage(damage = calculateDamage(round.getAttackList().get(attackerIndex), round.getAttackList().get(j)));
			
			round.getAttackList().get(victimIndex).updateTotalRandomDiff(-damage);
			round.getAttackList().get(attackerIndex).updateTotalRandomDiff(damage);
		}
	}

	@Override
	public double calculateDamage(PlayerInformation attacker, PlayerInformation victim)
	{
		Damage damage = getDamageCalculator().calculteDamage(attacker, victim);
		return damage.getTotalDamage();
	}

	@Override
	public void setPlayerPredictedSkill(Skills skill, int playerIndextAttackList) {
		getPlayerList().get(round.getAttackList().get(playerIndextAttackList).getPlayerIndex()).setSkill(skill);
		
	}

}
