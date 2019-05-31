package UI.view;
import PetConponents.PetTypes;
import PlayableComponents.Playable;
import PlayableComponents.PlayerInformation;
import UI.Presenter.PlayerPresenter;

public interface PlayerView {
	void SetPlayerPresenter(PlayerPresenter pPresenter);
	void bind();
	public void updateUI(PlayerInformation playerInfo, Playable player, Playable opponent);
	void updateRandNum(float randNum);
	void updateFightNum(int currentFightNum, int totalFightNum);
}
