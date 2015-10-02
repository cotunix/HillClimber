package HillClimb;

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
				if (climbHelper(i)) {
					board.calculateSquareCollisions();
				}

			}

			if (System.currentTimeMillis() - time > 100)
				return;

		}

		correct++;

	}

	private boolean climbHelper(int column) {
		int occ = -1;

		for (int i = 0; i < 8; i++) {
			if (board.board[i][column].isOccupied())
				occ = i;
		}
		int m = r.nextInt(8);
		if (board.board[m][column].getCollisions() <= board.board[occ][column]
				.getCollisions()) {
			switchQueen(column, occ, m);
			return true;
		}
		return false;

	}

	private void switchQueen(int i1, int j1, int j2) {
		board.board[j1][i1].deoccupy();
		board.board[j2][i1].occupy();
	}

	public static void main(String args[]) {
		for (int i = 0; i < 100; i++)
			new HillClimber();
		System.out.println(correct);
	}

}
