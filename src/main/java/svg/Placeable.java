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
	
	/** flatten transforms for this Placeable
	 * 
	 */
	public void flattenTransform() {
		if(this.transformation == null) return;
		ArrayList<Double> newCenter = this.transformation.map(centerX, centerY);
		this.centerX = newCenter.get(0);
		this.centerY = newCenter.get(1);
		this.transformation = null;
	}

	private void setTransformation(String str) {
		this.transformation = new Translate(0.0,0.0);
		Transformation cursor = this.transformation;
		while(!str.isEmpty()) {
			if(str.startsWith("translate(")) {
				str = str.substring(10, str.indexOf(")"));
				String tlcoords[] = str.split(",");
				if(tlcoords.length==1) {
					cursor.setNext(new Translate(Double.parseDouble(tlcoords[0]), 0.0));
					cursor = cursor.getNext();
				}
				if(tlcoords.length==2) {
					cursor.setNext(new Translate(Double.parseDouble(tlcoords[0]), 
							Double.parseDouble(tlcoords[1])));
					cursor = cursor.getNext();
				}
			} else if(str.startsWith("matrix(")) {
				str = str.substring(7, str.indexOf(")"));
				String macoords[] = str.split(",");
				if(macoords.length!=6) {
					System.out.println("Unable to interpret " + str);
					return;
				}
				cursor.setNext(new Matrix(
						Double.parseDouble(macoords[0]), 
						Double.parseDouble(macoords[1]), 
						Double.parseDouble(macoords[2]), 
						Double.parseDouble(macoords[3]), 
						Double.parseDouble(macoords[4]), 
						Double.parseDouble(macoords[5])));
				cursor = cursor.getNext();
			} else if(str.startsWith("scale(")) {
				//TODO needs refactoring. there should only matrices be allowed.
			} else {
				str = str.substring(1);
			}
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
