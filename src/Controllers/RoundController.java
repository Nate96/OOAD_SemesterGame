package Controllers;

import java.util.List;

import PetConponents.Skills;
import PlayableComponents.Playable;
import PlayableComponents.PlayerInformation;

public interface RoundController
{
	public void attack();
	public double calculateDamage(PlayerInformation attacker, PlayerInformation victim);
	public List<Playable> getPlayerList();
	public boolean setPlayerSkill(Skills skill, int playerIndex);
	public void setPlayerPredictedSkill(Skills skill, int playerIndextAttackList);
	public void startRound();
	//hello
}
