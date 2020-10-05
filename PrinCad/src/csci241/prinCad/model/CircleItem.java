package csci241.prinCad.model;

import csci240.prinCad.fx3d.Map3d;
import csci240.prinCad.fx3d.Xform;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

public class CircleItem extends CadItem {

	public final double _x;
	public final double _y;
	public final double _radius;

	public CircleItem(double x, double y, double radius) {
		_x = x;
		_y = y;
		_radius = radius;

	}

	public void draw(GraphicsContext gc) {
		gc.strokeOval(_x - _radius, _y - _radius, _radius * 2, _radius * 2);

	}

	public String save() {
		String type = "Circle ";
		return type + String.format("%1$f %2$f %3$f", _x, _y, _radius);
	}

	public static CircleItem load(String line) {
		String mystring = line;
		String arr[] = mystring.split(" ");
		String x = arr[1];
		String y = arr[2];
		String radius = arr[3];
		Double convertedX = Double.valueOf(x);
		Double convertedY = Double.valueOf(y);
		Double convertedRadius = Double.valueOf(radius);
		return new CircleItem(convertedX, convertedY, convertedRadius);

	}

	public CadBox getRectangle() {
		return new CadBox(_x - _radius, _y - _radius, _x + _radius, _y + _radius);
	}

	@Override
	public boolean intersects(CadLine line) {
		double dx0 = line.x0 - _x;
		double dy0 = line.y0 - _y;
		double dx1 = line.x1 - _x;
		double dy1 = line.y1 - _y;
		double lenSquare0 = dx0 * dx0 + dy0 * dy0;
		double lenSquare1 = dx1 * dx1 + dy1 * dy1;
		double radiusSquare = _radius * _radius;
		return ((lenSquare0 <= radiusSquare && lenSquare1 >= radiusSquare)
				|| (lenSquare0 >= radiusSquare && lenSquare1 <= radiusSquare));
	}

	// Does the line intersect this item?
	@Override
	public boolean intersects(CadPoint point) {
		double dx = point.x - _x;
		double dy = point.y - _y;
		double lenSquare = dx * dx + dy * dy;
		double radiusSquare = _radius * _radius;
		double sizeSquare = MarkerItem.MarkerSize * MarkerItem.MarkerSize;
		return Math.abs(radiusSquare - lenSquare) <= sizeSquare;
	}

	@Override
	public CadItem copy() {
		return new CircleItem(_x, _y, _radius);
	}

	@Override
	public Node get3dObject(Map3d map) {
		final PhongMaterial material = new PhongMaterial();
		material.setDiffuseColor(Color.DARKRED);
		material.setSpecularColor(Color.RED);

		Sphere sphere = new Sphere(map.Scale(_radius));
		sphere.setMaterial(material);

		Xform xform = new Xform();
		xform.getChildren().add(sphere);
		xform.setTx(map.ToWorldX(_x));
		xform.setTy(map.ToWorldY(_y));
	  	xform.setTz(map.ToWorldZ(getElevation()));

		return xform;

	}

}
