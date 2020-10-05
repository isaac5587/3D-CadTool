package csci241.prinCad.model;

public class CadBox {

	public final double xMin, yMin, xMax, yMax;

	public CadBox(double xMin, double yMin, double xMax, double yMax) {

		this.xMin = xMin; // represents the minimum x value
		this.yMin = yMin; // represents the minimum y value
		this.xMax = xMax; // represents the maximum x value
		this.yMax = yMax; // represents the maximum y value
	}

	public boolean isInside(CadBox rect) {
		return (rect.xMin >= this.xMin && //conditions to confirm that the shape is properly inside the square
				rect.xMax <= this.xMax && 
				rect.yMin >= this.yMin && 
				rect.yMax <= this.yMax );

	}

}
