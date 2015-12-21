package svg;

import java.util.ArrayList;


public class Placeable extends Element{
	protected String tag;
	protected double centerX;
	protected double centerY;
	protected Transformation transformation;
	
	public Placeable() {
		super();
	}
	
	public Placeable(String svgString) {
		super();
		String transform = this.getValueFromKey("transform", svgString);
		if(transform!=null) this.setTransformation(transform);
	}
	
	/** flatten transforms recursively
	 * 
	 */
	public void flattenTransform() {
		for(Element x : this.children) ((Placeable) x).flattenTransform();
		ArrayList<Double> newCenter = this.transformation.map(centerX, centerY);
		this.centerX = newCenter.get(0);
		this.centerY = newCenter.get(1);
	}

	private void setTransformation(String str) {
		if(str.startsWith("translate(")) {
			str = str.substring(10, str.indexOf(")"));
			String tlcoords[] = str.split(",");
			if(tlcoords.length==1) 
				this.transformation = new Translate(Double.parseDouble(tlcoords[0]), 0.0);
			if(tlcoords.length==2) 
				this.transformation = new Translate(Double.parseDouble(tlcoords[0]), 
						Double.parseDouble(tlcoords[1]));
			else System.out.println("Unable to interpret " + str);
		} else if(str.startsWith("matrix(")) {
			str = str.substring(7, str.indexOf(")"));
			String macoords[] = str.split(",");
			if(macoords.length!=6) {
				System.out.println("Unable to interpret " + str);
				return;
			}
			this.transformation = new Matrix(
					Double.parseDouble(macoords[0]), 
					Double.parseDouble(macoords[1]), 
					Double.parseDouble(macoords[2]), 
					Double.parseDouble(macoords[3]), 
					Double.parseDouble(macoords[4]), 
					Double.parseDouble(macoords[5]));
		} else if(str.startsWith("scale(")) {
			//TODO needs refactoring. there should only matrices be allowed.
		}
	}

	public double getCenterX() {
		return centerX;
	}

	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}

	public double getCenterY() {
		return centerY;
	}

	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}
	
	
}
