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
	
	public SVG() {
		super();
	}
	
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
		//some whitespaces and control 
		//bytes I recorded from the originial printer, propably in order to ensure, 
		//that no leading command will be mixed with the following.
		res +=  "\003\015\012\015\012\015\012\015\012\015\012";
		res += ";IN;"                     //Init
				+ "PA0,0;"                //Cut at 0,0
				+ "IW0,0,47000,64000;"    //set input window to 47000,64000
				+ "VS30;"                 //velocity 30
				+ "DT\u0003;";            //escape character for labels
		for(Element x : this.children) {
			res += x.toCAMM(globalScale);
		}
		//at the end, go to zero zero
		res += "PU0,0;";
		return res;
	}
	
	
}
