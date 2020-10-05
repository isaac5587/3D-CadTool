package csci241.prinCad.control;

import csci241.prinCad.model.CadPoint;
import csci241.prinCad.model.MarkerItem;
import csci241.prinCad.ui.PrinCanvas;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public abstract class MarkerTool implements CadTool {

	protected final double MarkerSize = 4;

	// Mouse movement properties
	boolean _activeMouse;

	// Only place a marker when the primary mouse button is clicked

	@Override
	public void mousePressed(CanvasToolInterface canvas, MouseEvent e) {

		if (e.getButton() == MouseButton.PRIMARY) {
			canvas.setCursor(Cursor.CROSSHAIR);
			_activeMouse = true;
		}
	}

	@Override
	public void mouseDragged(CanvasToolInterface canvas, MouseEvent e) {
	}

	// Actually place the marker when the user has released the mouse button
	@Override
	public void mouseRelease(CanvasToolInterface canvas, MouseEvent e) {

		if (_activeMouse) {
			_activeMouse = false;
			canvas.setCursor(Cursor.DEFAULT);
			canvas.getGC().setStroke(Color.ORANGERED);
			canvas.getGC().setLineWidth(0);

			Draw(canvas, e);

			canvas.reset(createMarkerItem(new CadPoint(e.getX(), e.getY())));
		}
	}

	// Have the derived class do the actual drawing of the marker
	protected abstract void Draw(CanvasToolInterface canvas, MouseEvent e);

	protected abstract MarkerItem createMarkerItem(CadPoint point);

}
