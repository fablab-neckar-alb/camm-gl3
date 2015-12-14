package svg.path;

public class ZClose extends PathElement {
	public ZClose() {
		super();
		this.next = null;
	}
	
	public void makeAbsolute(double cx, double cy) {
		//does nothing
	}
	
	//toCamm is already implemented in superclass
	public String toCamm(double globalScale) {
		return "";
	}
}
