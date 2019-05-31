package UI.view.impl;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.io.IOException;


import PetConponents.PetTypes;
import PlayableComponents.PlayerTypes;
import UI.Presenter.PreBattlePresenter;
import UI.view.PreBattleView;
import UIControllers.UIPreBattleSetupController;
import edu.team06.player.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;


public class PreBattleViewImpl implements PreBattleView {
	PreBattlePresenter prePresenter;
	private ObservableList<String> playerList = FXCollections.observableArrayList();
	@FXML
	private Button advanceToFightBtn;
	@FXML
	private TextField namePlayerFld;
	@FXML
	private TextField namePetFld;
	@FXML
	private Spinner<Double> startHPFld;
	@FXML
	private ListView<String> playerListBox;
	@FXML
	private ComboBox<PetTypes> petTypeCBox;
	@FXML
	private ComboBox<PlayerTypes> playerTypeCBox;
	@FXML
	private Spinner<Integer> numFightsFld;
	@FXML
	private Spinner<Integer> randNumFld;
	@FXML
	private Button createPlayerBtn;
	@FXML
	private Label preBattleErrorLbl;
	@Override
	public void setPreBattlePresenter(PreBattlePresenter prePresenter) 
	{
		this.prePresenter = prePresenter;
	}
	
	public void resetValues() {
		namePlayerFld.setText("");
		namePetFld.setText("");
		petTypeCBox.getSelectionModel().clearSelection();
		playerTypeCBox.getSelectionModel().clearSelection();
		startHPFld.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 1000000, 100));
	}

	@Override
	public void updateUI() {
		
		setError("");
		TextField tempUsername = namePlayerFld;
		TextField tempPetName = namePetFld;
		PetTypes tempPetType = petTypeCBox.getValue();
		PlayerTypes tempPlayerType = playerTypeCBox.getValue();
		Double tempHP = startHPFld.getValue();
		boolean validEntry = false;
		validEntry = prePresenter.checkIfValid(tempUsername, tempPetName, tempPetType, tempPlayerType, tempHP);
		if(validEntry)
		{
			playerList.add(namePlayerFld.getText());
			playerListBox.setItems(playerList);
			prePresenter.createPlayer();
			resetValues();
		}
		else
		{
			if(tempPetType == null)
			{
				setError("Select a valid pet type.");
			}
			else if(tempPlayerType == null)
			{
				setError("Select a valid player type.");
			}
			else if(tempUsername.getText().isEmpty())
			{
				setError("Enter your name.");
			}
			else if(tempPetName.getText().isEmpty())
			{
				setError("Enter your pet's name.");
			}
			
		}
		
	}
	
	@Override
	public void bind() {
		advanceToFightBtn.setOnAction(e -> {
			try {
				prePresenter.advanceToBattle();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		createPlayerBtn.setOnAction(e -> updateUI());
		numFightsFld.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000));
		randNumFld.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000000));
		playerTypeCBox.setItems(FXCollections.observableArrayList(PlayerTypes.HUMAN, PlayerTypes.COMPUTER));
		petTypeCBox.setItems(FXCollections.observableArrayList(PetTypes.INTELLIGENCE, PetTypes.SPEED, PetTypes.POWER));
		startHPFld.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 1000000, 100));
	}
	
	@FXML
	public void initalize()
	{
		
	}

	@Override
	public int getNumFightsFld() 
	{
		return numFightsFld.getValue();
	}
	
	@Override
	public int getRandNumFld()
	{
		return randNumFld.getValue();
	}
	
	@Override
	public PlayerTypes getPlayerType()
	{
		return playerTypeCBox.getValue();
	}
	
	@Override
	public PetTypes getPetType()
	{
		return petTypeCBox.getValue();
	}
	
	@Override
	public double getPetStartHP()
	{
		return startHPFld.getValue();
	}
	
	@Override
	public void setError(String errorMessage)
	{
		preBattleErrorLbl.setText(errorMessage);
	}
	
	@Override 
	public String getPlayerName()
	{
		return namePlayerFld.getText();
	}
	
	@Override
	public String getPetName()
	{
		return namePetFld.getText();
	}

}
