
public class OrderedPairArray {
	
	private int length;
	private OrderedPair[] pairArray;
	private int numCount = 0;
	
	public OrderedPairArray(int length) {
		this.length = length;
		pairArray = new OrderedPair[length];
	}
	
	public int getNumCount() {
		return numCount;
	}
	
	public OrderedPair[] getPairArray() {
		return pairArray;
	}
	
	public boolean isMember(OrderedPair pair) {
		if (numCount > 0) {
			for (int i = 0; i < numCount; i++) {
				if (pair.isEqual(pairArray[i])) { return true; }
			}
		}
		return false;
	}
	
	public void addPair(OrderedPair pair) {
		if (!isMember(pair) && numCount < length) {
			pairArray[numCount] = pair;
			numCount++;
		}
	}
}
