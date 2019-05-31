package UI.view;

import PetConponents.PetTypes;
import PlayableComponents.PlayerTypes;
import UI.Presenter.PreBattlePresenter;
import edu.team06.player.Main;

public interface PreBattleView {
	void setPreBattlePresenter(PreBattlePresenter prePresenter);
	void updateUI();
	void bind();
	int getNumFightsFld();
	public PetTypes getPetType();
	public PlayerTypes getPlayerType();
	public int getRandNumFld();
	public double getPetStartHP();
	void setError(String errorMessage);
	String getPlayerName();
	String getPetName();
}
