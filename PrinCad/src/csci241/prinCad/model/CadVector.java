package csci241.prinCad.model;

//Immutable point (reduces possibility of hard to spot bugs)
public class CadVector {

	// Can expose the values since the outside world is not able to modify
	public final double dx;
	public final double dy;
	public final double length;

	// One time assignment of dx, dy and length values
	public CadVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
		this.length = Math.sqrt(dx * dx + dy * dy);
	}

}
