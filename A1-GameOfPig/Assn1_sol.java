import java.util.Random;
import java.util.Scanner;

public class Solution {
	static int playerScore = 0;
	static int cpuScore = 0;
	static int turnPlayerScore = 0;
	static int turnCPUScore = 0;
	static Scanner in = new Scanner(System.in);
	static Random rnd = new Random(0);

	static void reportScore(boolean isPlayer) {
		if (isPlayer)
			System.out.println("Your turn sum=" + turnPlayerScore + ", Your game sum=" + playerScore);
		else
			System.out.println("CPU turn sum=" + turnCPUScore + ", CPU game sum=" + cpuScore);

	}

	static boolean askPlayer() {
		System.out.println("Do you want to roll again?Y/N");
		String ans = in.next();
		return ans.equalsIgnoreCase("Y");
	}

	static void playerTurn() {
		System.out.println("================");
		System.out.println("Your Turn");
		turnPlayerScore = 0;
		boolean must = false;
		do {
			must = false;
			int dice = 1 + rnd.nextInt(6);
			int dice2 = 1 + rnd.nextInt(6);
			System.out.println("You rolled: " + toString(dice) + " " + toString(dice2));

			if (dice == 1 && dice2 == 1) {
				playerScore = 0;
				turnPlayerScore = 0;
				System.out.println("Double ones, you lose your total score!!");
				break;
			}
			if (dice == 1 || dice2 == 1) {
				playerScore -= turnPlayerScore;
				turnPlayerScore = 0;
				System.out.println("Single one, you lose this turn!!");
				break;
			}
			if (dice == dice2) {
				must = true;
			}
			turnPlayerScore += dice + dice2;
			playerScore += dice + dice2;
			reportScore(true);
			if (playerScore >= 100)
				break;
		} while (askPlayer() || must);
		System.out.println("Your turn is finised");
		reportScore(true);
		System.out.println("=======================");
	}

	static void cpuTurn() {
		System.out.println("================");
		System.out.println("CPU Turn");
		turnCPUScore = 0;
		do {
			int dice = 1 + rnd.nextInt(6);
			int dice2 = 1 + rnd.nextInt(6);
			System.out.println("CPU rolled: " + toString(dice) + " + " + toString(dice2));
			if (dice == 1 && dice2 == 1) {
				cpuScore = 0;
				turnCPUScore = 0;
				System.out.println("Double ones, CPU loses  total score!!");
				break;
			}
			if (dice == 1 || dice2 == 1) {
				cpuScore -= turnCPUScore;
				turnCPUScore = 0;
				System.out.println("Single one, CPU loses this turn!!");
				break;
			}
			turnCPUScore += dice + dice2;
			cpuScore += dice + dice2;

			reportScore(false);
			if (cpuScore >= 100)
				break;
		} while (cpuScore < 20);
		System.out.println("CPU turn is finised");
		reportScore(false);
		System.out.println("=======================");

	}

	public static void main(String[] args) {

		while (playerScore < 100 && cpuScore < 100) {
			playerTurn();
			if(playerScore>=100)
				break;
			cpuTurn();
			System.out.println("Your score=" + playerScore + " , CPU score=" + cpuScore);
		}
		if (playerScore >= 100)
			System.out.println("You Won!!");
		else
			System.out.println("You Lose!!");

	}

	public static String toString(int x) {
		String s[] = { "One", "Two", "Three", "Four", "Five", "Six" };
		return s[x - 1];
	}
}
