package UI.view.impl;

import PetConponents.Pet;
import PetConponents.PetTypes;
import PetConponents.Skills;
import PlayableComponents.Playable;
import PlayableComponents.PlayerInformation;
import UI.Presenter.PlayerPresenter;
import UI.view.PlayerView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PlayerViewImpl implements PlayerView{
	PlayerPresenter pPresenter;
	
	@FXML 
	private Label fightLbl;
	@FXML
	private Button MoonBtn;
	@FXML
	private TextField MoonFld;
	@FXML
	private GridPane ScoreBoardGrid;
	@FXML
	private Button ReverseBtn;
	@FXML
	private TextField ReverseFld;
	@FXML
	private Button RockBtn;
	@FXML
	private TextField RockFld;
	@FXML
	private Button ScissorsBtn;
	@FXML
	private TextField ScissorsFld;
	@FXML
	private Button PaperBtn;
	@FXML
	private TextField PaperFld;
	@FXML
	private TextField CurrentHealthFld;
	@FXML
	private TextField RandDiffFld;
	@FXML
	private TextField RandNumFld;
	@FXML
	private Button NextTurnBtn;
	@FXML 
	private TextField PlayerNameFld;
	@FXML
	private TextField PetNameFld;
	@FXML
	private TextField CurrentOpponentFld;
	@FXML
	private TextField CurrentOppPetTypeFld;
	@FXML
	private Button PredictRockBtn;
	@FXML
	private Button PredictPaperBtn;
	@FXML
	private Button PredictScissorsBtn;
	@FXML
	private Button PredictMoonBtn;
	@FXML
	private Button PredictReverseBtn;
	
	
	
	
	
	
	
	@Override
	public void SetPlayerPresenter(PlayerPresenter pPresenter) {
		this.pPresenter = pPresenter;
	}
	
	@Override
	public void updateUI(PlayerInformation playerInfo, Playable player, Playable opponent) 
	{
		PlayerNameFld.setText(player.getPlayerName());
		PetNameFld.setText(player.getPetName());				//IT IS THE .setText() method, its not null.
		CurrentHealthFld.setText(player.getPet().getHealth().toString());
		RandDiffFld.setText(Double.toString(playerInfo.getTotalRandomDiff()));
		
//		CondDamageDealtFld.setText(Float.toString(condDamageDealt));
//		RandDamageDealtFld.setText(Float.toString(RandDamageDealt));
//		CondDamageTakenFld.setText(Float.toString(CondDamageTkn));
//		RandDamageTakenFld.setText(Float.toString(RandDamageTkn));
//		DamageTakenPrevFld.setText(Float.toString(DamageTakenPrev));
//		DamageDealtPrevFld.setText(Float.toString(DamageGivePrev));
		
		MoonFld.setText(displayRecharge(player.getPet(), Skills.SHOOT_THE_MOON).toString());
		ReverseFld.setText(displayRecharge(player.getPet(), Skills.REVERSAL_OF_FORTUNE).toString());
		RockFld.setText(displayRecharge(player.getPet(), Skills.ROCK_THROW).toString());
		PaperFld.setText(displayRecharge(player.getPet(), Skills.PAPER_CUT).toString());
		ScissorsFld.setText(displayRecharge(player.getPet(), Skills.SCISSORS_POKE).toString());
		CurrentOpponentFld.setText(opponent.getPlayerName());
		CurrentOppPetTypeFld.setText(opponent.getPet().getType().toString());
	}
	@Override
	public void updateFightNum(int currentFightNum, int totalFightNum)
	{
		fightLbl.setText(Integer.toString(currentFightNum) + " / " + Integer.toString(totalFightNum));
		
	}
	@Override
	public void updateRandNum(float randNum)
	{
		RandNumFld.setText(Float.toString(randNum));
	}
	
	@Override
	public void bind() {
		MoonBtn.setOnAction(e -> pPresenter.selectSkill(MoonBtn));
		RockBtn.setOnAction(e -> pPresenter.selectSkill(RockBtn));
		PaperBtn.setOnAction(e -> pPresenter.selectSkill(PaperBtn));
		ScissorsBtn.setOnAction(e -> pPresenter.selectSkill(ScissorsBtn));
		ReverseBtn.setOnAction(e -> pPresenter.selectSkill(ReverseBtn));
		PredictRockBtn.setOnAction(e -> pPresenter.predictSkill(PredictRockBtn));
		PredictPaperBtn.setOnAction(e -> pPresenter.predictSkill(PredictPaperBtn));
		PredictScissorsBtn.setOnAction(e -> pPresenter.predictSkill(PredictScissorsBtn));
		PredictMoonBtn.setOnAction(e -> pPresenter.predictSkill(PredictMoonBtn));
		PredictReverseBtn.setOnAction(e -> pPresenter.predictSkill(PredictReverseBtn));
		NextTurnBtn.setOnAction(e -> pPresenter.nextTurn());
	}
	
	public Integer displayRecharge(Pet pet, Skills skill)
	{
		if(pet.canUseSkill(skill))
			return 0;
		else
			return pet.getSkill(skill).getCurrentRechargeTime();
	}

}
