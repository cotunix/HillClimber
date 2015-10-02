package HillClimb;

public class HillClimber {

	Board board;
	boolean climbing;
	public static int correct;
	int[][] lowestCol = new int[8][2];
	

	public HillClimber() {
		board = new Board();
		board.calculateSquareCollisions();
		climbing = true;
		while (climbing) {
			climbing = false;
			
			for (int i = 0; i < 8; i++) {

				int[] ret = climbHelper(i);
				lowestCol[i][0] = ret[0];
				lowestCol[i][1] = ret[1];
				if (ret[2] != Integer.MAX_VALUE) {
					climbing = true;					
					switchQueen(i, ret[1], ret[0]);
					board.calculateSquareCollisions();
					System.out.println(board);
				}
				

			}

			
			
			

		}
		
		if (board.totalCollisions() == 0)
			correct++;
		
	}

	private int[] climbHelper(int column) {
		int lowest = Integer.MAX_VALUE, occ = -1, lowestNum = -1;

		for (int i = 0; i < 8; i++) {
			Square temp = board.board[i][column];
			if (temp.getCollisions() < lowest) {
				lowest = temp.getCollisions();
				lowestNum = i;
			}
			if (temp.isOccupied()){
				occ = i;
			}
		}

		if (lowestNum != occ) {
			int[] ret = { lowestNum, occ, lowest };
			return ret;
		} else {
			int[] ret = { lowestNum, occ, Integer.MAX_VALUE };
			return ret;
		}

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
