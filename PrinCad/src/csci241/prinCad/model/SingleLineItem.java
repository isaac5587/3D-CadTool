package csci241.prinCad.model;

import csci240.prinCad.fx3d.Map3d;
import csci240.prinCad.fx3d.Xform;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;

public class SingleLineItem extends CadItem {

	public final double _xPivot;
	public final double _yPivot;
	public final double _xEnd;
	public final double _yEnd;
	
	public CadLine _cadLine;

	public SingleLineItem(double xp, double yp, double xe, double ye) {
		_xPivot = xp;
		_yPivot = yp;
		_xEnd = xe;
		_yEnd = ye;
		_cadLine = new CadLine(_xPivot, _yPivot, _xEnd, _yEnd);
	}

	public void draw(GraphicsContext gc) {
		gc.strokeLine(_xPivot, _yPivot, _xEnd, _yEnd);

	}

	public String save() {

		String type = "Line ";
		return type + String.format("%1$f %2$f %3$f %4f", _xPivot, _yPivot, _xEnd, _yEnd);
	}

	public static SingleLineItem load(String line) {
		String mystring = line;
		String arr[] = mystring.split(" ");
		String xPivot = arr[1];
		String yPivot = arr[2];
		String xEnd = arr[3];
		String yEnd = arr[4];

		Double convertedXPivot = Double.valueOf(xPivot);
		Double convertedYPivot = Double.valueOf(yPivot);
		Double convertedXEnd = Double.valueOf(xEnd);
		Double convertedYEnd = Double.valueOf(yEnd);

		return new SingleLineItem(convertedXPivot, convertedYPivot, convertedXEnd, convertedYEnd);

	}

	public CadBox getRectangle() {
		return new CadBox(_xPivot, _yPivot, _xPivot, _yPivot);
	}

	@Override
	public boolean intersects(CadLine line) {
		return line.intersects(this._xPivot, this._yPivot, this._xEnd, this._yEnd);
	}

	@Override
	public CadItem copy() {
		return new SingleLineItem(_xPivot, _yPivot, _xEnd, _yEnd);
	}

	@Override
	public Node get3dObject(Map3d map) {
		final PhongMaterial material = new PhongMaterial();
		material.setDiffuseColor(Color.DARKGREEN);
		material.setSpecularColor(Color.AZURE);

		double xc = (this._xPivot + this._xEnd) / 2.0;
		double yc = (this._yPivot + this._yEnd) / 2.0;

		double rotationAngle = 0.0 - Math.acos(_cadLine.cos) * 180.0 / Math.PI;

		Cylinder cylinder = new Cylinder(5, map.Scale(_cadLine.len));
		cylinder.setMaterial(material);

		cylinder.setRotationAxis(Rotate.Z_AXIS);
		cylinder.setRotate(rotationAngle);

		Xform xform = new Xform();
		xform.getChildren().add(cylinder);
		xform.setTx(map.ToWorldX(xc));
		xform.setTy(map.ToWorldY(yc));
	  	xform.setTz(map.ToWorldZ(getElevation()));
		return xform;
	}

}
