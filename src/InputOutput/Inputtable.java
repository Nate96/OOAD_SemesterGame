package InputOutput;

import PetConponents.Pet;

public interface Inputtable extends AutoCloseable
{
	String getPlayerName();

	String getPetName();

	Pet createPet(String petName);

	int getPlayerNumber();

	int getFightNumber();

	long getRandomNumber();

	double getPetMaxHp();
}
