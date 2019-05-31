package PlayableComponents;

import java.util.Observable;
import java.util.Observer;

import Controllers.BattleController;
import PetConponents.Pet;
import PetConponents.PetTypes;
import PetConponents.Skills;

@SuppressWarnings("deprecation")
public interface Playable extends Observer
{
	public String getPlayerName(); // Returns the player name associated with
												// this playable

	public String getPetName(); // Returns the pet name associated with this
											// playable

	public Pet getPet(); // Returns the player type associated with this

	public PlayerTypes getPlayerTypes(); // Returns the pet type associated
														// with this playable object

	public PetTypes getPetType(); // Returns the current hp

	public double getCurrentHp(); // Returns the skill chosen on the current
											// turn

	public void setSkill(Skills sklill);
	
	public Skills chooseSkill(); // Subtracts hp from the current hp

	public void updateHp(double hp); // Resets the hp back to the starting hp

	public void resetHp(); // Sets the hp

	public void setCurrentHp(double currentHp); // Returns true if the pet is
																// awake
	public Skills getSkill();
	
	public boolean isAwake(); // Returns the enumeration for the skill
										// prediction

	public Skills getSkillPredeiction(); // Returns the current recharges time
	
	public Skills setSkillPredeiction();								// for the specified skill

	public double calculateHpPercent(); // Returns the current percent of hp
													// the playable has
	
	public void reset(); // Resets the playable’s data to start another fight

	public void decrementRechargeTimes();

	public void resetRechargeTime(Skills skill);
	
	@Override
	public void update(Observable o, Object arg);

	public void setPredictedSkill(Skills skill);

}
