
public class OrderedPair {
	
	public int x;
	public int y;
	
	public OrderedPair(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean isEqual(OrderedPair pair) {
		return ((this.x == pair.x) && (this.y == pair.y));
	}
}


