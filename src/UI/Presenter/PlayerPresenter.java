package UI.Presenter;

import UI.view.PlayerView;
import javafx.scene.control.Button;

public interface PlayerPresenter {
	public void setView(PlayerView pView);
	public void selectSkill(Button button);
	void predictSkill(Button button);
	void nextTurn();
}
