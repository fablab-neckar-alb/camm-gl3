package svg;


public final class Circle extends Placeable{
	protected String tag = "circle";
	
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
		String strokewidth = this.getValueFromKey("stroke-width", svgString);
		if(strokewidth!=null) {
			strokewidth = strokewidth.replaceAll("px", "");
			this.strokeWidth = Double.parseDouble(strokewidth);
		}
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
	
	public String toCAMM(double globalScale) {
		String res = "PU%x,%y;" +
				"^CI%r;";
		res = res.replaceAll("%x", this.fNumber(this.getCenterX(), globalScale));
		res = res.replaceAll("%y", this.fNumber(this.getCenterY(), globalScale));
		res = res.replaceAll("%r", this.fNumber(this.radius, globalScale));
		return res;
	}

	
}
