package UI.Presenter;

import java.io.IOException;

import PetConponents.PetTypes;
import PlayableComponents.PlayerTypes;
import UI.view.PreBattleView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public interface PreBattlePresenter {
	public void setView(PreBattleView preView);
	public void addClick(Button button);
	public boolean checkIfValid(TextField tempUsername, TextField tempPetName, PetTypes tempPetType, PlayerTypes tempPlayerType, Double tempHP);
	void createPlayer();
	void advanceToBattle() throws IOException;
	void setFightNum();
	void setRandSeed();
}
