package csci241.prinCad.control;

import csci241.prinCad.model.CadBox;
import csci241.prinCad.model.CircleItem;
import csci241.prinCad.model.ModelManager;
import csci241.prinCad.model.RectangleItem;
import csci241.prinCad.ui.PrinCanvas;
import javafx.scene.Cursor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class BoxSelectionTool implements CadTool {

	double _xPivot, _yPivot, _xEnd, _yEnd, _x, _y, _w, _h;
	boolean _activeMouse;
	double w = Math.abs(_xEnd - _xPivot) + 2;
	double h = Math.abs(_yEnd - _yPivot) + 2;

	GraphicsContext _gc;

	public void mousePressed(CanvasToolInterface canvas, MouseEvent e) {
		if (e.getButton() == MouseButton.PRIMARY) {
			double x = e.getX();
			double y = e.getY();
			_xPivot = x;
			_yPivot = y;
			_xEnd = x;
			_yEnd = y;
			_x = x;
			_y = y;
			_w = 0.0;
			_h = 0.0;
			_activeMouse = true;
			_gc = canvas.getGraphicsContext2D();
			_gc.setStroke(Color.TURQUOISE);
			_gc.setLineWidth(0);
			canvas.setCursor(Cursor.CROSSHAIR);
		}
	}

	public void mouseDragged(CanvasToolInterface canvas, MouseEvent e) {
		if (_activeMouse) {
			canvas.draw();
			// double x = Math.min(_xPivot, _xEnd) - 1;
			// double y = Math.min(_yPivot, _yEnd) - 1;
			// double w = Math.abs(_xEnd - _xPivot) + 2;
			// double h = Math.abs(_yEnd - _yPivot) + 2;
			// _gc.fillRect(x, y, w, h);
			_xEnd = e.getX();
			_yEnd = e.getY();
			_x = Math.min(_xPivot, _xEnd);
			_y = Math.min(_yPivot, _yEnd);
			_w = Math.abs(_xEnd - _xPivot);
			_h = Math.abs(_yEnd - _yPivot);
			_gc.setStroke(Color.TURQUOISE);
			_gc.strokeRect(_x, _y, _w, _h);

		}

	}

	public void mouseRelease(CanvasToolInterface canvas, MouseEvent e) {
		if (_activeMouse) {
			_activeMouse = false;
			canvas.setCursor(Cursor.DEFAULT);
			canvas.getModel().select(new CadBox(_x, _y, _x + _w, _y + _h));
			canvas.draw();
		}
	}
}
