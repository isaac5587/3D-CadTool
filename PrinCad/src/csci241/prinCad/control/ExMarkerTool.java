package csci241.prinCad.control;

import csci241.prinCad.model.CadPoint;
import csci241.prinCad.model.ExMarkerItem;
import csci241.prinCad.model.MarkerItem;
import csci241.prinCad.ui.PrinCanvas;
import javafx.scene.Cursor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class ExMarkerTool extends MarkerTool {

	GraphicsContext _gc;

	@Override
	protected void Draw(CanvasToolInterface canvas, MouseEvent e) {

		_gc = canvas.getGC();

		double x = e.getX();
		double y = e.getY();
		_gc.setStroke(Color.RED);
		_gc.strokeLine(x - MarkerSize, y - MarkerSize, x + MarkerSize, y + MarkerSize);
		_gc.strokeLine(x + MarkerSize, y - MarkerSize, x - MarkerSize, y + MarkerSize);
		
	 

	}

	@Override
	protected MarkerItem createMarkerItem(CadPoint point) {
		
		return new ExMarkerItem(point);
	}

}
