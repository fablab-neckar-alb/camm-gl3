package svg;

import java.util.ArrayList;

public interface TransformationInterface {
	public ArrayList<Double> map(double x, double y);
	public TransformationInterface getNext();
}
