package Controllers;

import java.util.List;

import Events.PlayerEventInformation;
import PlayableComponents.PlayerInformation;

public interface BattleController
{
	public void startBattle();
	public List<PlayerInformation> determineWinners();
	public List<PlayerEventInformation> buildInformationList();
}
