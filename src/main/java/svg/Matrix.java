package svg;

import java.util.ArrayList;

public class Matrix extends Transformation implements TransformationInterface {

	private double a;
	private double b;
	private double c;
	private double d;
	private double e;
	private double f;
	
	public Matrix(double a, double b, double c, double d, double e, double f) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
		
	}
	
	public ArrayList<Double> map(double x, double y) {
		ArrayList<Double> res = new ArrayList<Double>();
		res.add(new Double(a*x + c*y + e));
		res.add(new Double(b*x + d*y + f));
		return null;
	}

}
