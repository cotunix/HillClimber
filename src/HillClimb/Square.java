package HillClimb;

/**
 * Square class for the 8x8 8 queens. Tracks whether a queen is in the space and
 * number of collisions on the square.
 * 
 * @author Kyle Richardson
 * 
 */
public class Square {

	private int coll;
	private boolean occupied;

	/**
	 * Initializes a square without a queen and no collisions
	 */
	public Square() {
		coll = 0;
		occupied = false;
	}

	/**
	 * Increments number of collisions by one.
	 */
	public void incrementCollisions() {
		coll++;
	}

	/**
	 * Checks to see if there is a queen on the space
	 * 
	 * @return true if a queen is on this square
	 */
	public boolean isOccupied() {
		return occupied;
	}

	/**
	 * Places a queen on this square
	 */
	public void occupy() {
		occupied = true;
	}

	/**
	 * Removes the queen from this space
	 */
	public void deoccupy() {
		occupied = false;
	}

	/**
	 * returns number of collisions on this square
	 * 
	 * @return collisions from this square
	 */
	public int getCollisions() {
		return coll;
	}

	/**
	 * Resets collisions to 0
	 */
	public void resetCollisions() {
		coll = 0;
	}

	@Override
	/**
	 * Converts to a string with number of collisions and "Q" if a queen resides
	 * in this space.
	 */
	public String toString() {
		return (Integer.toString(coll) + (occupied ? "Q" : " "));
	}

}
