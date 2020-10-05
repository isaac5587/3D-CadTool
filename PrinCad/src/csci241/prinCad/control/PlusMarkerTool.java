package csci241.prinCad.control;

import csci241.prinCad.model.CadPoint;
import csci241.prinCad.model.MarkerItem;
import csci241.prinCad.model.PlusMarkerItem;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class PlusMarkerTool extends MarkerTool {

	GraphicsContext _gc;

	@Override
	protected void Draw(CanvasToolInterface canvas, MouseEvent e) {

		_gc = canvas.getGC();

		double x = e.getX();
		double y = e.getY();
		_gc.setStroke(Color.GREEN);
		_gc.strokeLine(x - MarkerSize, y, x + MarkerSize, y);
		_gc.strokeLine(x, y - MarkerSize, x, y + MarkerSize);

	}

	@Override
	protected MarkerItem createMarkerItem(CadPoint point) {

		return new PlusMarkerItem(point);
	}

}
