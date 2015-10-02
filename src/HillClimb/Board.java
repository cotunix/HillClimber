package HillClimb;

/**
 * OKAY it's time for bed. But you think you have a realization on why your code isn't working.
 * The only answer is in A: how you're calculation the heuristic B: the definition of hill climbing
 * The way this algorithm currently works is wrong. It simply only checks the neighbors. This is not what we want to do.
 * We want to calculate the heuristic for the whole board and move THE BEST POSSIBLE QUEEN TO THE BEST POSSILBLE POSITION
 * Check this out: https://courses.cs.washington.edu/courses/csep573/11wi/lectures/04-lsearch.pdf
 * In the diagram, we should move a queen to a 12 spot, NO MATTER WHAT. 
 * First choice is easiest, so we would move the first queen with a 12 in the column to it.
 * So in the diagram, the queen in the second column.
 * Work on this today
 * @author Kyle
 *
 */

public class Board {

	public Square[][] board;

	/**
	 * Creates a new board, with a 8x8 Square array and puts a Queen in a random row in each column.
	 */
	public Board() {
		board = new Square[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = new Square();
			}
		}

		for (int i = 0; i < 8; i++) {
			int r = (int) Math.round(Math.random() * 7);
			board[r][i].occupy();

		}
	}

	/**
	 * This method calculates the total number of collision for a single square
	 * 
	 * @param r
	 *            row the square is located
	 * @param c
	 *            column the square is located
	 */
	public void calculateSquareCollisions() {
		resetCollisions();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				checkRow(i, j);
				checkDiagonal(i, j);
			}

		}
	}
	
	private void resetCollisions(){
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				board[i][j].resetCollisions();
			}
		}
	}
	
	public int totalCollisions(){
		int c = 0;
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				c += board[i][j].isOccupied() ? board[i][j].getCollisions() : 0;
			}
		}
		return c;
	}

	/**
	 * Calculate collisions with other queens in the same row
	 * 
	 * @param row
	 *            row of queen calculating collisions for
	 * @param column
	 *            column of queen calculating collisions for
	 */

	private void checkRow(int row, int column) {
		for (int i = 0; i < 8; i++) {
			// make sure not to calculate collision with itself
			if (i != column) {
				if (board[row][i].isOccupied())
					board[row][column].incrementCollisions();
			}
		}
	}

	/**
	 * Calculates any collisions along the diagonal. 
	 * @param row Row of queen
	 * @param column column of queen
	 */
	private void checkDiagonal(int row, int column) {
		// this way : \ down
		int j = column + 1;
		for (int i = row + 1; i < 8; i++) {

			if (j < 8 && board[i][j].isOccupied())
				board[row][column].incrementCollisions();
			j++;
		}
		
		
		j = column + 1;
		for (int i = row - 1; i > -1; i--) {

			if (j < 8 && board[i][j].isOccupied())
				board[row][column].incrementCollisions();

			j++;
		}

		j = column - 1;
		for (int i = row + 1; i < 8; i++) {
			
				if (j > -1 && board[i][j].isOccupied())
					board[row][column].incrementCollisions();
				
			j--;
		}
		
		j = column - 1;
		for (int i = row - 1; i > -1; i--) {
			
				if (j > -1 && board[i][j].isOccupied())
					board[row][column].incrementCollisions();
			j --;
		}

	}

	@Override
	public String toString() {
		String r = "";
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				r += (board[i][j].toString() + " ");
			}
			r += "\n";
		}

		return r;
	}
	
	

	public static void main(String args[]) {
		
		Board m = new Board();
		m.calculateSquareCollisions();
		System.out.print(m);
		
	}

}
