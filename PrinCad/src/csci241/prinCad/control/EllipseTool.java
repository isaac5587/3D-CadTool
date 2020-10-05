package csci241.prinCad.control;

import csci241.prinCad.model.CadItem;
import csci241.prinCad.model.CircleItem;
import csci241.prinCad.model.EllipseItem;
import csci241.prinCad.model.RectangleItem;
import csci241.prinCad.ui.PrinCanvas;
import javafx.scene.Cursor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class EllipseTool implements CadTool {

	double _xPivot, _yPivot, _xEnd, _yEnd;
	boolean _activeMouse;

	GraphicsContext _gc;

	@Override
	public void mousePressed(CanvasToolInterface canvas, MouseEvent e) {
		if (e.getButton() == MouseButton.PRIMARY) {
			double x = e.getX();
			double y = e.getY();
			_xPivot = x;
			_yPivot = y;
			_xEnd = x;
			_yEnd = y;
			_activeMouse = true;
			_gc = canvas.getGraphicsContext2D();
			_gc.setStroke(Color.RED);
			_gc.setLineWidth(0);
			canvas.setCursor(Cursor.CROSSHAIR);
		}
	}

	@Override
	public void mouseDragged(CanvasToolInterface canvas, MouseEvent e) {
		if (_activeMouse) {
			double x = Math.min(_xPivot, _xEnd) - 1;
			double y = Math.min(_yPivot, _yEnd) - 1;
			double w = Math.abs(_xEnd - _xPivot) + 2;
			double h = Math.abs(_yEnd - _yPivot) + 2;
			// _gc.fillRect(x, y, w, h);
			_xEnd = e.getX();
			_yEnd = e.getY();
			x = Math.min(_xPivot, _xEnd);
			y = Math.min(_yPivot, _yEnd);
			w = Math.abs(_xEnd - _xPivot);
			h = Math.abs(_yEnd - _yPivot);
			canvas.draw();

			_gc.strokeOval(x, y, w, h);

		}

	}

	@Override
	public void mouseRelease(CanvasToolInterface canvas, MouseEvent e) {
		if (_activeMouse) {
			_activeMouse = false;
			canvas.setCursor(Cursor.DEFAULT);
			_gc = null;
			double x = Math.min(_xPivot, _xEnd);
			double y = Math.min(_yPivot, _yEnd);
			double w = Math.abs(_xEnd - _xPivot);
			double h = Math.abs(_yEnd - _yPivot);
			EllipseItem _ellipseItem = new EllipseItem(x, y, w, h);
			canvas.reset(_ellipseItem);

		}
	}

}