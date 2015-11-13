package svg;

import java.util.ArrayList;

public class SVG extends Element{
	protected static final String tag = "svg";
	
	protected String version;
	protected String baseProfile;
	protected double width;
	protected double height;
	protected ArrayList<Double> viewBox;
	
	public SVG(String svgString) {
		super();
		this.version = this.getValueFromKey("version", svgString);
		this.baseProfile = this.getValueFromKey("baseProfile", svgString);
		this.width = Double.parseDouble(this.getValueFromKey("width", svgString));
		this.height = Double.parseDouble(this.getValueFromKey("height", svgString));
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

	public static String getTag() {
		return tag;
	}
	
	
}
