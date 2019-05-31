package Events;

public class RoundStartEvent extends BaseEvent
{

	private int roundNumber;

	private final int DEFALT_ROUND_NUMBER = 0;

	private RoundStartEvent(EventStartBuilder eventStartBuilder)
	{
		super(EventTypes.ROUND_START);

		if (eventStartBuilder.roundNumber != null)
			this.roundNumber = eventStartBuilder.roundNumber;
		else
			this.roundNumber = this.DEFALT_ROUND_NUMBER;

	}

	public int getRoundNumber()
	{
		return roundNumber;
	}

	@Override
	public String toString()
	{
		return null;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + roundNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoundStartEvent other = (RoundStartEvent) obj;
		if (roundNumber != other.roundNumber)
			return false;
		return true;
	}

	///////////////////////////////////////////////////////////////////////////////////////////
	// Builder class
	public static class EventStartBuilder
	{
		private Integer roundNumber;

		public EventStartBuilder withRoundNumber(Integer roundNumber)
		{
			this.roundNumber = roundNumber;
			return this;
		}

		public RoundStartEvent buildRoundStartEvent()
		{
			return new RoundStartEvent(this);
		}
	}
}
