package PetConponents;

public enum Skills
{
	ROCK_THROW(1)
	{
		@Override
		public String toString()
		{
			return "Rock Throw";
		}
	},

	SCISSORS_POKE(1)
	{
		@Override
		public String toString()
		{
			return "Scissors Poke";
		}
	},

	PAPER_CUT(1)
	{
		@Override
		public String toString()
		{
			return "Paper Cut";
		}
	},

	SHOOT_THE_MOON(6)
	{
		@Override
		public String toString()
		{
			return "Shoot The Moon";
		}
	},

	REVERSAL_OF_FORTUNE(6)
	{
		@Override
		public String toString()
		{
			return "Reversal Of Fortune";
		}
	};

	private final int RECHARGE_TIME;

	/**
	 * Creates skill with given recharges value
	 * 
	 * @param recharge
	 */
	private Skills(int recharge)
	{
		this.RECHARGE_TIME = recharge;
	}

	public int getRecharge()
	{
		return RECHARGE_TIME;
	}

}
