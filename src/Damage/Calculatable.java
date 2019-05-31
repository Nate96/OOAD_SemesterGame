package Damage;

import PlayableComponents.PlayerInformation;

public interface Calculatable
{

	/**
	 * Calculates the damage.
	 * 
	 * @param int
	 * @return the damage
	 */
	public Damage calculteDamage(PlayerInformation attacker, PlayerInformation victim);

}