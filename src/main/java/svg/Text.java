package svg;

public final class Text extends Placeable{
	private String tag = "text";
	private String caption;
	
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
	
	
}
