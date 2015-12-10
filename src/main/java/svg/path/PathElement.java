package svg.path;

import svg.Element;

public abstract class PathElement extends Element{
	protected PathElement next;
	
	public PathElement next() {
		return this.next;
	}

	public String toCamm(double globalScale) {
		return "";
	}
}
