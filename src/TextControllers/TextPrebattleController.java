package TextControllers;

import java.util.LinkedList;
import java.util.List;

import InputOutput.DefaultInputGetter;
import InputOutput.DefaultOutputSender;
import InputOutput.IOManager;
import InputOutput.Inputtable;
import InputOutput.Outputtable;
import PlayableComponents.Playable;

public class TextPrebattleController
{

	private IOManager ioManager;
	List<Playable> playerList = new LinkedList<>(); // Move

	public TextPrebattleController()
	{
		Inputtable inputGetter = new DefaultInputGetter();
		Outputtable outputSender = new DefaultOutputSender();
		ioManager = new IOManager(inputGetter, outputSender);
	}

	public TextPrebattleController(IOManager ioManager)
	{
		this.ioManager = ioManager;
	}

	public void readInput()
	{

		playerList = ioManager.createPlayers();

	}

	public void closeIOStreams()
	{
		closeInputStream();
		closeOutputStream();
	}

	public void closeInputStream()
	{
		ioManager.closeInput();
	}

	public void closeOutputStream()
	{
		ioManager.closeOutput();
	}
}
