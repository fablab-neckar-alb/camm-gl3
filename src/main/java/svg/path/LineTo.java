package svg.path;

public class LineTo extends PathElement {

	public LineTo(double x, double y, boolean relative) {
		super(x, y, relative);
	}
	
	public void makeAbsolute(double cx, double cy) {
		if(this.relative) {		
			this.x += cx;
			this.y += cy;
		}
		this.next().makeAbsolute(this.x, this.y);
	}
	
	public String toCamm(double globalScale) {
		String res = "PD%x%y;";
		res = res.replaceAll("%x", this.fNumber(x, globalScale));
		res = res.replaceAll("%y", this.fNumber(y, globalScale));
		return res + this.next.toCAMM(globalScale);
	}
}
