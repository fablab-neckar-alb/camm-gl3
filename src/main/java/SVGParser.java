

import java.util.ArrayList;
import java.util.Stack;

import svg.Circle;
import svg.Element;
import svg.SVG;
import svg.Text;

public class SVGParser {
	private Element root;
	private Element cursor;
	private boolean inHeader = false;
	private String header;
	private Stack<String> state;
	private boolean metaState = false;
	
	private ArrayList<String> acceptableTags;
	
	public SVGParser() {
		this.acceptableTags = new ArrayList<String>();
		this.root = new Element();
		this.cursor = this.root;
		this.state = new Stack<String>();
	}
	
	public void parse(String line) {
		int i=0;
		while(i<line.length()) {
			if(line.startsWith("</" + this.state + ">", i)) {
				this.cursor = this.cursor.getParent();
				i += 2 + this.state.peek().length();
			} else if(inHeader && line.startsWith(">", i)) {
				this.header += line.charAt(i);
				Element newEl = generateElement(this.state.peek(), this.header);
				if(newEl!=null) {
					this.cursor.addChild(newEl);
					newEl.setParent(this.cursor);
					if(this.metaState)	this.metaState = false;
					else this.cursor = newEl;
				}
				this.inHeader = false;
			} else if(inHeader && line.startsWith("/>", i)) {
				this.header += line.substring(i, i+2);
				Element newEl = generateElement(this.state.pop(), this.header);
				if(newEl!=null) {
					this.cursor.addChild(newEl);
					newEl.setParent(this.cursor);
				}
				this.inHeader = false;
				i++;
			} else if(inHeader) {
				this.header += line.charAt(i);
			} else if(line.startsWith("<", i) && !this.inHeader) {
				this.state.push(getTagFromLine(line, i));
				this.inHeader = true;
				this.header = "";
				this.header += line.charAt(i);
				if(line.startsWith("<?", i) || line.startsWith("<!", i)) this.metaState = true;
			}
			i++;
		}
		this.header += "\n";
	}

	private String getTagFromLine(String line, int i) {
		int pos = line.indexOf(" ", i);
		if(pos==-1) return "empty";
		return line.substring(i+1, pos);
	}

	private Element generateElement(String tag, String svgString) {
		System.out.println("Generating " + tag + " by\n" + this.header + "\n");
		if(tag=="svg") return new SVG(svgString);
		if(tag=="circle") return new Circle(svgString);
		if(tag=="text") return new Text(svgString);
		
		return new Element(svgString, tag);
	}

	public Element getRoot() {
		return root;
	}
	
	
}
