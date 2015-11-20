package svg;

import java.util.ArrayList;

public class SVG extends Element{
	protected String tag = "svg";
	
	protected String version;
	protected String baseProfile;
	protected double width;
	protected double height;
	protected ArrayList<Double> viewBox;
	protected String unit;
	
	public SVG(String svgString) {
		super();
		this.version = this.getValueFromKey("version", svgString);
		this.baseProfile = this.getValueFromKey("baseProfile", svgString);
		String w = this.getValueFromKey("width", svgString);
		String h = this.getValueFromKey("height", svgString);
		if(w.contains("px")) {
			w = w.replaceAll("px", "");
			h = h.replaceAll("px", "");
			this.unit = "px";
		}
		if(w.contains("%")) {
			w = w.replaceAll("%", "");
			h = h.replaceAll("%", "");
			this.unit = "%";
		}
		this.width = Double.parseDouble(w);
		this.height = Double.parseDouble(h);
		String vB = this.getValueFromKey("viewBox", svgString);
		if(vB!=null) {
			String[] viewBox = vB.split(" ");
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
	}

	public String getTag() {
		return tag;
	}
	
	public String toCAMM(double globalScale) {
		String res = "";
		for(Element x : this.children) {
			res += x.toCAMM(globalScale);
			res += "^OA;\n";
		}
		res += "H\n";
		return res;
	}
	
	
}
