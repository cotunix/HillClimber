package HillClimb;

public class Square {

	private int coll;
	private boolean occupied;
	
	public Square(){
		coll = 0;
		occupied = false;
	}
	
	public void incrementCollisions(){
		coll++;
	}
	
	public boolean isOccupied() {
		return occupied;
	}
	
	public void occupy() {
		occupied = true;
	}
	
	public void deoccupy() {
		occupied = false;
	}
	
	public int getCollisions() {
		return coll;
	}
	
	public void resetCollisions() {
		coll = 0;
	}
	
	
	
	@Override
	public String toString(){
		return (Integer.toString(coll) + (occupied ? "Q" : " "));
	}
	
}
