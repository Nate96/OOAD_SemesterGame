package UI.Presenter.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import PetConponents.Pet;
import PetConponents.PetTypes;
import PlayableComponents.AIPlayer;
import PlayableComponents.Human;
import PlayableComponents.Playable;
import PlayableComponents.PlayerTypes;
import UI.Presenter.PreBattlePresenter;
import UI.view.PreBattleView;
import edu.team06.player.Main;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import UIControllers.UIPreBattleSetupController;

public class PreBattlePresenterImpl implements PreBattlePresenter{
	PreBattleView preView;
	UIPreBattleSetupController pbs;
	List<Playable> tempPlayerList;
	int numFights = 0;
	int randSeed = 1;
	Main main;
	
	public PreBattlePresenterImpl(UIControllers.UIPreBattleSetupController pbs, Main main)
	{
		this.pbs = pbs;
		tempPlayerList = new ArrayList<Playable>();
		this.main = main;
	}
	
	@Override
	public void setView(PreBattleView preView) {
		// TODO Auto-generated method stub
		this.preView = preView;
	}
	@Override
	public void addClick(Button button) {
		// TODO Auto-generated method stub
		System.out.println("This Works");
	}
	
	@Override
	public boolean checkIfValid(TextField tempUsername, TextField tempPetName, PetTypes tempPetType, PlayerTypes tempPlayerType, Double tempHP) {
		if(tempUsername.getText().isEmpty() || tempPetName.getText().isEmpty() || tempPetType == null || tempPlayerType == null || tempHP == null)
		{
			return false;
		}
		return true;
	}
	
	@Override
	public void createPlayer()
	{
		String playerName = preView.getPlayerName();
		String petName = preView.getPetName();
		PetTypes petType = preView.getPetType();
		PlayerTypes playerType = preView.getPlayerType();
		double startHp = preView.getPetStartHP();
		Pet tempPet = new Pet(petName, petType, startHp);
		Playable tempPlayer;
		switch(playerType)
		{
		case HUMAN:
			tempPlayer = new Human(playerName, tempPet);
			tempPlayerList.add(tempPlayer);
			break;
		case COMPUTER:
			tempPlayer = new AIPlayer(playerName, tempPet);
			tempPlayerList.add(tempPlayer);
			break;
		}
		
	}
	@Override
	public void setFightNum()
	{
		numFights = preView.getNumFightsFld();
	}
	@Override
	public void setRandSeed()
	{
		randSeed = preView.getRandNumFld();
	}
	@Override
	public void advanceToBattle() throws IOException
	{
		setFightNum();
		setRandSeed();
		if(tempPlayerList.size() > 1 && numFights > 0)
		{
			try {
				pbs.setup(tempPlayerList, numFights, randSeed);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			main.PlayerForm(pbs.getSettings());
		}
	}

}
