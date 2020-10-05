package csci241.prinCad.control;

import csci241.prinCad.model.CircleItem;
import csci241.prinCad.model.SingleLineItem;
import csci241.prinCad.ui.PrinCanvas;
import javafx.scene.Cursor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class SingleLineTool implements CadTool {
	boolean _activeMouse;
	double _xPivot, _yPivot, _xEnd, _yEnd;
	GraphicsContext _gc;

	// _dx = _xPivot - e.getX();
	// _dy = _yPivot - e.getY();
	// _r = Math.sqrt(_dx * _dx + _dy *_dy);

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
			_gc.setStroke(Color.BLACK);
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
			_gc.fillRect(x, y, w, h);
			_xEnd = e.getX();
			_yEnd = e.getY();
			_gc.setStroke(Color.BLACK);
			_gc.strokeLine(_xPivot, _yPivot, _xEnd, _yEnd);
		}
	}

	@Override
	public void mouseRelease(CanvasToolInterface canvas, MouseEvent e) {
		if (_activeMouse) {
			_activeMouse = false;
			canvas.setCursor(Cursor.DEFAULT);
			SingleLineItem _lineItem = new SingleLineItem(_xPivot, _yPivot, _xEnd, _yEnd);
			canvas.reset(_lineItem);

		}

	}
}
