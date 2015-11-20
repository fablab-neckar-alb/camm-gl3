package svg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Text extends Placeable{
	private String tag = "text";
	private String caption;
	private double length;
	private String spacingAndGlyphs;
	
	public Text(String svgString) {
		super();
		this.caption = "";
		//TODO
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		//strip off whitespaces
		while(caption.startsWith(" ") || caption.startsWith("\n") || caption.startsWith("/t"))
			caption = caption.substring(1);
		while(caption.endsWith(" ") || caption.endsWith("\n") || caption.endsWith("/t"))
			caption = caption.substring(0, caption.length()-1);
		this.caption = caption;
	}

	public String getTag() {
		return tag;
	}
	
	public String toCAMM(double globalScale) {
		
//		String res = "S %size\n"
//				+ "P%capt\n";
//		res = res.replaceAll("%size", this.fNumber(this.getCenterX(), globalScale));
//		res = res.replaceAll("%capt", this.caption);
		return "";
	}
	
	
}
