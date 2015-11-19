package svg;

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
