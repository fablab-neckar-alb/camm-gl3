package svg;


public class Placeable extends Element{
	protected String tag;
	protected double centerX;
	protected double centerY;
	
	public Placeable() {
		super();
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
