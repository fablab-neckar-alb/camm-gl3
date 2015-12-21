package svg;

import java.util.ArrayList;

public class Translate extends Transformation implements TransformationInterface {
	protected double x;
	protected double y;

	public Translate(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public ArrayList<Double> map(double x, double y) {
		ArrayList<Double> res = new ArrayList<Double>();
		res.add(x + this.x);
		res.add(y + this.y);
		if(this.getNext()==null)	return res;
		else return this.getNext().map(res.get(0), res.get(1));
	}
	
	public String toString() {
		String res = "x+" + this.x + ", y+" + this.y + " : ";
		if(this.getNext()!=null) res += this.getNext().toString();
		else res += "[]";
		return res;
	}
}
