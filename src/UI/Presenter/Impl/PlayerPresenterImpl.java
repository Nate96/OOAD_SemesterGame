package UI.Presenter.Impl;

import BattleComponents.Fight;
import Controllers.BattleController;
import PetConponents.Skills;
import PlayableComponents.Playable;
import PlayableComponents.PlayerInformation;
import PlayableComponents.PlayerTypes;
import UI.Presenter.PlayerPresenter;
import UI.view.PlayerView;
import UIControllers.UIBattleController;
import UIControllers.UIFightController;
import UIControllers.UIRoundController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class PlayerPresenterImpl implements PlayerPresenter{
	private PlayerView pView;
	private UIBattleController bc;
	private UIFightController fc;
	private UIRoundController rc;
	private Playable currentPlayer;
	private PlayerInformation currentPlayerInfo;
	private int currentPlayerIndex;
	private int fightCount = 0;
	private int roundCount = 0;
	boolean validSkill = false;
	public PlayerPresenterImpl(BattleController uIBc) {
		this.bc = (UIBattleController)uIBc;
		bc.startBattle();
		fc = new UIFightController(bc.getBattle(), fightCount++);
		bc.getBattle().getFightList().add(fc);
		rc = new UIRoundController(fc.getFight(), roundCount++);
		fc.getFight().getRoundList().add(rc);
	}
	@Override
	public void setView(PlayerView pView) {
		this.pView = pView;
		setup();
		
	}
	@Override
	public void selectSkill(Button button)
	{
		int playerIndex = rc.getRound().getAttackList().get(currentPlayerIndex).getPlayerIndex();
		if(bc.getBattle().getPlayerList().get(playerIndex).getPlayerTypes() == PlayerTypes.HUMAN)
		{
				switch(button.getId())
			{
			case "RockBtn":
				 validSkill = rc.setPlayerSkill(Skills.ROCK_THROW, playerIndex);
				 break;
			case "PaperBtn":
				validSkill = rc.setPlayerSkill(Skills.PAPER_CUT, playerIndex);
				break;
			case "ScissorsBtn":
				validSkill = rc.setPlayerSkill(Skills.SCISSORS_POKE, playerIndex);
				break;
			case "MoonBtn":
				validSkill = rc.setPlayerSkill(Skills.SHOOT_THE_MOON, playerIndex);
				break;
			case "ReverseBtn":
				validSkill = rc.setPlayerSkill(Skills.REVERSAL_OF_FORTUNE, playerIndex);
				break;
			}
		}
			
	}
	
	@Override
	
	public void predictSkill(Button button)
	{
		if(currentPlayer.getPlayerTypes() == PlayerTypes.HUMAN)
		{
			if(currentPlayerInfo.getChossenSkill() == Skills.SHOOT_THE_MOON)
			{
				switch(button.getId())
				{
				case "PredictRockBtn":
					rc.setPlayerPredictedSkill(Skills.ROCK_THROW, currentPlayerIndex);
					break;
				case "PredictPaperBtn":
					rc.setPlayerPredictedSkill(Skills.PAPER_CUT, currentPlayerIndex);
					break;
				case "PredictScissorsBtn":
					rc.setPlayerPredictedSkill(Skills.SCISSORS_POKE, currentPlayerIndex);
					break;
				case "PredictMoonBtn":
					rc.setPlayerPredictedSkill(Skills.SHOOT_THE_MOON, currentPlayerIndex);
					break;
				case "PredictReverseBtn":
					rc.setPlayerPredictedSkill(Skills.REVERSAL_OF_FORTUNE, currentPlayerIndex);
					break;
				}
			}
		}		
		
	}
	public void setup()
	{
		validSkill = true;
		currentPlayerInfo = bc.getBattle().getPlayerInformationList().get(0);
		currentPlayer = bc.getBattle().getPlayerList().get(0);
		currentPlayerIndex = 0;
		pView.updateUI(currentPlayerInfo, currentPlayer, bc.getBattle().getPlayerList().get(1));
	}
	
	@Override
	public void nextTurn()
	{
		if(currentPlayer.getPlayerTypes() == PlayerTypes.COMPUTER)
		{
			int playerIndex = rc.getRound().getAttackList().get(currentPlayerIndex).getPlayerIndex();
			rc.setPlayerSkill(bc.getBattle().getPlayerList().get(playerIndex).chooseSkill(), playerIndex);
			currentPlayer.setPredictedSkill(currentPlayer.chooseSkill());
			validSkill = true;
		}
		if(validSkill)
		{
			if(currentPlayerIndex+1 >= rc.getRound().getAttackList().size())
			{
						if(!fc.isDone())
						{
							
							rc.attack();
							//rc.chargeSkills(bc.getBattle().getPlayerList());
							fc.getFight().getRoundList().add(new UIRoundController(fc.getFight(), roundCount));
							fc.getFight().getRoundList().get(roundCount).startRound();
							rc = (UIRoundController) fc.getFight().getRoundList().get(roundCount++);
							currentPlayerIndex = 0;
							int index = rc.getRound().getAttackList().get(currentPlayerIndex).getPlayerIndex();
							currentPlayer = bc.getBattle().getPlayerList().get(index);
							index = currentPlayerIndex + 1 < rc.getRound().getAttackList().size() ? 
								 rc.getRound().getAttackList().get(currentPlayerIndex+1).getPlayerIndex() : 0;
							pView.updateUI(rc.getRound().getAttackList().get(currentPlayerIndex), currentPlayer, bc.getBattle().getPlayerList().get(index));
						
							
							
						}
						else if(roundCount < fc.getFight().getBattle().getNumberOfFights())
						{
							fc = new UIFightController(fc.getFight().getBattle(), fightCount++);
							fc.getFight().getRoundList().add(new UIRoundController(fc.getFight(), roundCount));
							fc.getFight().getRoundList().get(roundCount).startRound();
							rc = (UIRoundController) fc.getFight().getRoundList().get(roundCount++);
							bc.getBattle().getFightList().add(fc);
							
							currentPlayerIndex = 0;
							int index = rc.getRound().getAttackList().get(currentPlayerIndex).getPlayerIndex();
							currentPlayer = bc.getBattle().getPlayerList().get(index);
							index = currentPlayerIndex + 1 < rc.getRound().getAttackList().size() ? 
								 rc.getRound().getAttackList().get(currentPlayerIndex+1).getPlayerIndex() : 0;
							pView.updateUI(rc.getRound().getAttackList().get(currentPlayerIndex), currentPlayer, bc.getBattle().getPlayerList().get(index));
						
						}
						else
						{
							bc.deleteObservers();
							new Alert(Alert.AlertType.INFORMATION, "Winner of the Battle is " + 
									bc.getBattle().getPlayerList().get(bc.determineWinners().get(0).getPlayerIndex()).getPlayerName()).showAndWait();
							System.exit(0);
							//end battle
						}
			}
			else
			{
				currentPlayerIndex++;
				int index = rc.getRound().getAttackList().get(currentPlayerIndex).getPlayerIndex();
				currentPlayer = bc.getBattle().getPlayerList().get(index);
				index = currentPlayerIndex + 1 < rc.getRound().getAttackList().size() ? 
					 rc.getRound().getAttackList().get(currentPlayerIndex+1).getPlayerIndex() : 0;
				pView.updateUI(rc.getRound().getAttackList().get(currentPlayerIndex), currentPlayer, bc.getBattle().getPlayerList().get(index));
			}
		}
		validSkill = false;
	}
	
}
