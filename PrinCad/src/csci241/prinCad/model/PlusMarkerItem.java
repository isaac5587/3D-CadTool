package csci241.prinCad.model;

import csci240.prinCad.fx3d.Map3d;
import csci240.prinCad.fx3d.Xform;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;

public class PlusMarkerItem extends MarkerItem {

	public PlusMarkerItem(CadPoint point) {
		super(point);
	}

	@Override
	public Node get3dObject(Map3d map) {

		final PhongMaterial material = new PhongMaterial();
		material.setDiffuseColor(Color.GREEN);
		material.setSpecularColor(Color.GREEN);

		double s = map.Scale(MarkerSize + MarkerSize) * 2.0;

		Cylinder cylinderX = new Cylinder(0.25, s);
		cylinderX.setMaterial(material);
		cylinderX.setRotationAxis(Rotate.X_AXIS);
		cylinderX.setRotate(90);

		Cylinder cylinderY = new Cylinder(0.25, s);
		cylinderY.setMaterial(material);

		cylinderY.setRotationAxis(Rotate.Y_AXIS);
		cylinderY.setRotate(90);

		Cylinder cylinderZ = new Cylinder(0.25, s);
		cylinderZ.setMaterial(material);

		cylinderZ.setRotationAxis(Rotate.Z_AXIS);
		cylinderZ.setRotate(90);

		Xform xform = new Xform();
		xform.getChildren().add(cylinderX);
		xform.getChildren().add(cylinderY);
		xform.getChildren().add(cylinderZ);
		xform.setTx(map.ToWorldX(_point.x));
		xform.setTy(map.ToWorldY(_point.y));

		return xform;

	}

	public static PlusMarkerItem load(String data) {

		CadPoint point = MarkerItem.ToPoint(data);
		if (point == null)
			return null;

		return new PlusMarkerItem(point);
	}

	@Override
	protected void draw(GraphicsContext gc) {

		double x = _point.x;
		double y = _point.y;
		gc.setStroke(Color.GREEN);
		gc.strokeLine(x - MarkerSize, y, x + MarkerSize, y);
		gc.strokeLine(x, y - MarkerSize, x, y + MarkerSize);

	}

	@Override
	public CadItem copy() {
		return new PlusMarkerItem(_point);

	}

	@Override
	public CadBox getRectangle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean intersects(CadLine line) {
		// TODO Auto-generated method stub
		return false;
	}

}
