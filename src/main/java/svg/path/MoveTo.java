package svg.path;

public class MoveTo extends PathElement{
	private double x;
	private double y;
	
	public MoveTo(String pathStr) {
		//TODO
	}
	
	public String toCamm(double globalScale) {
		String res = "PU%x%y;";
		res = res.replaceAll("%x", this.fNumber(x, globalScale));
		res = res.replaceAll("%y", this.fNumber(y, globalScale));
		return res + this.next.toCAMM(globalScale);
	}
}
