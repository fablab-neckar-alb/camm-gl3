package svg.path;

public class MoveTo extends PathElement{
	private double x;
	private double y;
	private boolean relative;
	
	public MoveTo(String pathStr) {
		if(pathStr.startsWith("M")) this.relative = true;
		else this.relative = false;
		pathStr = pathStr.substring(pathStr.indexOf(" "));
		String els[] = pathStr.split(" ");
		this.x = Double.parseDouble(els[0]);
		this.y = Double.parseDouble(els[1]);
		
	}
	
	public String toCamm(double globalScale) {
		String res = "PU%x%y;";
		res = res.replaceAll("%x", this.fNumber(x, globalScale));
		res = res.replaceAll("%y", this.fNumber(y, globalScale));
		return res + this.next.toCAMM(globalScale);
	}
}
