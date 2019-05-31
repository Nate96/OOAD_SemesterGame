package Events;

public class BaseEvent
{

	private EventTypes eventType;

	public BaseEvent(EventTypes eventType)
	{
		this.eventType = eventType;
	}

	public EventTypes getEventType()
	{
		return eventType;
	}

	@Override
	public String toString()
	{
		return "Base Class";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((eventType == null) ? 0 : eventType.hashCode());
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
		BaseEvent other = (BaseEvent) obj;
		if (eventType != other.eventType)
			return false;
		return true;
	}

}
