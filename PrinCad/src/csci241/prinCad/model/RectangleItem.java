package csci241.prinCad.model;

import csci240.prinCad.fx3d.Map3d;
import csci240.prinCad.fx3d.Xform;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class RectangleItem extends CadItem {

	private static final double Depth = 60;
	public final double _x;
	public final double _y;
	public final double _w;
	public final double _h;

	public RectangleItem(double x, double y, double w, double h) {
		_x = x;
		_y = y;
		_w = w;
		_h = h;

	}

	public void draw(GraphicsContext gc) {
		gc.strokeRect(_x, _y, _w, _h);

	}

	public String save() {
		String type = "Rectangle ";
		return type + String.format("%1$f %2$f %3$f %4f", _x, _y, _w, _h);
	}

	public static RectangleItem load(String line) {
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

		return new RectangleItem(convertedX, convertedY, convertedW, convertedH);

	}

	public CadBox getRectangle() {
		return new CadBox(_x, _y, _x, _y);
	}

	@Override
	public boolean intersects(CadLine line) {
		return line.intersects(this._x, this._y, this._w, this._h);

	}

	@Override
	public CadItem copy() {
		return new RectangleItem(_x, _y, _w, _h);
	}

	@Override
	public Node get3dObject(Map3d map) {

		final PhongMaterial material = new PhongMaterial();
		material.setDiffuseColor(Color.DEEPSKYBLUE);
		material.setSpecularColor(Color.LIGHTPINK);
		
		double w2 = this._w / 2.0;
		double h2 = this._h / 2.0;

		Box box = new Box(map.Scale(this._w), map.Scale(this._h), map.Scale(this.Depth));
		box.setMaterial(material);
		
		Xform xform = new Xform();
		xform.getChildren().add(box);
		xform.setTx(map.ToWorldX(_x + w2));
		xform.setTy(map.ToWorldY(_y + h2));
	  	xform.setTz(map.ToWorldZ(getElevation()));

		
		return xform;

	}

	

}
