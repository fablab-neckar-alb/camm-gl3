package svg;

public abstract class Transformation implements TransformationInterface{
	private Transformation next;

	public Transformation getNext() {
		return next;
	}

	public void setNext(Transformation next) {
		this.next = next;
	}
	
	
}
