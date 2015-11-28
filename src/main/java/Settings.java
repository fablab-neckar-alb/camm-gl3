
public final class Settings {
	private String infile;
	private String plotterDevice = "/dev/usb/lp0";
	
	private double global_scale = 1.0;
	
	private String asciiBytes;

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
	
	
	
}
