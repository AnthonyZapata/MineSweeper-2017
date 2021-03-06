import java.util.Random;

import javax.swing.JOptionPane;

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
	public void clickedCell(OrderedPair p) {
		if (!isMine(p))
			minesArray[p.x][p.y] = -1;
	}
	public boolean areAllCellsPressed() {
		for (int x = 0; x < TOTAL_COLUMNS; x++) {   //Fill the array with 0
			for (int y = 0; y < TOTAL_ROWS; y++) {
				if (minesArray[x][y] == 0)
					return false;
			}
		}
		return true;
	}
	public int getTotalMines() {
		return TOTAL_MINES;
	}
	public OrderedPair[] getMinePositions() {
		return minePositions.getPairArray();
	}
	public void mineExploted() {
		JOptionPane.showMessageDialog(null,"MINE EXPLOTED. YOU LOST!!!","DEFEATED",JOptionPane.PLAIN_MESSAGE);
	}
	public void noMineExploted() {
		JOptionPane.showMessageDialog(null,"THE GAME IS INTACT.\n YOU WON!!!","VICTORY",JOptionPane.PLAIN_MESSAGE);
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
	
	public OrderedPairArray findBlocksAround(OrderedPair p, OrderedPairArray pArray) {
		//Return an OrderedPairArray of the positions of the empty squares
		for (int x = -1; x < 2; x++) {
			for (int y = -1; y < 2; y++) {
				int cellX = p.x + x;
				int cellY = p.y + y;
				OrderedPair aroundMine = new OrderedPair(cellX, cellY);

				if (cellX >= 0 && cellY >= 0 && cellX < TOTAL_COLUMNS && cellY < TOTAL_ROWS) { //The cell is inside the grid
					if (cellX != p.x || cellY != p.y) { //The cell is around the center pair and not the center itself
						pArray.addPair(aroundMine);
					}
				}
			}
		}
		return pArray;
	}
	
	public OrderedPairArray uncoverEmptyBlocks(OrderedPair p) { //Uncovers all the empty cells
															    // around an empty cell
		OrderedPairArray firstBlocks = findBlocksAround(p, new OrderedPairArray(8));
		OrderedPairArray emptyBlocksArray = new OrderedPairArray(8);
		OrderedPairArray numberedBlocksArray = new OrderedPairArray(8);
		
		for (int i=0; i < firstBlocks.getNumCount(); i++) {
			OrderedPair firstPairs = firstBlocks.getPairArray()[i];
			if (findMinesAround(firstPairs) == 0) {
				emptyBlocksArray.addPair(firstPairs);
			}
			else if (!isMine(firstPairs)) {
				numberedBlocksArray.addPair(firstPairs);
			}
		}
		
		for (int i=0; i < emptyBlocksArray.getNumCount(); i++) { //Iterates through all the empty cells recursively (in a sense!)
			
			OrderedPair emptyPairs = emptyBlocksArray.getPairArray()[i];
			OrderedPairArray aroundBlocks = findBlocksAround(emptyPairs, new OrderedPairArray(8));
			
			for (int j=0; j < aroundBlocks.getNumCount(); j++) {
				OrderedPair aroundPairs = aroundBlocks.getPairArray()[j];
				if (findMinesAround(aroundPairs) == 0) {
					emptyBlocksArray.addPair(aroundPairs);
				}
				else if (!isMine(aroundPairs)) {
					numberedBlocksArray.addPair(aroundPairs);
				}
			}
		}
		return emptyBlocksArray.union(numberedBlocksArray);
		
	}
	
	
	
}
















