package svg;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Element {
	protected String tag;
	protected String code;

	protected Element parent;
	protected ArrayList<Element> children = new ArrayList<Element>();

	public Element() {
	}
	
	public Element(String svgString, String t) {
		this.tag = t;
		this.code = svgString;
	}

	protected String getValueFromKey(String key, String str) {
		Pattern pattern = Pattern.compile(key + "=\"(.+?)\"");
		Matcher matcher = pattern.matcher(str);
		matcher.find();
		try{
			return matcher.group(1);
		} catch (IllegalStateException e) {
			return null;
		}		
	}
	
	public void flattenAllTransforms(double globalScale) {
		for(Element x : this.getSubTree()) {
			try{
				((Placeable) x).flattenTransform();
				((Placeable) x).setCenterX(((Placeable) x).getCenterX() * globalScale);
				((Placeable) x).setCenterY(((Placeable) x).getCenterY() * globalScale);
			} catch (ClassCastException e) {}
		}
	}
	
	public ArrayList<Element> getSubTree() {
		ArrayList<Element> res = new ArrayList<Element>();
		for(Element x : this.children) {
			res.addAll(x.getSubTree());
		}
		res.add(this);
		return res;
	}

	public Element getParent() {
		return parent;
	}

	public void setParent(Element parent) {
		this.parent = parent;
	}

	public ArrayList<Element> getChildren() {
		return children;
	}
	
	public void addChild(Element child) {
		this.children.add(child);
	}

	public String getTag() {
		return tag;
	}

	public String getCode() {
		return code;
	}
	
	protected String fNumber(double x, double scale) {
		return Integer.toString((int)(x*scale));
	}
	
	protected String fDouble(double x, double scale) {
		Locale.setDefault(Locale.ENGLISH);
		DecimalFormat df = new DecimalFormat("#.#####");
		return df.format(x*scale);
	}
	
	public String toString() {
		ArrayList<String> lines = new ArrayList<String>();
		for(Element x : this.children) lines.addAll(Arrays.asList(x.toString().split("\n")));
		String res = this.getTag() + "\n";
		for(String x : lines) res += "\t" + x + "\n";
		return res;
	}
	
	public String toCAMM(double globalScale) {
		String res = "";
		for(Element x : this.children) {
			res += x.toCAMM(globalScale);
		}
		return res;
	}
}
