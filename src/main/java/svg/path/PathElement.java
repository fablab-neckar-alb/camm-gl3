package svg.path;

import svg.Element;

public abstract class PathElement extends Element{
	protected PathElement next;
	protected double x;
	protected double y;
	protected boolean relative;
	
	public PathElement(double x, double y, boolean relative) {
		super();
		this.x = x;
		this.y = y;
		this.relative = relative;
	}
	
	public PathElement() {
		// for ZClose, does nothing
	}

	public PathElement next() {
		return this.next;
	}
	
	public void makeAbsolute(double cx, double cy) {
		if(this.relative) {
			this.x += cx;
			this.y += cy;
			this.relative = false;
		}
		this.next().makeAbsolute(this.x, this.y);
	}

	public String toCamm(double globalScale) {
		return "";
	}

	public void setNext(PathElement next) {
		this.next = next;
	}
}
