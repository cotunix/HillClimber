package HillClimb;

import java.util.ArrayList;
import java.util.Random;

public class HillClimber {

	Board board;
	boolean climbing;
	public static int correct;
	
	Random r = new Random();
	long time;

	public HillClimber() {
		board = new Board();
		board.calculateSquareCollisions();
		time = System.currentTimeMillis();
		while (board.totalCollisions() != 0) {
			for (int i = 0; i < 8; i++) {
				climbHelper(i);
					board.calculateSquareCollisions();				
				
			}
			// If it takes more than 5 milliseconds(Arbitrary), it's likely there isn't a
			// solution to be found.
			if (System.currentTimeMillis() - time > 5)
				return;

		}

		correct++;

	}

	private boolean climbHelper(int column) {
		int occ = -1;
		boolean ret = false;
		ArrayList<Integer> better = new ArrayList<Integer>();

		for (int i = 0; i < 8; i++) {
			if (board.board[i][column].isOccupied())
				occ = i;
		}

		for (int i = 0; i < 8; i++) {
			if (board.board[i][column].getCollisions() <= board.board[occ][column]
					.getCollisions()) {
				ret = true;
				better.add(i);
			}
		}
		if (ret == true) {
			int m = r.nextInt(better.size());
			switchQueen(column, occ, better.get(m));
		}
		return ret;
	}

	private void switchQueen(int i1, int j1, int j2) {
		board.board[j1][i1].deoccupy();
		board.board[j2][i1].occupy();
	}

	public static void main(String args[]) {
		for (int i = 0; i < 10000; i++)
			new HillClimber();
		System.out.println(correct);
	}

}
