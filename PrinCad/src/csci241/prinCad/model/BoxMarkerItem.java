package csci241.prinCad.model;

import csci240.prinCad.fx3d.Map3d;
import csci240.prinCad.fx3d.Xform;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class BoxMarkerItem extends MarkerItem {

	public BoxMarkerItem(CadPoint point) {
		super(point);
	}

	@Override
	public Node get3dObject(Map3d map) {

		final PhongMaterial material = new PhongMaterial();
		material.setDiffuseColor(Color.PURPLE);
		material.setSpecularColor(Color.PURPLE);

		double s = map.Scale(MarkerSize + MarkerSize);
		Box box = new Box(s, s, s);
		box.setMaterial(material);

		Xform xform = new Xform();
		xform.getChildren().add(box);
		xform.setTx(map.ToWorldX(_point.x));
		xform.setTy(map.ToWorldY(_point.y));
		return xform;
	}

	public static BoxMarkerItem load(String data) {

		CadPoint point = MarkerItem.ToPoint(data);
		if (point == null)
			return null;

		return new BoxMarkerItem(point);
	}

	@Override
	protected void draw(GraphicsContext gc) {

		double x = _point.x;
		double y = _point.y;
		gc.setStroke(Color.PURPLE);
		gc.strokeRect(x - MarkerSize, y, MarkerSize, MarkerSize);

	}

	@Override
	public CadItem copy() {
		return new BoxMarkerItem(_point);
	}

	@Override
	public boolean intersects(CadLine line) {
		return false;
	}

	@Override
	public CadBox getRectangle() {
		return null;
	}
}
