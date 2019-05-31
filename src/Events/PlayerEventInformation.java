package Events;

import java.util.Set;

import PetConponents.*;
import PlayableComponents.PlayerTypes;

public class PlayerEventInformation
{

	private PlayerTypes playerType;
	private Set<Skills> skillSet;
	private PetTypes petType;
	private Double startingHp;
	private String petName;

	// default for all attributes is null

	private PlayerEventInformation(PlayerEventInformationBuilder builder)
	{
		if (builder.playerType != null)
		{
			this.petType = builder.petType;
		}
		if (builder.skillSet != null)
		{
			this.skillSet = builder.skillSet;
		}
		if (builder.petType != null)
		{
			this.petType = builder.petType;
		}
		if(builder.name != null)
		{
			this.petName = builder.name;
		}
		else
		{
			this.petName = "Nameless pet";
		}
		if(builder.startingHp != null)
		{
			this.startingHp = builder.startingHp;
		}
		else
		{
			this.startingHp = 0.0;
		}
	}

	public PetTypes getPetType()
	{
		return petType;
	}
	
	public String getPetName()
	{
		return petName;
	}

	public PlayerTypes getPlayerType()
	{
		return playerType;
	}

	public Double getStartingHp()
	{
		return startingHp;
	}
	
	public Set<Skills> getSkillSet()
	{
		return skillSet;
	}

	@Override
	public String toString()
	{
		return "Player Information Event";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((petType == null) ? 0 : petType.hashCode());
		result = prime * result
				+ ((playerType == null) ? 0 : playerType.hashCode());
		result = prime * result
				+ ((skillSet == null) ? 0 : skillSet.hashCode());
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
		PlayerEventInformation other = (PlayerEventInformation) obj;
		if (petType != other.petType)
			return false;
		if (playerType != other.playerType)
			return false;
		if (skillSet == null)
		{
			if (other.skillSet != null)
				return false;
		} else if (!skillSet.equals(other.skillSet))
			return false;
		return true;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////
	// Builder class for RoundStartEvent
	public static class PlayerEventInformationBuilder
	{
		private PlayerTypes playerType;
		private Set<Skills> skillSet;
		private PetTypes petType;
		private Double startingHp;
		private String name;

		public PlayerEventInformationBuilder withPlayerTypes(
				PlayerTypes playerType)
		{
			this.playerType = playerType;
			return this;
		}

		public PlayerEventInformationBuilder withSkills(Set<Skills> skillSet)
		{
			this.skillSet = skillSet;
			return this;
		}
		
		public PlayerEventInformationBuilder withStartingHP(Double hp)
		{
			this.startingHp = hp;
			return this;
		}

		public PlayerEventInformationBuilder withPetType(PetTypes petType)
		{
			this.petType = petType;
			return this;
		}
		
		public PlayerEventInformationBuilder withPetName(String name)
		{
			this.name = name;
			return this;
		}

		public PlayerEventInformation buildPlayerEventInformation()
		{
			return new PlayerEventInformation(this);
		}

	}

}
