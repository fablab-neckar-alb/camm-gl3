package svg.path;

public class LineTo extends PathElement {

	private double x;
	private double y;
	private boolean relative;
	
	public LineTo() {
		
	}
	
	public void makeAbsolute(double cx, double cy) {
		if(this.relative) {		
			this.x += cx;
			this.y += cy;
		}
		this.next().makeAbsolute(this.x, this.y);
	}
	
	public String toCamm(double globalScale) {
		String res = "PU%x%y;";
		res = res.replaceAll("%x", this.fNumber(x, globalScale));
		res = res.replaceAll("%y", this.fNumber(y, globalScale));
		return res + this.next.toCAMM(globalScale);
	}
}
