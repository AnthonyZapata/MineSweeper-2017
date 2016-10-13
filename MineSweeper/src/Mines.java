import java.util.Random;

public class Mines {
	
	private static final int TOTAL_COLUMNS = 9;
	private static final int TOTAL_ROWS = 9;
	private static final int TOTAL_MINES = 10;
	private OrderedPairArray minePositions = new OrderedPairArray(TOTAL_MINES);
	public int[][] minesArray = new int[TOTAL_COLUMNS][TOTAL_ROWS];
	
	
	public Mines() { //This is the constructor... this code runs first to initialize
		
		for (int x = 0; x < TOTAL_COLUMNS; x++) {   //Fill the array with 0
			for (int y = 0; y < TOTAL_ROWS; y++) {
				minesArray[x][y] = 0;
			}
		}
		while (minePositions.getNumCount() < TOTAL_MINES) { //Generate the random coordinates for mines
			int x = (new Random()).nextInt(9);
			int y = (new Random()).nextInt(9);
			OrderedPair pair = new OrderedPair(x, y);
			minePositions.addPair(pair);  //The positions are stored as OrderedPairs
		}
		for (OrderedPair p : getMinePositions()) {
			minesArray[p.x][p.y] = 1;
		}
	}
	
	public boolean isMine(int column, int row) {
		return minesArray[column][row] > 0;
	}
	
	public int getTotalMines() {
		return TOTAL_MINES;
	}
	public OrderedPair[] getMinePositions() {
		return minePositions.getPairArray();
	}
	
}
















