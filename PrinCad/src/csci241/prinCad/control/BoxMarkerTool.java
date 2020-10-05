package csci241.prinCad.control;

import csci241.prinCad.model.BoxMarkerItem;
import csci241.prinCad.model.CadPoint;
import csci241.prinCad.model.MarkerItem;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class BoxMarkerTool extends MarkerTool {

	GraphicsContext _gc;

	@Override
	protected void Draw(CanvasToolInterface canvas, MouseEvent e) {

		_gc = canvas.getGC();

		double x = e.getX();
		double y = e.getY();
		_gc.setStroke(Color.PURPLE);
		_gc.strokeRect(x - MarkerSize, y, MarkerSize, MarkerSize);

	}

	@Override
	protected MarkerItem createMarkerItem(CadPoint point) {

		return new BoxMarkerItem(point);
	}

}
