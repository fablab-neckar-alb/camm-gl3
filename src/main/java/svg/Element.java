package svg;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Element {
	protected String tag;

	protected Element parent;
	protected ArrayList<Element> children = new ArrayList<Element>();

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
	
	
}
