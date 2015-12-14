package svg.path;

public class ZClose extends PathElement {
	public ZClose() {
		this.next = null;
	}
	
	public void makeAbsolute(double cx, double cy) {
	}
	
	public String toCamm(double globalScale) {
		return "";
	}
}
