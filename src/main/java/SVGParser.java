

import java.util.ArrayList;

import svg.Circle;
import svg.Element;
import svg.SVG;

public class SVGParser {
	private Element root;
	private Element cursor;
	
	private ArrayList<String> acceptableTags;
	
	public SVGParser() {
		this.acceptableTags.add(SVG.getTag());
		this.acceptableTags.add(Circle.getTag());
		this.root = new Element();
		this.cursor = this.root;
	}
	
	public void parse(String line) {
		int i=0;
		while(i<line.length()) {
			for(String tag : this.acceptableTags) {
				if(line.startsWith("<" + tag, i)) {
					Element newEl = generateElement(tag, line.substring(i, line.indexOf(">", i)));
					if(newEl!=null) this.cursor.addChild(newEl);
					newEl.setParent(this.cursor);
					this.cursor = newEl;
				} else if(line.startsWith("</" + tag + ">", i)) {
					this.cursor = this.cursor.getParent();
				}
			}
			i++;
		}
		
	}

	private Element generateElement(String tag, String svgString) {
		if(tag==SVG.getTag()) return new SVG(svgString);
		if(tag==Circle.getTag()) return new Circle(svgString);
		
		return null;
	}
}
