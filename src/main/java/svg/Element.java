package svg;

import java.util.ArrayList;
import java.util.Arrays;
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
	
	public String toString() {
		ArrayList<String> lines = new ArrayList<String>();
		for(Element x : this.children) lines.addAll(Arrays.asList(x.toString().split("\n")));
		String res = this.tag + "\n";
		for(String x : lines) res += "\t" + x + "\n";
		return res;
	}
}
