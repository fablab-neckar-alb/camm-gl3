package svg;



public final class Text extends Placeable{
	private String tag = "text";
	private String caption;
	private double length;
	@SuppressWarnings("unused")
	private String lengthAdjust;
	
	public Text(String svgString) {
		super(svgString);
		this.caption = "";
		String x = this.getValueFromKey("x", svgString);
		if(x!=null) this.centerX = Double.parseDouble(x);
		String y = this.getValueFromKey("y", svgString);
		if(y!=null) this.centerY = Double.parseDouble(y);
		String length = this.getValueFromKey("textLength", svgString);
		if(length!=null) this.length = Double.parseDouble(length);
		String lengthAdjust = this.getValueFromKey("lengthAdjust", svgString);
		if(lengthAdjust!=null) this.lengthAdjust = lengthAdjust;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		//strip off whitespaces
		while(caption.startsWith(" ") || caption.startsWith("\n") || caption.startsWith("\t"))
			caption = caption.substring(1);
		while(caption.endsWith(" ") || caption.endsWith("\n") || caption.endsWith("\t"))
			caption = caption.substring(0, caption.length()-1);
		//remove all non ascii chars
		caption = caption.replaceAll("[^\\x20-\\x7e]", "");
		this.caption = caption;
	}

	public String getTag() {
		return tag + " " + this.length + " '" + this.caption + "' " + this.caption.length();
	}
	
	public String toCAMM(double globalScale) {
		String res = "PU%xpos,%ypos;SI%sizex,%sizey;LB%caption\u0003;";
		res = res.replaceAll("%caption", this.caption);
		res = res.replaceAll("%xpos", 
				this.fNumber(this.centerX - this.length / 2, globalScale));
		res = res.replaceAll("%ypos", 
				this.fNumber(this.centerY - this.length / (this.caption.length() * 2), globalScale));
		res = res.replaceAll("%sizex", 
				this.fDouble(this.length / (double)this.caption.length(), globalScale / 600));
		res = res.replaceAll("%sizey", 
				this.fDouble(this.length / (double)this.caption.length(), globalScale / 600));
		return res;
	}
	
	
}
