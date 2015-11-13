package svg;


public final class Circle extends Placeable{
	protected static String tag = "circle";
	
	protected double radius;
	protected String strokeColor;
	protected double strokeWidth;
	
	public Circle() {
		super();
	}
	
	public Circle(String svgString) {
		super();
		String cx = this.getValueFromKey("cx", svgString);
		if(cx!=null) this.centerX = Double.parseDouble(cx);
		String cy = this.getValueFromKey("cy", svgString);
		if(cy!=null) this.centerY = Double.parseDouble(cy);
		String r = this.getValueFromKey("r", svgString);
		if(r!=null) this.radius = Double.parseDouble(r);
		String stroke = this.getValueFromKey("stroke", svgString);
		if(stroke!=null) this.strokeColor = stroke;
		String strokewidth = this.getValueFromKey("stroke-width", svgString).replaceAll("px", "");
		if(strokewidth!=null) this.strokeWidth = Double.parseDouble(strokewidth);
	}
	
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public String getStrokeColor() {
		return strokeColor;
	}
	public void setStrokeColor(String strokeColor) {
		this.strokeColor = strokeColor;
	}
	public double getStrokeWidth() {
		return strokeWidth;
	}
	public void setStrokeWidth(double strokeWidth) {
		this.strokeWidth = strokeWidth;
	}

	public String getTag() {
		return tag;
	}
	
}
