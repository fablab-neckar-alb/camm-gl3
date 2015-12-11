package svg.path;

public class MoveTo extends PathElement{
	
	public MoveTo(double x, double y, boolean relative) {
		super(x, y, relative);
	}
	
	public String toCamm(double globalScale) {
		String res = "PU%x%y;";
		res = res.replaceAll("%x", this.fNumber(x, globalScale));
		res = res.replaceAll("%y", this.fNumber(y, globalScale));
		return res + this.next.toCAMM(globalScale);
	}
}
