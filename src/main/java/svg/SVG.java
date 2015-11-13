package svg;

import java.util.ArrayList;

public class SVG extends Element{
	protected static String tag = "svg";
	
	protected String version;
	protected String baseProfile;
	protected double width;
	protected double height;
	protected ArrayList<Double> viewBox;
	
	public SVG(String svgString) {
		super();
		this.version = this.getValueFromKey("version", svgString);
		this.baseProfile = this.getValueFromKey("baseProfile", svgString);
		String w = this.getValueFromKey("width", svgString);
		this.width = Double.parseDouble(w.replaceAll("px", ""));
		String h = this.getValueFromKey("height", svgString);
		this.height = Double.parseDouble(h.replaceAll("px", ""));
		String[] viewBox = this.getValueFromKey("viewBox", svgString).split(" ");
		try{
			this.viewBox = new ArrayList<Double>();
			for(String str : viewBox) {
				double x = Double.parseDouble(str);
				this.viewBox.add(x);
			}
		} catch (NumberFormatException e) {
			this.viewBox = null;
		}
	}

	public String getTag() {
		return tag;
	}
	
	
}
