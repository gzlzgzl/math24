import java.io.*;
import java.util.*;
public class Main
{   
	public static void main(String args[]) throws Exception
	{ 
		// Define variables.
		ArrayList<Integer> numbers = new ArrayList();
		Scanner keyboard=new Scanner(System.in);
		Calculator TI89=new Calculator();
		Solver solver=new Solver();	
		Check4 checking=new Check4();
		String solution="";
		int score = 0;
		
		// Explains the rules of the game.
		System.out.println("In this game, you will randomly get four integers from 1-13.");
		System.out.println("You will need to use all of them.");
		System.out.println("By using addition, subtraction, multiplication, division, try to get to 24.");
		System.out.println("You will earn point if you find out the answer; otherwise you will lose a point.");
		System.out.println("(There is at least one solution for every question.)");
		 
		boolean Continue = true;
		while(Continue == true)
		{   
			// Randomly get four integers and make sure they hae a solution, them display them
			do
			{
				numbers.clear();
				numbers.add(randomGenerator());
				numbers.add(randomGenerator());
				numbers.add(randomGenerator());
				numbers.add(randomGenerator());
				Collections.sort(numbers); 
			}while((solver.solve(numbers.get(0),numbers.get(1), numbers.get(2), numbers.get(3))).equals("No solution")==true);
			for(int item: numbers)
			{
				System.out.print(item+"   ");
			}
			System.out.println("");
			
			// ask the player to type the solution, computer read the solution.
			System.out.println("Type the solution below. ");
			System.out.println("Add:+; subtract:-; multiply:*; divide:/; brackets are allowed.");
			System.out.println("Example: 3*(9+7)/2 ");
			System.out.println("If you can't find a solution, press Return.");
			String equation=keyboard.nextLine();
			
			// check whether the player has given a solution with all given numbers.
			while(!((checking.Checknumber(numbers.get(0), numbers.get(1), numbers.get(2), numbers.get(3), equation))))
			{
				// if the player does not give a solution
				if(equation.equals(""))
				{
					break;
				}
							 
				// if the player does not use the given numbers
				else
				{
					System.out.println("Please use the given numbers.");
					System.out.println("Enter again:");
					equation=keyboard.nextLine();
				}
			} // while loop
			
			// check whether the solution is right	
			String ans=checking.FinalCheck(equation);
			//System.out.println(ans);
		    ans=TI89.evaluate(ans);
		    //System.out.println(ans);
		    
			// if the solution is right
			if(ans.equals("24"))
			{
				score ++;
				System.out.println("Congratulations, right solution! ");
			}
			// if the solution is wrong or the player does not come up with a solution
			else
			{
				score--;
				System.out.println("Ineffective solution.");
				System.out.println("Here is a solution.");
				solution=solver.solve(numbers.get(0), numbers.get(1), numbers.get(2), numbers.get(3));
				TI89.print(solution);
			}
			
			// display the score
			System.out.println("Your score is: " + score);
			
			// ask the player if to continue the game
			System.out.println("Do you want to continue? ");
			System.out.println("1.Yes; 2.No.");
			if((keyboard.nextLine().equals("2")))
			{
				Continue=false;
			}
		} // continue or not
	} // main method
	
	// Randomly generates numbers
	public static int randomGenerator()
	{
		Random variables = new Random();
		int inta=variables.nextInt(13)+1;
		return inta;
	} 
}