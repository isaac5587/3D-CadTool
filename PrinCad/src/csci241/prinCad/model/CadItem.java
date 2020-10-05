package csci241.prinCad.model;

import csci240.prinCad.fx3d.Item3dInterface;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class CadItem implements Item3dInterface {

	protected boolean _isSelected;

	protected double _elevation = 200.0; // default value

	public double getElevation() { // getter
		return this._elevation;
	}

	public void setElevation(double elevation) { // setter
		this._elevation = elevation;
	}

	public abstract CadBox getRectangle();

	public abstract boolean intersects(CadLine line);

	public boolean isSelection() {
		return _isSelected;
	}

	protected abstract void draw(GraphicsContext gc);

	public void draw(GraphicsContext gc, Color originalColor, Color newColor) {
		if (_isSelected) {
			gc.setStroke(newColor);
			gc.setLineWidth(1);
			draw(gc);

			gc.setStroke(originalColor);
			gc.setLineWidth(0);
		} else {
			draw(gc);
		}
	}

	public abstract String save();

	// public abstract void clear (GraphicsContext gc);

	public void select(CadBox rect) {
		if (rect.isInside(getRectangle()))
			_isSelected = !_isSelected;
	}

	public void select(CadLine line) {
		if (intersects(line))
			_isSelected = !_isSelected;
	}

	public boolean intersects(CadPoint point) {
		// not yet implemented
		return false;
	}

	public abstract CadItem copy();

}
