package edu.team06.player;

import java.io.IOException;

import BattleComponents.GameSettings;
import BattleComponents.PreBattleSetup;
import Controllers.BattleController;
import InputOutput.DefaultInputGetter;
import InputOutput.DefaultOutputSender;
import InputOutput.IOManager;
import InputOutput.Inputtable;
import InputOutput.Outputtable;
import UI.Presenter.PlayerPresenter;
import UI.Presenter.PreBattlePresenter;
import UI.Presenter.Impl.PlayerPresenterImpl;
import UI.Presenter.Impl.PreBattlePresenterImpl;
import UI.view.PlayerView;
import UI.view.PreBattleView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import TextControllers.TextBattleController;
import UIControllers.UIBattleController;
import UIControllers.UIPreBattleSetupController;

import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
public class Main extends Application
{
	private Stage primaryStage;
	public static void main(String[] args)
	{
		Application.launch(args);
	
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		Inputtable inputGetter = new DefaultInputGetter();
		Outputtable outputSender = new DefaultOutputSender();
		GameSettings gameSettings = new GameSettings();
		IOManager ioManager = new IOManager(inputGetter, outputSender);
		
		PreBattleSetup pbs = new PreBattleSetup(ioManager);
		boolean startUI = pbs.requestUIOption();
		
		if(startUI)
		{
			mainWindow();			
		}
		else
		{
			gameSettings = pbs.run();
			TextBattleController battleController = new TextBattleController(gameSettings);
			battleController.startBattle();
		}	
	}
	public void mainWindow() throws IOException
	{
		String loadPath = "/UIResources/PreBattleSetupForm.fxml";
		
		// Initializes the loader to load a .fxml file
		// The path is relative starting at the src folder
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(loadPath));
			
		// loads the root panel defined in the fxml file
		// mainPane in this example - see Example1.fxml
		VBox rootPane = fxmlLoader.load();
		
		// Stores the ExampleViewImpl created by the fxmlLoader into exampleView
		// Yes, getController returns the view
		// The fxml idea was to use MVC, but with dynamic UI creation, MVP works better
		PreBattleView preView = fxmlLoader.getController();
		UIPreBattleSetupController UIpbs = new UIPreBattleSetupController();
		// Instantiate the concrete presenter
		PreBattlePresenter prePresenter = new PreBattlePresenterImpl(UIpbs, this);
		preView.setPreBattlePresenter(prePresenter);
		prePresenter.setView(preView);
		preView.bind();
		
						
		Scene scene = new Scene(rootPane);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	public void PlayerForm(GameSettings gameSettings) throws IOException
	{
		primaryStage.hide();
		String loadPath = "/UIResources/PlayerForm.fxml";
		
		// Initializes the loader to load a .fxml file
		// The path is relative starting at the src folder
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(loadPath));
			
		// loads the root panel defined in the fxml file
		// mainPane in this example - see Example1.fxml
		VBox rootPane = fxmlLoader.load();
		PlayerView pView = fxmlLoader.getController();
		BattleController UIBc = new UIBattleController(gameSettings);
		PlayerPresenter pPresenter = new PlayerPresenterImpl(UIBc);
		pView.SetPlayerPresenter(pPresenter);
		pPresenter.setView(pView);
		pView.bind();
		Scene scene = new Scene(rootPane);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
