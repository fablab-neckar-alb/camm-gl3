package svg;

import java.util.ArrayList;

import svg.path.PathElement;

public class Path extends Placeable{
	
	private PathElement data;
	
	public Path(String svgString) {
		super();
		String d = this.getValueFromKey("d", svgString);
		if(d!=null) this.parsePathData(d);
	}
	
	private void parsePathData(String str) {
		//TODO
	}
	
	public String toCamm(double globalScale) {
		return data.toCamm(globalScale);
	}
}
