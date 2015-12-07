package svg;

public class G extends Element{
	protected Transformation transformation;
	
	public G(String svgString) {
		super();
		String transform = this.getValueFromKey("transform", svgString);
		if(transform!=null) this.setTransformation(transform);
	}

	private void setTransformation(String str) {
		if(str.startsWith("translate(")) {
			str = str.substring(10, str.indexOf(")"));
			String tlcoords[] = str.split(",");
			if(tlcoords.length!=2) {
				System.out.println("Unable to interpret " + str);
				return;
			}
			this.transformation = new Translate(Double.parseDouble(tlcoords[0]), 
					Double.parseDouble(tlcoords[1]));
		} else if(str.startsWith("matrix(")) {
			str = str.substring(7, str.indexOf(")"));
			String macoords[] = str.split(",");
			
		}
	}
}
