package Events;

import java.util.List;

public class FightStartEvent extends BaseEvent
{

	private int fightNumber;
	private List<PlayerEventInformation> playerEventInfo;

	// default values
	// default value for the list will be null
	private final int FIGHT_NUMBER_DEFALUT = 0;

	private FightStartEvent(FightStartEventBuilder fighStartEventBuilder)																								
	{
		super(EventTypes.FIGHT_START);

		if (fighStartEventBuilder.fightNumber != null)
			this.fightNumber = fighStartEventBuilder.fightNumber;
		else
			this.fightNumber = this.FIGHT_NUMBER_DEFALUT;
		if (fighStartEventBuilder.playerEventInfo != null)
			this.playerEventInfo = fighStartEventBuilder.playerEventInfo;
	}

	public int getFightNumber()
	{
		return fightNumber;
	}

	// don't know what this is suppose to do yet
	//^Well jeez.... thats helpful... /:
	public List<PlayerEventInformation> getPlayerEventInformation()
	{
		return playerEventInfo;
	}

	@Override
	public String toString()
	{
		return "Fight Start Event";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + FIGHT_NUMBER_DEFALUT;
		result = prime * result + fightNumber;
		result = prime * result
				+ ((playerEventInfo == null) ? 0 : playerEventInfo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		FightStartEvent other = (FightStartEvent) obj;
		if (FIGHT_NUMBER_DEFALUT != other.FIGHT_NUMBER_DEFALUT)
			return false;
		if (fightNumber != other.fightNumber)
			return false;
		if (playerEventInfo == null)
		{
			if (other.playerEventInfo != null)
				return false;
		} else if (!playerEventInfo.equals(other.playerEventInfo))
			return false;
		return true;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	// Builder class

	public static class FightStartEventBuilder
	{
		private Integer fightNumber;
		private List<PlayerEventInformation> playerEventInfo;

		public FightStartEventBuilder withFightNumber(int fightNumber)
		{
			this.fightNumber = fightNumber;
			return this;
		}

		public FightStartEventBuilder withPlayerEventInformationList(
				List<PlayerEventInformation> list)
		{
			this.playerEventInfo = list; //Null
			return this;
		}

		public FightStartEvent buildFightStartEvent()
		{
			return new FightStartEvent(this);
		}

	}
}
