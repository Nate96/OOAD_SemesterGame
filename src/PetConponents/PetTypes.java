package PetConponents;

public enum PetTypes
{
	SPEED
	{
		@Override
		public String toString()
		{
			return "Speed";
		}
	},

	POWER
	{
		@Override
		public String toString()
		{
			return "Power";
		}
	},

	INTELLIGENCE
	{
		@Override
		public String toString()
		{
			return "Intelligence";
		}
	};

}