
public final class Settings {
	private String infile;
	private String plotterDevice = "/dev/usb/lp0";
	
	private double global_scale = 1.0;
	
	private String asciiBytes;
	
	private boolean sortForY = false;
	
	private boolean weirdTreePlotOptim = false;
	private final double slowCircleMinRadius = 200.0;
	private final double textMinCharSize = 50.0;

	public String getInfile() {
		return infile;
	}

	public void setInfile(String infile) {
		this.infile = infile;
	}

	public double getGlobal_scale() {
		return global_scale;
	}

	public void setGlobal_scale(double global_scale) {
		this.global_scale = global_scale;
	}

	public String getPlotterDevice() {
		return plotterDevice;
	}

	public String getAsciiBytes() {
		return asciiBytes;
	}

	public void setAsciiBytes(String asciiBytes) {
		this.asciiBytes = asciiBytes;
	}

	public boolean isSortForY() {
		return sortForY;
	}

	public void setSortForY(boolean sortForY) {
		this.sortForY = sortForY;
	}

	public boolean isWeirdTreePlotOptim() {
		return weirdTreePlotOptim;
	}

	public void setWeirdTreePlotOptim(boolean weirdTreePlotOptim) {
		this.weirdTreePlotOptim = weirdTreePlotOptim;
	}

	public double getSlowCircleMinRadius() {
		return slowCircleMinRadius;
	}

	public double getTextMinCharSize() {
		return textMinCharSize;
	}
	
	
	
	
}
