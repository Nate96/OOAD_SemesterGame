package PlayableComponents;

public enum PlayerTypes
{

	HUMAN
	{
		@Override
		public String toString()
		{
			return "Human";
		}
	}, 
	COMPUTER
	{
		@Override
		public String toString()
		{
			return "Computer";
		}
	},
	ULTRON
	{
		@Override
		public String toString()
		{
			return "Ultron";
		}
	};

}
