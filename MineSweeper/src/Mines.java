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
	
	public boolean isMine(OrderedPair p) {
		return minesArray[p.x][p.y] > 0;
	}
	
	public int getTotalMines() {
		return TOTAL_MINES;
	}
	public OrderedPair[] getMinePositions() {
		return minePositions.getPairArray();
	}
	public int findMinesAround(OrderedPair minePos) {
		//Return the number of Mines around the cell which was pressed.
		int mineCounter = 0;
		for (int x = -1; x < 2; x++) {
			for (int y = -1; y < 2; y++) {
				int cellX = minePos.x + x;
				int cellY = minePos.y + y;
				OrderedPair aroundMine = new OrderedPair(cellX, cellY);
				
				if (cellX >= 0 && cellY >= 0 && cellX < TOTAL_COLUMNS && cellY < TOTAL_ROWS) {
					if (isMine(aroundMine)) {
						mineCounter++;
					}
				}
			}
		}
		return mineCounter; //
	}
	
	
	
	
}
















