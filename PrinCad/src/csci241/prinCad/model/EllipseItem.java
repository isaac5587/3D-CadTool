package csci241.prinCad.model;

import csci240.prinCad.fx3d.Map3d;
import csci240.prinCad.fx3d.Xform;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

public class EllipseItem extends CadItem {

	public final double _x;
	public final double _y;
	public final double _w;
	public final double _h;
	private static final double _r = 60;


	public EllipseItem(double x, double y, double w, double h) {
		_x = x;
		_y = y;
		_w = w;
		_h = h;

	}

	public void draw(GraphicsContext gc) {
		gc.strokeOval(_x, _y, _w, _h);

	}

	public String save() {
		String type = "Ellipse ";
		return type + String.format("%1$f %2$f %3$f %4f", _x, _y, _w, _h);
	}

	public static EllipseItem load(String line) {
		String mystring = line;
		String arr[] = mystring.split(" ");
		String x = arr[1];
		String y = arr[2];
		String w = arr[3];
		String h = arr[4];

		Double convertedX = Double.valueOf(x);
		Double convertedY = Double.valueOf(y);
		Double convertedW = Double.valueOf(w);
		Double convertedH = Double.valueOf(h);

		return new EllipseItem(convertedX, convertedY, convertedW, convertedH);

	}

	public CadBox getRectangle() {
		return new CadBox(_x, _y, _x, _y);
	}

	@Override
	public boolean intersects(CadLine line) {
		double dx0 = line.x0 - _x;
		double dy0 = line.y0 - _y;
		double dx1 = line.x1 - _x;
		double dy1 = line.y1 - _y;
		double lenSquare0 = dx0 * dx0 + dy0 * dy0;
		double lenSquare1 = dx1 * dx1 + dy1 * dy1;
		double radiusSquare = _w * _h;
		return ((lenSquare0 <= radiusSquare && lenSquare1 >= radiusSquare)
				|| (lenSquare0 >= radiusSquare && lenSquare1 <= radiusSquare));
	}
	// return line.intersects(this._x, this._y, this._w,this._h);

	@Override
	public CadItem copy() {
		return new SingleLineItem(_x, _y, _w, _h);
	}

	@Override
	public Node get3dObject(Map3d map) {
		final PhongMaterial material = new PhongMaterial();
		material.setDiffuseColor(Color.DARKRED);
		material.setSpecularColor(Color.RED);

		Sphere sphere = new Sphere(map.Scale(_r));
		sphere.setMaterial(material);
		Xform xform = new Xform();
		xform.getChildren().add(sphere);
		xform.setTx(map.ToWorldX(_x));
		xform.setTy(map.ToWorldY(_y));

		return xform;
	}

	
	

}
