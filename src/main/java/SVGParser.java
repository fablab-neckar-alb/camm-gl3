

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
	
	private boolean specialCaseText = false;
	private String specialCaseTextBuffer = "";
	
	private ArrayList<String> acceptableTags;
	
	public SVGParser() {
		this.acceptableTags = new ArrayList<String>();
		this.root = new Element("", "root");
		this.cursor = this.root;
		this.state = new Stack<String>();
		this.state.push("root");
	}
	
	public void parse(String line) {
		int i=0;
		while(i<line.length()) {
			//System.out.println(this.specialCaseTextBuffer);
			if(line.startsWith("</" + this.state.peek() + ">", i)) { //element ends
				if(this.specialCaseText) {
					((Text) this.cursor).setCaption(this.specialCaseTextBuffer);
					this.specialCaseTextBuffer = "";
					this.specialCaseText = false;
				}
				this.cursor = this.cursor.getParent();
				i += 2 + this.state.pop().length();
			} else if(inHeader && line.startsWith(">", i)) { //header ends, but element continues
				this.header += line.charAt(i);
				if(this.state.peek().equals("text")) this.specialCaseText = true;
				Element newEl = generateElement(this.state.peek(), this.header);
				if(newEl!=null) {
					this.cursor.addChild(newEl);
					newEl.setParent(this.cursor);
					if(this.metaState)	this.metaState = false;
					else this.cursor = newEl;
				}
				this.inHeader = false;
			} else if(inHeader && line.startsWith("/>", i)) { //header ends, element ends
				this.header += line.substring(i, i+2);
				Element newEl = generateElement(this.state.pop(), this.header);
				if(newEl!=null) {
					this.cursor.addChild(newEl);
					newEl.setParent(this.cursor);
				}
				this.inHeader = false;
				i++;
			} else if(inHeader) { // header continues
				this.header += line.charAt(i);
			} else if(line.startsWith("<", i) && !this.inHeader) { //header starts
				this.state.push(getTagFromLine(line, i));
				this.inHeader = true;
				this.header = "";
				this.header += line.charAt(i);
				if(line.startsWith("<?", i) || line.startsWith("<!", i)) this.metaState = true;
			} else if(specialCaseText) {
				this.specialCaseTextBuffer += line.charAt(i);
			}
			i++;
		}
	}

	private String getTagFromLine(String line, int i) {
		int pos1 = line.indexOf(" ", i);
		if(pos1 == -1) pos1 = Integer.MAX_VALUE;
		int pos2 = line.indexOf(">", i);
		if(pos2 == -1) pos2 = Integer.MAX_VALUE;
		int pos3 = line.indexOf("\n", i);
		if(pos3 == -1) pos3 = Integer.MAX_VALUE;
		int pos = Math.min(Math.min(pos1, pos2), pos3);
		if(pos==Integer.MAX_VALUE) return "empty";
		return line.substring(i+1, pos);
	}

	private Element generateElement(String tag, String svgString) {
		
		System.out.println("Generating " + tag + " by\n" + this.header + "\n");
		if(tag.equals("svg")) return new SVG(svgString);
		else if(tag.equals("circle")) return new Circle(svgString);
		else if(tag.equals("text")) return new Text(svgString);
		else return new Element(svgString, "unknown_" + tag + "_");
	}

	public Element getRoot() {
		return root;
	}
	
	
}
