package svg;

import java.util.ArrayList;

public class SVGParser {
	private Element root;
	private Element cursor;
	
	private ArrayList<String> acceptableTags;
	
	public SVGParser() {
		this.acceptableTags.add(SVG.tag);
		this.acceptableTags.add(Circle.tag);
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
		if(tag==SVG.tag) return new SVG(svgString);
		if(tag==Circle.tag) return new Circle(svgString);
		
		return null;
	}
}
