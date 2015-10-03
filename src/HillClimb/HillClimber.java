package HillClimb;
import java.util.Random;
import java.util.ArrayList;

/**
 * Simple HillClimbing algorithm to solve 8 queens. Algorithm follows the following steps:
 * First, the algorithm populates the 8x8 chessboard with 8 queens, one in each column in a random row
 * After the chessboard is populated, the algorithm will go through each column of the chessboard, and find all squares in that column that have a lower number of collisions in that row.
 * Then the algorithm randomly selects a better square to move to, and moves the queen to that square.
 * Recalculate the collisions, and move to the next column, repeating until a solution is found OR the program times out.
 * 
 * Solves the n-queen problem roughly 97% of the time.
 * 
 * @author Kyle Richardson
 * @version 2.1
 *
 */


public class HillClimber {

	Board board;
	boolean climbing;
	public static int correct;
	
	Random r = new Random();
	long time;

	/**
	 * Runs the HillClimber algorithm.
	 */
	public HillClimber() {
		board = new Board();
		board.calculateSquareCollisions();
		time = System.currentTimeMillis();
		while (board.totalCollisions() != 0) {
			for (int i = 0; i < 8; i++) {
				climbHelper(i);
					board.calculateSquareCollisions();				
				
			}
			// If it takes more than 50 milliseconds(arbitrarily decided), it's likely there isn't a
			// solution to be found.
			if (System.currentTimeMillis() - time > 50)
				return;

		}

		correct++;

	}

	/**
	 * Moves a queen to a position that is AT LEAST as good as it's current position.
	 * @param column column of the queen to move
	 * @return boolean representing if the queen is moved
	 */
	private boolean climbHelper(int column) {
		int occ = -1;
		boolean ret = false;
		ArrayList<Integer> better = new ArrayList<Integer>();

		//find which space in the column is occupied
		for (int i = 0; i < 8; i++) {
			if (board.board[i][column].isOccupied())
				occ = i;
		}

		//add all indexes of squares with a better number of collisions to an ArrayList
		for (int i = 0; i < 8; i++) {
			if (board.board[i][column].getCollisions() <= board.board[occ][column]
					.getCollisions()) {
				ret = true;
				better.add(i);
			}
		}
		//checks to see if there is a better square, then moves the queen to a randomly selected better square
		if (ret == true) {
			int m = r.nextInt(better.size());
			switchQueen(column, occ, better.get(m));
		}
		return ret;
	}

	/**
	 * Switches queen to a new position within the column.
	 * @param i1 column of the queen
	 * @param j1 current row queen occupies
	 * @param j2 new row for queen to occupy
	 */
	private void switchQueen(int i1, int j1, int j2) {
		board.board[j1][i1].deoccupy();
		board.board[j2][i1].occupy();
	}

	/**
	 * Runs the hillclimber algorithm, and prints number of times a solution is reached.
	 * @param args number of times to run the algorithm
	 */
	public static void main(String args[]) {
		for (int i = 0; i < Integer.parseInt(args[0]); i++)
			new HillClimber();
		System.out.println(correct);
	}

}
