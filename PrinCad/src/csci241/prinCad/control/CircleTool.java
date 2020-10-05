package csci241.prinCad.control;

import csci241.prinCad.model.CadItem;
import csci241.prinCad.model.CircleItem;
import csci241.prinCad.ui.PrinCanvas;
import javafx.scene.Cursor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class CircleTool implements CadTool {

	double _xPivot, _yPivot, _xEnd, _yEnd, dx, dy, r;
	boolean _activeMouse;

	GraphicsContext _gc;

	@Override
	public void mousePressed(CanvasToolInterface canvas, MouseEvent e) {
		if (e.getButton() == MouseButton.PRIMARY) {
			double x = e.getX();
			double y = e.getY();
			_xPivot = x;
			_yPivot = y;
			// _xEnd = x;
			// _yEnd = y;
			_activeMouse = true;
			_gc = canvas.getGraphicsContext2D();
			_gc.setLineWidth(0);
			canvas.setCursor(Cursor.CROSSHAIR);
		}
	}

	@Override
	public void mouseDragged(CanvasToolInterface canvas, MouseEvent e) {
		if (_activeMouse) {
			// _gc.fillRect(_xPivot - r, _yPivot - r, r * 2, r * 2);
			_gc = canvas.getGraphicsContext2D();
			canvas.draw();
			dx = _xPivot - e.getX();
			dy = _yPivot - e.getY();
			r = Math.sqrt(dx * dx + dy * dy);
			_xEnd = e.getX();
			_yEnd = e.getY();
			_gc.strokeOval(_xPivot - r, _yPivot - r, r * 2, r * 2);

		}

	}

	@Override
	public void mouseRelease(CanvasToolInterface canvas, MouseEvent e) {
		if (_activeMouse) {
			_activeMouse = false;
			canvas.setCursor(Cursor.DEFAULT);
			CircleItem _circleItem = new CircleItem(_xPivot, _yPivot, r);
			canvas.reset(_circleItem);

		}
	}

}
