package PlayableComponents;

import Events.*;
import PetConponents.Pet;
import PetConponents.Skills;

import java.util.Observable;
import java.util.Scanner;

@SuppressWarnings("deprecation")
public class Human extends Player
{
	private Scanner stdin = new Scanner(System.in);
	
	public Human(String name, Pet pet) {
		super(name, pet, PlayerTypes.HUMAN);
	}

	@Override
	public Skills chooseSkill() {
		boolean valid = false;
		while (!valid) {
			try {
				System.out.println("\nPlease select a skill for " + getPetName() + ":");
				System.out.println("1: Rock Throw");
				System.out.println("2: Scissors Poke");
				System.out.println("3: Paper Cut");
				System.out.println("4: Shoot The Moon");
				System.out.println("5: Reversal Of Fortune");
				String temp = stdin.nextLine();
				int input = Integer.parseInt(temp);
				switch (input) {
				case 1:
					return Skills.ROCK_THROW;
				case 2:
					return Skills.SCISSORS_POKE;
				case 3:
					return Skills.PAPER_CUT;
				case 4: 
				  	return Skills.SHOOT_THE_MOON; 
				case 5: 
				  	return Skills.REVERSAL_OF_FORTUNE;
				 
				default:
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.println("Invalid Input, Please Try Again!");
			}
		}
		// will never reach here
		return null;
	}
	
	public Skills getSkillPredeiction()
	{
		return this.predictedSkill;
	}
	
	public Skills setSkillPredeiction() {
		boolean valid = false;
		while (!valid) {
			try {
		
				System.out.println("Prdeict a skill:");
				System.out.println("1: Rock throw");
				System.out.println("2: Scissors poke");
				System.out.println("3: Paper cut");
				System.out.println("4: Shoot the moon");
				System.out.println("5: Reversal of fortune");
				String temp = stdin.nextLine();
				int input = Integer.parseInt(temp);
				switch (input) 
				{
				case 1:
					return Skills.ROCK_THROW;
				case 2:
					return Skills.SCISSORS_POKE;
				case 3:
					return Skills.PAPER_CUT;
				case 4: 
				  	return Skills.SHOOT_THE_MOON; 
				case 5: 
				  	return Skills.REVERSAL_OF_FORTUNE;
				default:
					throw new Exception();
				}
			}
			catch (Exception e) 
			{
			System.out.println("Invalid Input, Please Try Again!");
			}
		}
		// will never reach here
		return null;
	}
	
	
	public void setPredictedSkill(Skills skill) 
	{
		this.predictedSkill = skill;
	}


	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof AttackEvent) {			
		}
		if(arg instanceof RoundStartEvent) {
		}
		if (arg instanceof FightStartEvent) {		
		}
	}

	@Override
	public void setSkill(Skills sklill) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Skills getSkill() {
		// TODO Auto-generated method stub
		return null;
	}
}
